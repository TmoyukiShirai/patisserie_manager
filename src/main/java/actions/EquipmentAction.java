package actions;

import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

import javax.servlet.ServletException;

import actions.views.EmployeeView;
import actions.views.EquipmentView;
import constants.AttributeConst;
import constants.ForwardConst;
import constants.JpaConst;
import constants.MessageConst;
import models.Equipment;
import services.EquipmentService;

/**
 * 備品に関する処理を行うActionクラス
 *
 */
public class EquipmentAction extends ActionBase {

    private EquipmentService service;

    /**
     * メソッドを実行する
     */
    @Override
    public void process() throws ServletException, IOException {

        service = new EquipmentService();

        //メソッドを実行
        invoke();
        service.close();
    }

    /**
     * 一覧画面を表示する
     * @throws ServletException
     * @throws IOException
     */
    public void index() throws ServletException, IOException {

        //指定されたページ数の一覧画面に表示する備品データを取得
        int page = getPage();
        List<EquipmentView> equipments = service.getAllPerPage(page);

        //全備品データの件数を取得
        long equipmentsCount = service.countAll();

        putRequestScope(AttributeConst.EQUIPMENTS, equipments); //取得した備品データ
        putRequestScope(AttributeConst.EQ_COUNT, equipmentsCount); //全ての備品データの件数
        putRequestScope(AttributeConst.PAGE, page); //ページ数
        putRequestScope(AttributeConst.MAX_ROW, JpaConst.ROW_PER_PAGE); //1ページに表示するレコードの数

        //セッションにフラッシュメッセージが設定されている場合はリクエストスコープに移し替え、セッションからは削除する
        String flush = getSessionScope(AttributeConst.FLUSH);
        if (flush != null) {
            putRequestScope(AttributeConst.FLUSH, flush);
            removeSessionScope(AttributeConst.FLUSH);
        }

        //一覧画面を表示
        forward(ForwardConst.FW_EQ_INDEX);
    }

    /**
     * 新規登録画面を表示する
     * @throws ServletException
     * @throws IOException
     */
    public void entryNew() throws ServletException, IOException {

        putRequestScope(AttributeConst.TOKEN, getTokenId()); //CSRF対策用トークン

        //新規登録画面を表示
        forward(ForwardConst.FW_EQ_NEW);

    }

    /**
     * 新規登録を行う
     * @throws ServletException
     * @throws IOException
     */
    public void create() throws ServletException, IOException {

        //CSRF対策 tokenのチェック
        if (checkToken()) {
            //パラメータの値をもとに備品情報のインスタンスを作成する
            EquipmentView iv = new EquipmentView(
                    null,
                    getRequestParam(AttributeConst.EQ_NAME),
                    getRequestParam(AttributeConst.EQ_MAKER),
                    getRequestParam(AttributeConst.EQ_SALER),
                    getDoubleRequestParam(AttributeConst.EQ_PRICE),
                    getDoubleRequestParam(AttributeConst.EQ_AMOUNT),
                    getRequestParam(AttributeConst.EQ_AMOUNT_U),
                    AttributeConst.DEL_FLAG_FALSE.getIntegerValue());

            //備品,アレルギー情報登録
            List<String> errors2 = service.create(iv);
            if (errors2.size() > 0) {
                //登録中にエラーがあった場合
                putRequestScope(AttributeConst.TOKEN, getTokenId()); //CSRF対策用トークン
                putRequestScope(AttributeConst.EQUIPMENT, iv);//入力された備品情報,Equipmentはインスタンス化されていて全カラムの情報をもつ
                putRequestScope(AttributeConst.ERR, errors2);//エラーのリスト
                //新規登録画面を再表示
                forward(ForwardConst.FW_EQ_NEW);

            } else {
                //登録中にエラーがなかった場合

                //セッションに登録完了のフラッシュメッセージを設定
                putSessionScope(AttributeConst.FLUSH, MessageConst.I_REGISTERED.getMessage());

                //一覧画面にリダイレクト
                redirect(ForwardConst.ACT_EQ, ForwardConst.CMD_INDEX);
            }
        }
    }

