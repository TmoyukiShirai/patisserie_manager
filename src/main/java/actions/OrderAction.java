package actions;

import java.io.IOException;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;

import actions.views.EmployeeConverter;
import actions.views.EmployeeView;
import actions.views.OrderHistoryView;
import constants.AttributeConst;
import constants.ForwardConst;
import constants.JpaConst;
import constants.MessageConst;
import models.Employee;
import models.Ingredient;
import models.JoinOrderHistory;
import services.OrderService;

/**
 * 発注書に関する処理を行うActionクラス
 *
 */
public class OrderAction extends ActionBase {

    private OrderService service;

    /**
     * メソッドを実行する
     */
    @Override
    public void process() throws ServletException, IOException {

        service = new OrderService();

        //メソッドを実行
        invoke();
        service.close();
    }

    /**
     * 発注書インデックスページを表示する
     * @throws ServletException
     * @throws IOException
     */
    public void index() throws ServletException, IOException {
        //一覧画面を表示
        forward(ForwardConst.FW_ORDER_INDEX);
    }


    //発注書作成showOrderはIngredientActionに記述

    /**
     * 発注履歴一覧画面を表示する
     * @throws ServletException
     * @throws IOException
     */
    public void indexOrderHistory() throws ServletException, IOException {

        //全発注履歴を取得
        String saler = getRequestParam(AttributeConst.J_ODH_SALER);

        List<JoinOrderHistory> orderHistories = service.getAllSaler(saler);

        //件数を取得
        long orderHistoriesCount = service.countAllSaler(saler);

        //取得したデータをリクエストスコープにセットする
        putRequestScope(AttributeConst.J_ODHS, orderHistories); //取得した発注書データ
        putRequestScope(AttributeConst.J_ODHS_COUNT, orderHistoriesCount); //全ての発注書データの件数
        int page = getPage();
        putRequestScope(AttributeConst.PAGE, page); //ページ数
        putRequestScope(AttributeConst.MAX_ROW, JpaConst.ROW_PER_PAGE); //1ページに表示するレコードの数

        //セッションにフラッシュメッセージが設定されている場合はリクエストスコープに移し替え、セッションからは削除する
        String flush = getSessionScope(AttributeConst.FLUSH);
        if (flush != null) {
            putRequestScope(AttributeConst.FLUSH, flush);
            removeSessionScope(AttributeConst.FLUSH);
        }

        //一覧画面を表示
        forward(ForwardConst.FW_ORDER_INDEX_ODH);
    }

    /**
     * 発注履歴詳細画面を表示する
     * @throws ServletException
     * @throws IOException
     */
    public void showOrderHistory() throws ServletException, IOException {

        //指定された日付の発注書データを取得
        String orderedDate = getRequestParam(AttributeConst.ODH_ODD_DATE);
        List<OrderHistoryView> orderHistories = service.getAllOdhsOfDate(orderedDate);

        //発注書データの件数を取得
        long orderHistoriesCount = service.countAllOdhsOfDate(orderedDate);

        //取得したデータをリクエストスコープにセットする
        putRequestScope(AttributeConst.ODHS, orderHistories); //取得した発注書データ
        putRequestScope(AttributeConst.ODHS_COUNT, orderHistoriesCount); //全ての発注書データの件数
        int page = getPage();
        putRequestScope(AttributeConst.PAGE, page); //ページ数
        putRequestScope(AttributeConst.MAX_ROW, JpaConst.ROW_PER_PAGE); //1ページに表示するレコードの数

        //JoinOrderHistoryの情報をセット
        String odd_date = getRequestParam(AttributeConst.J_ODH_ODD_DATE);
        putRequestScope(AttributeConst.J_ODH_ODD_DATE,odd_date);
        String saler = getRequestParam(AttributeConst.J_ODH_SALER);
        putRequestScope(AttributeConst.J_ODH_SALER,saler);
        String odd_emp = getRequestParam(AttributeConst.J_ODH_ODD_EMP);
        putRequestScope(AttributeConst.J_ODH_ODD_EMP,odd_emp);

        //合計金額を計算
        double totalPrice_b = 0;
        for (int i = 0; i< orderHistories.size(); i++) {
            OrderHistoryView tmpOh = orderHistories.get(i);
            Double tmpPrice = tmpOh.getPrice();
            totalPrice_b = totalPrice_b + tmpPrice;//合計金額を足していく
        }
        int totalPrice_a = (int)(Math.round(totalPrice_b)); // 小数第1位で四捨五入してintに変換
        NumberFormat comFormat = NumberFormat.getNumberInstance(); //カンマ区切りに変換
        String totalPrice = String.valueOf(comFormat.format(totalPrice_a));
        putRequestScope(AttributeConst.ODH_TOTAL_PRICE,totalPrice);

        //詳細画面を表示
        forward(ForwardConst.FW_ORDER_SHOW_ODH);
    }

