package actions;

import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;

import actions.views.EmployeeView;
import actions.views.IngredientView;
import constants.AttributeConst;
import constants.ForwardConst;
import constants.JpaConst;
import constants.MessageConst;
import models.Allergy;
import models.AllergyItem;
import models.Ingredient;
import services.IngredientService;

/**
 * 食材に関する処理を行うActionクラス
 *
 */
public class IngredientAction extends ActionBase {

    private IngredientService service;

    /**
     * メソッドを実行する
     */
    @Override
    public void process() throws ServletException, IOException {

        service = new IngredientService();

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

        //指定されたページ数の一覧画面に表示する食材データを取得
        int page = getPage();
        List<IngredientView> ingredients = service.getAllPerPage(page);

        //全食材データの件数を取得
        long ingredientsCount = service.countAll();

        //取得したデータをリクエストスコープにセットする
        putRequestScope(AttributeConst.INGREDIENTS, ingredients); //取得した食材データ
        putRequestScope(AttributeConst.INGR_COUNT, ingredientsCount); //全ての食材データの件数
        putRequestScope(AttributeConst.PAGE, page); //ページ数
        putRequestScope(AttributeConst.MAX_ROW, JpaConst.ROW_PER_PAGE); //1ページに表示するレコードの数

        //セッションにフラッシュメッセージが設定されている場合はリクエストスコープに移し替え、セッションからは削除する
        String flush = getSessionScope(AttributeConst.FLUSH);
        if (flush != null) {
            putRequestScope(AttributeConst.FLUSH, flush);
            removeSessionScope(AttributeConst.FLUSH);
        }

        //一覧画面を表示
        forward(ForwardConst.FW_INGR_INDEX);
    }

    /**
     * 新規登録画面を表示する
     * @throws ServletException
     * @throws IOException
     */
    public void entryNew() throws ServletException, IOException {

        putRequestScope(AttributeConst.TOKEN, getTokenId()); //CSRF対策用トークン
        List<AllergyItem> allergyItems = service.getAllAllergy();
        putRequestScope(AttributeConst.ALGY_ITEMS, allergyItems);

        // 編集画面でも利用するため、空のインスタンスを生成
        IngredientView iv = new IngredientView();
        // リクエストスコープにセット
        putRequestScope(AttributeConst.INGREDIENT, iv);

        //新規登録画面を表示
        forward(ForwardConst.FW_INGR_NEW);

    }

    /**
     * 新規登録を行う
     * @throws ServletException
     * @throws IOException
     */
    public void create() throws ServletException, IOException {
        //CSRF対策 tokenのチェック
        if (checkToken()) {//Ingredientを取得するときに紐づくAllergyのデータを取得できる

            //                   ↓INGR_ALLERGYが３つアレルギーを持っていれば、配列に３つ値が入る
            String[] allergyItems = getRequestParams(AttributeConst.ALGY_ITEM);
            //                                     ↑複数形

            // List<Allergy>を宣言
            List<Allergy> allergyList = new ArrayList<Allergy>();
            //                      ↓配列から要素を取り出し、この宣言した変数に詰めていく（allergiesの持っている配列の回数分）
            if (allergyItems != null) {
                for (String allergyItemId : allergyItems) {
                    // Allergyのインスタンスを生成する

                    AllergyItem allergyItemEntity = new AllergyItem();
                    allergyItemEntity.setId(toNumber(allergyItemId));
                    Allergy a = new Allergy();

                    a.setItem(allergyItemEntity);
                    allergyList.add(a);
                }
            }
            //パラメータの値をもとに食材情報のインスタンスを作成する
            IngredientView iv = new IngredientView(
                    null,
                    getRequestParam(AttributeConst.INGR_NAME),
                    getRequestParam(AttributeConst.INGR_MAKER),
                    getRequestParam(AttributeConst.INGR_SALER),
                    getDoubleRequestParam(AttributeConst.INGR_PRICE),
                    getDoubleRequestParam(AttributeConst.INGR_AMOUNT),
                    getRequestParam(AttributeConst.INGR_AMOUNT_U),
                    getDoubleRequestParam(AttributeConst.INGR_ENERGY),
                    allergyList,  //allergyListにはINGR_ALLERGYが格納されている
                    AttributeConst.DEL_FLAG_FALSE.getIntegerValue());
            List<String> errors = service.create(iv); //食材情報を登録
            if (errors.size() > 0) {
                //登録中にエラーがあった場合、下の３つの情報を入力項目に再表示
                putRequestScope(AttributeConst.TOKEN, getTokenId()); //CSRF対策用トークン
                putRequestScope(AttributeConst.INGREDIENT, iv);//入力された食材情報,Ingredientはインスタンス化されていて全カラムの情報をもつ
                putRequestScope(AttributeConst.ERR, errors);//エラーのリスト
                //新規登録画面を再表示
                forward(ForwardConst.FW_INGR_NEW);

            } else {
                //登録中にエラーがなかった場合

                //セッションに登録完了のフラッシュメッセージを設定
                putSessionScope(AttributeConst.FLUSH, MessageConst.I_REGISTERED.getMessage());

                //一覧画面にリダイレクト
                redirect(ForwardConst.ACT_INGR, ForwardConst.CMD_INDEX);
            }
        }
    }