    /**
     * 詳細画面を表示する
     * @throws ServletException
     * @throws IOException
     */
    public void show() throws ServletException, IOException {

        //idを条件に備品データを取得する
        EquipmentView iv = service.findOne(toNumber(getRequestParam(AttributeConst.EQ_ID)));

        if (iv == null || iv.getDeleteFlag() == AttributeConst.DEL_FLAG_TRUE.getIntegerValue()) {
            //該当の備品データが存在しない場合はエラー画面を表示
            forward(ForwardConst.FW_ERR_UNKNOWN);

        } else {
            putRequestScope(AttributeConst.EQUIPMENT, iv); //取得した備品データ
            //詳細画面を表示
            forward(ForwardConst.FW_EQ_SHOW);
        }
    }

    /**
     * 編集画面を表示する
     * @throws ServletException
     * @throws IOException
     */
    public void edit() throws ServletException, IOException {

        //idを条件に備品データを取得する
        EquipmentView iv = service.findOne(toNumber(getRequestParam(AttributeConst.EQ_ID)));

        if (iv == null || iv.getDeleteFlag() == AttributeConst.DEL_FLAG_TRUE.getIntegerValue()) {
            //該当の備品データが存在しない場合はエラー画面を表示
            forward(ForwardConst.FW_ERR_UNKNOWN);
        }
        putRequestScope(AttributeConst.TOKEN, getTokenId()); //CSRF対策用トークン
        putRequestScope(AttributeConst.EQUIPMENT, iv); //取得した備品データ

        //編集画面を表示
        forward(ForwardConst.FW_EQ_EDIT);
    }

    /**
     * 更新を行う
     * @throws ServletException
     * @throws IOException
     */
    public void update() throws ServletException, IOException {

        //CSRF対策 tokenのチェック
        if (checkToken()) {

            //idを条件に備品データを取得する
            EquipmentView iv = service.findOne(toNumber(getRequestParam(AttributeConst.EQ_ID)));

            //入力された備品内容を設定する
            iv.setName(getRequestParam(AttributeConst.EQ_NAME));
            iv.setMaker(getRequestParam(AttributeConst.EQ_MAKER));
            iv.setSaler(getRequestParam(AttributeConst.EQ_SALER));
            iv.setPrice(getDoubleRequestParam(AttributeConst.EQ_PRICE));
            iv.setAmount(getDoubleRequestParam(AttributeConst.EQ_AMOUNT));
            iv.setAmount_u(getRequestParam(AttributeConst.EQ_AMOUNT_U));
            AttributeConst.DEL_FLAG_FALSE.getIntegerValue();

            //備品,アレルギーデータを更新する
            List<String> errors = service.update(iv);

            if (errors.size() > 0) {
                //更新中にエラーが発生した場合

                putRequestScope(AttributeConst.TOKEN, getTokenId()); //CSRF対策用トークン
                putRequestScope(AttributeConst.EQUIPMENT, iv); //入力された備品情報

                //編集画面を再表示
                forward(ForwardConst.FW_EQ_EDIT);
            } else {
                //更新中にエラーがなかった場合

                //セッションに更新完了のフラッシュメッセージを設定
                putSessionScope(AttributeConst.FLUSH, MessageConst.I_UPDATED.getMessage());

                //一覧画面にリダイレクト
                redirect(ForwardConst.ACT_EQ, ForwardConst.CMD_INDEX);

            }
        }
    }