    /**
     * 発注したデータを履歴に登録する
     * @throws ServletException
     * @throws IOException
     */
    public void create() throws ServletException, IOException {
        //CSRF対策 tokenのチェック
        if (checkToken()) {

            List<OrderHistoryView> odhList = new ArrayList<OrderHistoryView>();

            //セッションからログイン中の従業員情報を取得
            EmployeeView orderedEmp = (EmployeeView) getSessionScope(AttributeConst.LOGIN_EMP);

            String[] oddDates = getRequestParams(AttributeConst.ODH_ODD_DATE);
            String[] deliDates = getRequestParams(AttributeConst.ODH_DELI_DATE);
            String[] numOfOds = getRequestParams(AttributeConst.ODH_NUM_OF_ORDER);
            String[] prices = getRequestParams(AttributeConst.ODH_PRICE);
            String[] ingrIds = getRequestParams(AttributeConst.INGR_ID);

            int tmpSum = 0;
            for (int i = 0; i< numOfOds.length; i++){
                String tmpN = numOfOds[i];
                String tmpN2 = tmpN + "0" ;
                int tmp3 = Integer.parseInt(tmpN2);
                tmpSum = tmpSum + tmp3;
            }
                if(tmpSum == 0) {//発注数が1つも入力されていないとき
                    //注文失敗エラーメッセージ表示フラグをたてる
                    putRequestScope(AttributeConst.NUM_ERR, true);
                    //一覧画面を表示
                    forward(ForwardConst.FW_ORDER_INDEX);
            }else {

            for (int i = 0; i< numOfOds.length; i++){//発注数が入力された数

                if(numOfOds[i] != "") {
                   Double numOfOrder = Double.parseDouble(numOfOds[i]);
                   String oddDate = oddDates[i];
                   String deliDate = deliDates[i];
                   Double price_b = Double.parseDouble(prices[i]);
                   Double priceAfter = price_b * numOfOrder;
                   int ingrId = Integer.parseInt(ingrIds[i]);
                   Ingredient ingredient = service.findOne(ingrId);

                   OrderHistoryView odhEntity = new OrderHistoryView();

                   if (numOfOrder == null || numOfOrder == 0.0) {
                       // 発注数が未入力の場合、データを登録しない
                       continue;
                   }else {
                       odhEntity.setOrderedDate(oddDate);
                       odhEntity.setDeliDate(deliDate);
                       odhEntity.setIngredient(ingredient);
                       odhEntity.setNumberOfOrder(numOfOrder);
                       odhEntity.setPrice(priceAfter);
                       odhEntity.setOrderedEmp(orderedEmp);

                       odhList.add(odhEntity);
                    }
                   }
                 }
            //下記３つはjoinOrderHistoryに登録する情報
            String oddDate = getRequestParam(AttributeConst.ODH_ODD_DATE);
            String saler = getRequestParam(AttributeConst.INGR_SALER);
            Employee oddEmp = EmployeeConverter.toModel(orderedEmp);
            service.create(odhList,oddDate,saler,oddEmp);

            //セッションに登録完了のフラッシュメッセージを設定
            putSessionScope(AttributeConst.FLUSH, MessageConst.I_ORDERED.getMessage());
            //発注書メニュー
            forward(ForwardConst.FW_ORDER_INDEX);
                }
            }
        }
    }