    /**
     * 詳細画面を表示する
     * @throws ServletException
     * @throws IOException
     */
    public void show() throws ServletException, IOException {

        //idを条件に食材データを取得する
        IngredientView iv = service.findOne(toNumber(getRequestParam(AttributeConst.INGR_ID)));

        if (iv == null || iv.getDeleteFlag() == AttributeConst.DEL_FLAG_TRUE.getIntegerValue()) {
        //該当の食材データが存在しない場合はエラー画面を表示
            forward(ForwardConst.FW_ERR_UNKNOWN);

        } else {
            putRequestScope(AttributeConst.INGREDIENT, iv); //取得した食材データ
            //詳細画面を表示
            forward(ForwardConst.FW_INGR_SHOW);
        }
    }

    /**
     * 編集画面を表示する
     * @throws ServletException
     * @throws IOException
     */
    public void edit() throws ServletException, IOException {

        //idを条件に食材データを取得する
        IngredientView iv = service.findOne(toNumber(getRequestParam(AttributeConst.INGR_ID)));

        if (iv == null || iv.getDeleteFlag() == AttributeConst.DEL_FLAG_TRUE.getIntegerValue()) {
        //該当の食材データが存在しない場合はエラー画面を表示
            forward(ForwardConst.FW_ERR_UNKNOWN);
        }

        putRequestScope(AttributeConst.TOKEN, getTokenId()); //CSRF対策用トークン
        putRequestScope(AttributeConst.INGREDIENT, iv); //取得した食材データ

        List<AllergyItem> allergyItems = service.getAllAllergy();
        putRequestScope(AttributeConst.ALGY_ITEMS, allergyItems);
        //編集画面を表示
        forward(ForwardConst.FW_INGR_EDIT);
    }