    /**
     * 論理削除を行う
     * @throws ServletException
     * @throws IOException
     */
    public void destroy() throws ServletException, IOException {

        //CSRF対策 tokenのチェック
        if (checkToken()) { //追記

            //idを条件に食材データを論理削除する
            service.destroy(toNumber(getRequestParam(AttributeConst.EQ_ID)));

            //セッションに削除完了のフラッシュメッセージを設定
            putSessionScope(AttributeConst.FLUSH, MessageConst.I_DELETED.getMessage());

            //一覧画面にリダイレクト
            redirect(ForwardConst.ACT_EQ, ForwardConst.CMD_INDEX);
        }
    }
    public void search() throws ServletException, IOException {
        //検索に該当する備品を取得
        int page = getPage();
        String search = getRequestParam(AttributeConst.SEARCH);
        //検索に該当する備品データを、指定されたページ数の一覧画面に表示する分取得する
        List<EquipmentView> equipments = service.getNameBySearchPerPage(search, page);
        //検索に該当する備品データの件数を取得
        long myEquipmentsCount = service.countNameBySearch(search);
        putRequestScope(AttributeConst.EQUIPMENTS, equipments); //備品名をキーに取得した備品データ
        putRequestScope(AttributeConst.EQ_COUNT, myEquipmentsCount); //検索した備品の数
        putRequestScope(AttributeConst.PAGE, page); //ページ数
        putRequestScope(AttributeConst.MAX_ROW, JpaConst.ROW_PER_PAGE); //1ページに表示するレコードの数
        //一覧画面を表示
        forward(ForwardConst.FW_EQ_INDEX);
    }

    /**
     * 備品の発注書ページを表示する
     * @throws ServletException
     * @throws IOException
     */
    public void showOrder() throws ServletException, IOException {

       int page = getPage();
       String saler = getRequestParam(AttributeConst.EQ_SALER);
       //取引先を条件に備品テーブルからデータを取得する
       List<Equipment> equipments = service.getAllEqsOfSaler(saler,page);
       //取得したデータをリクエストスコープにセットする
       putRequestScope(AttributeConst.EQUIPMENTS, equipments); //取得した発注書データ
       putRequestScope(AttributeConst.EQ_SALER, saler); //表示のため取引先名も別でセット
       //検索に該当する備品データの件数を取得
       long EquipmentsCount = service.countAllEqsOfSaler(saler);
       putRequestScope(AttributeConst.INGR_COUNT, EquipmentsCount); //検索した備品の数
       putRequestScope(AttributeConst.PAGE, page); //ページ数
       putRequestScope(AttributeConst.MAX_ROW, JpaConst.ROW_PER_PAGE); //1ページに表示するレコードの数


       //今日の日付を取得
       LocalDate orderDate_b = LocalDate.now();
       DateTimeFormatter Date_full = DateTimeFormatter.ofPattern("yyyy年MM月dd日(E)");//表示形式変更(年あり）
       String orderDate1 = orderDate_b.format(Date_full);//表示形式変更
       putRequestScope(AttributeConst.ORDER_DATE_1, orderDate1);

       DateTimeFormatter Date_short = DateTimeFormatter.ofPattern("MM月dd日(E)");//表示形式変更(年なし)
       String orderDate2 = orderDate_b.format(Date_short);//表示形式変更
       putRequestScope(AttributeConst.ORDER_DATE_2, orderDate2);
       //納品日を3日後に設定
       LocalDate deliDate_b = orderDate_b.plusDays(3);
       String deliDate = deliDate_b.format(Date_short);//表示形式変更
       putRequestScope(AttributeConst.DELI_DATE, deliDate);
       //セッションからログイン中の従業員情報を取得、発注者に設定
       EmployeeView ev = (EmployeeView) getSessionScope(AttributeConst.LOGIN_EMP);
       putRequestScope(AttributeConst.LOGIN_EMP, ev);
       putRequestScope(AttributeConst.TOKEN, getTokenId()); //CSRF対策用トークン

       //発注画面を表示
       forward(ForwardConst.FW_ORDER_SHOW_EQ);
        }


}