    /**
     * 更新を行う
     * @throws ServletException
     * @throws IOException
     */
    public void update() throws ServletException, IOException {

        //CSRF対策 tokenのチェック
        if (checkToken()) {
            String[] allergies = getRequestParams(AttributeConst.ALGY_ITEM);
            // List<Allergy>の変数を宣言する
            List<Allergy> allergyList = new ArrayList<Allergy>();
            if (allergies != null) {
                for (String allergyItemId : allergies) {
                    // Allergyのインスタンスを生成する

                    AllergyItem allergyItemEntity = new AllergyItem();
                    allergyItemEntity.setId(toNumber(allergyItemId));
                    Allergy a = new Allergy();

                    a.setItem(allergyItemEntity);
                    allergyList.add(a);
                }
            }

            //idを条件に食材データを取得する
            IngredientView iv = service.findOne(toNumber(getRequestParam(AttributeConst.INGR_ID)));

            //入力された食材内容を設定する
            iv.setName(getRequestParam(AttributeConst.INGR_NAME));
            iv.setMaker(getRequestParam(AttributeConst.INGR_MAKER));
            iv.setSaler(getRequestParam(AttributeConst.INGR_SALER));
            iv.setPrice(getDoubleRequestParam(AttributeConst.INGR_PRICE));
            iv.setAmount(getDoubleRequestParam(AttributeConst.INGR_AMOUNT));
            iv.setAmount_u(getRequestParam(AttributeConst.INGR_AMOUNT_U));
            iv.setEnergy(getDoubleRequestParam(AttributeConst.INGR_ENERGY));
            iv.setAllergy(allergyList);
            AttributeConst.DEL_FLAG_FALSE.getIntegerValue();

            //食材,アレルギーデータを更新する
            List<String> errors = service.update(iv);

            if (errors.size() > 0) {
                //更新中にエラーが発生した場合

                putRequestScope(AttributeConst.TOKEN, getTokenId()); //CSRF対策用トークン
                putRequestScope(AttributeConst.INGREDIENT, iv); //入力された食材情報
                putRequestScope(AttributeConst.ERR, errors); //エラーのリスト

                //編集画面を再表示
                forward(ForwardConst.FW_INGR_EDIT);
            } else {
                //更新中にエラーがなかった場合

                //セッションに更新完了のフラッシュメッセージを設定
                putSessionScope(AttributeConst.FLUSH, MessageConst.I_UPDATED.getMessage());

                //一覧画面にリダイレクト
                redirect(ForwardConst.ACT_INGR, ForwardConst.CMD_INDEX);

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
            service.destroy(toNumber(getRequestParam(AttributeConst.INGR_ID)));

            //セッションに削除完了のフラッシュメッセージを設定
            putSessionScope(AttributeConst.FLUSH, MessageConst.I_DELETED.getMessage());

            //一覧画面にリダイレクト
            redirect(ForwardConst.ACT_INGR, ForwardConst.CMD_INDEX);
        }
    }
    public void search() throws ServletException, IOException {
        //検索に該当する食材を取得
        int page = getPage();
        String search = getRequestParam(AttributeConst.SEARCH);
        //検索に該当する食材データを、指定されたページ数の一覧画面に表示する分取得する
        List<IngredientView> ingredients = service.getNameBySearchPerPage(search, page);
        //検索に該当する食材データの件数を取得
        long myIngredientsCount = service.countNameBySearch(search);
        putRequestScope(AttributeConst.INGREDIENTS, ingredients); //食材名をキーに取得した食材データ
        putRequestScope(AttributeConst.INGR_COUNT, myIngredientsCount); //検索した食材の数
        putRequestScope(AttributeConst.PAGE, page); //ページ数
        putRequestScope(AttributeConst.MAX_ROW, JpaConst.ROW_PER_PAGE); //1ページに表示するレコードの数
        //一覧画面を表示
        forward(ForwardConst.FW_INGR_INDEX);
        }


    /**
     * 食材の発注書ページを表示する
     * @throws ServletException
     * @throws IOException
     */
    public void showOrder() throws ServletException, IOException {

       int page = getPage();
       String saler = getRequestParam(AttributeConst.INGR_SALER);
       //取引先を条件に食材テーブルからデータを取得する
       List<Ingredient> ingredients = service.getAllIngrsOfSaler(saler,page);
       //取得したデータをリクエストスコープにセットする
       putRequestScope(AttributeConst.INGREDIENTS, ingredients); //取得した発注書データ
       putRequestScope(AttributeConst.INGR_SALER, saler); //表示のため取引先名も別でセット
       //検索に該当する食材データの件数を取得
       long IngredientsCount = service.countAllIngrsOfSaler(saler);
       putRequestScope(AttributeConst.INGR_COUNT, IngredientsCount); //検索した食材の数
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
       forward(ForwardConst.FW_ORDER_SHOW_INGR);
        }


}


