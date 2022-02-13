package actions;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;

import actions.views.ProcessRecipeView;
import actions.views.RecipeView;
import constants.AttributeConst;
import constants.ForwardConst;
import constants.JpaConst;
import constants.MessageConst;
import models.Allergy;
import models.AllergyItem;
import models.Ingredient;
import models.JoinIngredient;
import models.ProcessRecipe;
import models.Recipe;
import services.RecipeService;

/**
 * レシピに関する処理を行うActionクラス
 *
 */
public class RecipeAction extends ActionBase {

    private RecipeService service;

    /**
     * メソッドを実行する
     */
    @Override
    public void process() throws ServletException, IOException {

        service = new RecipeService();

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

        //指定されたページ数の一覧画面に表示するレシピデータを取得
        int page = getPage();
        List<RecipeView> recipes = service.getAllPerPage(page);

        //全レシピデータの件数を取得
        long recipesCount = service.countAll();

        putRequestScope(AttributeConst.RECIPES, recipes); //取得したレシピデータ
        putRequestScope(AttributeConst.RECI_COUNT, recipesCount); //全てのレシピデータの件数
        putRequestScope(AttributeConst.PAGE, page); //ページ数
        putRequestScope(AttributeConst.MAX_ROW, JpaConst.ROW_PER_PAGE); //1ページに表示するレコードの数

        //セッションにフラッシュメッセージが設定されている場合はリクエストスコープに移し替え、セッションからは削除する
        String flush = getSessionScope(AttributeConst.FLUSH);
        if (flush != null) {
            putRequestScope(AttributeConst.FLUSH, flush);
            removeSessionScope(AttributeConst.FLUSH);
        }

        //一覧画面を表示
        forward(ForwardConst.FW_RECI_INDEX);
    }

    /**
     * 新規登録画面を表示する
     * @throws ServletException
     * @throws IOException
     */
    public void entryNew() throws ServletException, IOException {
        List<JoinIngredient> tmpJoinIngredient = new ArrayList<JoinIngredient>(); //rvに渡す大枠
        for (int i = 0; i < 10 ; i++) {
            JoinIngredient tmp = new JoinIngredient(); //空のインスタンスを10個生成
            tmpJoinIngredient.add(tmp);
        }

        putRequestScope(AttributeConst.TOKEN, getTokenId()); //CSRF対策用トークン
        List<Ingredient> ingredients = service.getAllIngredients();
        putRequestScope(AttributeConst.INGREDIENTS, ingredients);

        // 編集画面でも利用するため、空のインスタンスを生成
        RecipeView rv = new RecipeView();
        // リクエストスコープにセット
        rv.setJoinIngredient(tmpJoinIngredient); //rvのjoinIngredientに10個の空のインスタンスをセット
        putRequestScope(AttributeConst.RECIPE, rv);

        //新規登録画面を表示
        forward(ForwardConst.FW_RECI_NEW);

    }

    /**
     * 新規登録を行う
     * @throws ServletException
     * @throws IOException
     */

    public void create() throws ServletException, IOException {
        //CSRF対策 tokenのチェック
        if (checkToken()) {

            //中間テーブルの食材リストのインスタンス生成
            List<JoinIngredient> ingrsAndAmountsList = new ArrayList<JoinIngredient>();
            String[] amounts = getRequestParams(AttributeConst.J_INGR_AMOUNT);
            String[] ingrs = getRequestParams(AttributeConst.J_INGR_INGREDIENT);
            String[] units = getRequestParams(AttributeConst.J_INGR_AMOUNT_U);
            for (int i = 0; i <10; i++) { //繰り返し
                // 0～9行目の食材IDを1件ずつ取得する
                String ingredientId = ingrs[i];
                String amount = amounts[i];
                String unit = units[i];
                // Ingredientのインスタンスを生成する
                Ingredient ingredientEntity = new Ingredient();
                //中間テーブルのインスタンス生成
                JoinIngredient j = new JoinIngredient();

                if (ingredientId == null || "noSelected".equals(ingredientId)) {
                    // 食材IDが未選択の場合、データを登録しない
                    continue;

                } else {
                    ingredientEntity.setId(toNumber(ingredientId));
                }

                if (amount == null || amount == "") {
                    continue;
                } else {

                    j.setAmount(Double.parseDouble(amount));
                }
                j.setAmount_u(unit);
                j.setIngredient(ingredientEntity);//JoinIngredientに食材名(ID)をセット
                ingrsAndAmountsList.add(j);
            }

                //パラメータの値をもとにレシピ情報のインスタンスを作成する
                RecipeView rv = new RecipeView(
                        null,
                        getRequestParam(AttributeConst.RECI_TITLE),
                        getRequestParam(AttributeConst.RECI_TYPE),
                        getDoubleRequestParam(AttributeConst.RECI_NUMBER),
                        ingrsAndAmountsList,
                        null,
                        AttributeConst.DEL_FLAG_FALSE.getIntegerValue());

                //レシピ情報登録
                List<String> errors = service.create(rv);

                if (errors.size() > 0) {
                    //登録中にエラーがあった場合

                    putRequestScope(AttributeConst.TOKEN, getTokenId()); //CSRF対策用トークン
                    putRequestScope(AttributeConst.RECIPE, rv);//入力されたレシピ情報
                    putRequestScope(AttributeConst.ERR, errors);//エラーのリスト

                    //新規登録画面を再表示
                    forward(ForwardConst.FW_RECI_NEW);

                } else {
                    //登録中にエラーがなかった場合

                    //セッションに登録完了のフラッシュメッセージを設定
                    putSessionScope(AttributeConst.FLUSH, MessageConst.I_REGISTERED.getMessage());

                    //一覧画面にリダイレクト
                    redirect(ForwardConst.ACT_RECI, ForwardConst.CMD_INDEX);
                }
            }
        }

    /**
     * 詳細画面を表示する
     * @throws ServletException
     * @throws IOException
     */
    public void show() throws ServletException, IOException {

        //idを条件にレシピデータを取得する
        RecipeView rv = service.findOne(toNumber(getRequestParam(AttributeConst.RECI_ID)));
        if (rv == null || rv.getDeleteFlag() == AttributeConst.DEL_FLAG_TRUE.getIntegerValue()) {
            //該当のレシピデータが存在しない場合はエラー画面を表示
            forward(ForwardConst.FW_ERR_UNKNOWN);

        } else {

            List<JoinIngredient> recordJi = rv.getJoinIngredient();//レシピからJoinIngredientのリストを取り出す
            List<JoinIngredient> tempJoinIngredientList = new ArrayList<JoinIngredient>();//空のリスト
            double totalAmountReci_b = 0;
            double totalEnergy_b = 0;
            double totalPrice_b = 0;

            for (int i = 0; i < 10 ; i++) {
                JoinIngredient tmpJi = new JoinIngredient();//データを1つずつ詰めるインスタンス
                if ( i <= (recordJi.size() - 1 )) {
                    tmpJi = recordJi.get(i);//リストのi番目のデータをtmpに詰める,tmpはリストから取り出した１つのデータ

                    int tempJiId = tmpJi.getId();//取得してそのまま詰め直す
                    Recipe tempRecipe = tmpJi.getRecipe();//取得してそのまま詰め直す
                    String tempAmount_u = tmpJi.getAmount_u();//取得してそのまま詰め直す
                    Double tempAmount = tmpJi.getAmount();

                    tmpJi.setId(tempJiId);//JoinIngredientのインスタンスに格納
                    tmpJi.setRecipe(tempRecipe);
                    tmpJi.setAmount_u(tempAmount_u);
                    tmpJi.setAmount(tempAmount);

                    Ingredient recordIngr = tmpJi.getIngredient();//tmpから食材情報を取り出す
                    int tempIngrId = recordIngr.getId();//取得してそのまま詰め直す
                    String tempIngrName = recordIngr.getName();//取得してそのまま詰め直す
                    String tempMaker = recordIngr.getMaker();//取得してそのまま詰め直す
                    List<Allergy> tempAllergy = recordIngr.getAllergy();//取得してそのまま詰め直す

                    Double tempEnergy = recordIngr.getEnergy();
                    Double CalculatedEnergy_b = tempEnergy / 100 * tempAmount;//再計算されたエネルギー
                    Double CalculatedEnergy = ((double)Math.round(CalculatedEnergy_b * 10))/10; // 小数第二位で四捨五入
                    //1gあたりのエネルギー×使用分量

                    Double tempPrice = recordIngr.getPrice();
                    Double tempAmountIngr = recordIngr.getAmount();//食材の内容量
                    Double CalculatedPrice_b = tempPrice / tempAmountIngr * tempAmount;//再計算された単価
                    Double CalculatedPrice = ((double)Math.round(CalculatedPrice_b * 10))/10; // 小数第二位で四捨五入
                    //1gあたりの単価×使用分量

                    Ingredient calcData = new Ingredient();//インスタンスにセット
                    calcData.setEnergy(CalculatedEnergy);
                    calcData.setPrice(CalculatedPrice);
                    calcData.setId(tempIngrId);
                    calcData.setName(tempIngrName);
                    calcData.setMaker(tempMaker);
                    calcData.setAllergy(tempAllergy);
                    calcData.setDeleteFlag(AttributeConst.DEL_FLAG_FALSE.getIntegerValue());

                    tmpJi.setIngredient(calcData);//JoinIngredientのインスタンスに格納

                    tempJoinIngredientList.add(tmpJi);//JoinIngredientのリストに格納

                    totalAmountReci_b = totalAmountReci_b + tempAmount;//合計分量を足していく
                    totalEnergy_b = totalEnergy_b + CalculatedEnergy;//合計カロリーを足していく
                    totalPrice_b = totalPrice_b + CalculatedPrice;//合計金額を足していく
                }
            }

            Double totalAmountReci = ((double)Math.round(totalAmountReci_b * 10))/10; // 小数第二位で四捨五入
            Double totalEnergy = ((double)Math.round(totalEnergy_b * 10))/10; // 小数第二位で四捨五入
            Double totalPrice = ((double)Math.round(totalPrice_b * 10))/10; // 小数第二位で四捨五入

            int tempId = rv.getId();
            String tempTitle = rv.getTitle();
            String tempType = rv.getType();
            Double tempNumber = rv.getNumber();
            List<ProcessRecipe> tempPr = rv.getProcessRecipe();

            RecipeView rvAfter = new RecipeView();
            rvAfter.setId(tempId);
            rvAfter.setTitle(tempTitle);
            rvAfter.setType(tempType);
            rvAfter.setNumber(tempNumber);
            rvAfter.setProcessRecipe(tempPr);
            rvAfter.setJoinIngredient(tempJoinIngredientList);
            rvAfter.setDeleteFlag(AttributeConst.DEL_FLAG_FALSE.getIntegerValue());

            putRequestScope(AttributeConst.TOKEN, getTokenId()); //CSRF対策用トークン
            putRequestScope(AttributeConst.RECIPE, rvAfter); //取得したレシピデータ
            putRequestScope(AttributeConst.TOTAL_AMOUNT_RECI,totalAmountReci);
            putRequestScope(AttributeConst.TOTAL_ENERGY,totalEnergy);
            putRequestScope(AttributeConst.TOTAL_PRICE,totalPrice);
            List<AllergyItem> allergyItems = new ArrayList<AllergyItem>();
            putRequestScope(AttributeConst.ALGY_ITEMS, allergyItems);
            //詳細画面を表示
            forward(ForwardConst.FW_RECI_SHOW);
        }
    }

    /**
     * 編集画面を表示する
     * @throws ServletException
     * @throws IOException
     */
    public void edit() throws ServletException, IOException {

        //idを条件にレシピデータを取得する
        Recipe recipe = service.findOneInternal(toNumber(getRequestParam(AttributeConst.RECI_ID)));

        if (recipe == null || recipe.getDeleteFlag() == AttributeConst.DEL_FLAG_TRUE.getIntegerValue()) {
            //該当のレシピデータが存在しない場合はエラー画面を表示
            forward(ForwardConst.FW_ERR_UNKNOWN);
        }

        List<JoinIngredient> record = recipe.getJoinIngredient();//レシピからJoinIngredientのリストを取り出す
        List<JoinIngredient> tempJoinIngredient = new ArrayList<JoinIngredient>();//空のリスト

        for (int i = 0; i < 10 ; i++) {
            JoinIngredient tmp = new JoinIngredient();//データを1つずつ詰めるインスタンス
            if ( i <= (record.size() - 1 )) {
                tmp = record.get(i);//リストのi番目のデータをtmpに詰める
            }
            tempJoinIngredient.add(tmp);//リストに詰める
        }

        int tempId = recipe.getId();
        String tempTitle = recipe.getTitle();
        String tempType = recipe.getType();
        Double tempNumber = recipe.getNumber();
        List<ProcessRecipe> tempPr = recipe.getProcessRecipe();

        RecipeView rv = new RecipeView();
        rv.setId(tempId);
        rv.setTitle(tempTitle);
        rv.setType(tempType);
        rv.setNumber(tempNumber);
        rv.setProcessRecipe(tempPr);
        rv.setJoinIngredient(tempJoinIngredient);

        putRequestScope(AttributeConst.RECIPE, rv); //取得したレシピデータ
        putRequestScope(AttributeConst.TOKEN, getTokenId()); //CSRF対策用トークン
        List<Ingredient> ingredients = service.getAllIngredients();
        putRequestScope(AttributeConst.INGREDIENTS, ingredients);

        //編集画面を表示
        forward(ForwardConst.FW_RECI_EDIT);
    }


    /**
     * 更新を行う
     * @throws ServletException
     * @throws IOException
     */
    public void update() throws ServletException, IOException {

        //CSRF対策 tokenのチェック
        if (checkToken()) {
          //中間テーブルの食材リストのインスタンス生成
            List<JoinIngredient> ingrsAndAmountsList = new ArrayList<JoinIngredient>();
            String[] amounts = getRequestParams(AttributeConst.J_INGR_AMOUNT);
            String[] ingrs = getRequestParams(AttributeConst.J_INGR_INGREDIENT);
            String[] units = getRequestParams(AttributeConst.J_INGR_AMOUNT_U);
            for (int i = 0; i <10; i++) { //繰り返し
                // 0～9行目の食材IDを1件ずつ取得する
                String ingredientId = ingrs[i];
                String amount = amounts[i];
                String unit = units[i];
                // Ingredientのインスタンスを生成する
                Ingredient ingredientEntity = new Ingredient();
                //中間テーブルのインスタンス生成
                JoinIngredient j = new JoinIngredient();

                if (ingredientId == null || "noSelected".equals(ingredientId)) {
                    // 食材IDが未選択の場合、データを登録しない
                    continue;
                } else {
                    ingredientEntity.setId(toNumber(ingredientId));
                }
                if (amount == null || amount == "") {
                    continue;
                } else {
                    j.setAmount(Double.parseDouble(amount));
                }
                j.setAmount_u(unit);
                j.setIngredient(ingredientEntity);//JoinIngredientに食材名(ID)をセット
                ingrsAndAmountsList.add(j);
            }

                //idを条件にレシピデータを取得する
                RecipeView rv = service.findOne(toNumber(getRequestParam(AttributeConst.RECI_ID)));

                //入力されたレシピ内容を設定する
                rv.setTitle(getRequestParam(AttributeConst.RECI_TITLE));
                rv.setType(getRequestParam(AttributeConst.RECI_TYPE));
                rv.setNumber(getDoubleRequestParam(AttributeConst.RECI_NUMBER));
                rv.setJoinIngredient(ingrsAndAmountsList);
                AttributeConst.DEL_FLAG_FALSE.getIntegerValue();
                //レシピデータを更新する
                List<String> errors = service.update(rv);

                if (errors.size() > 0) {
                    //更新中にエラーが発生した場合

                    putRequestScope(AttributeConst.TOKEN, getTokenId()); //CSRF対策用トークン
                    putRequestScope(AttributeConst.RECIPE, rv); //入力されたレシピ情報
                    putRequestScope(AttributeConst.ERR, errors); //エラーのリスト

                    //編集画面を再表示
                    forward(ForwardConst.FW_RECI_EDIT);
                } else {
                    //更新中にエラーがなかった場合

                    //セッションに更新完了のフラッシュメッセージを設定
                    putSessionScope(AttributeConst.FLUSH, MessageConst.I_UPDATED.getMessage());

                    //一覧画面にリダイレクト
                    redirect(ForwardConst.ACT_RECI, ForwardConst.CMD_INDEX);

                }
            }
        }

    /**
     * レシピの論理削除を行う
     * @throws ServletException
     * @throws IOException
     */
    public void destroy() throws ServletException, IOException {

        //CSRF対策 tokenのチェック
        if (checkToken()) { //追記

            //idを条件にレシピデータを論理削除する
            service.destroy(toNumber(getRequestParam(AttributeConst.RECI_ID)));

            //セッションに削除完了のフラッシュメッセージを設定
            putSessionScope(AttributeConst.FLUSH, MessageConst.I_DELETED.getMessage());

            //一覧画面にリダイレクト
            redirect(ForwardConst.ACT_RECI, ForwardConst.CMD_INDEX);
        }
    }

    /**
     * レシピの数値計算を行う
     * @throws ServletException
     * @throws IOException
     */
    public void calculate() throws ServletException, IOException {

      //idを条件にレシピデータを取得する
        Recipe recipe = service.findOneInternal(toNumber(getRequestParam(AttributeConst.RECI_ID)));

        if (recipe == null || recipe.getDeleteFlag() == AttributeConst.DEL_FLAG_TRUE.getIntegerValue()) {
            //該当のレシピデータが存在しない場合はエラー画面を表示
            forward(ForwardConst.FW_ERR_UNKNOWN);
        }

        Double tempNumber = recipe.getNumber();//人数を取得
        Double inputNumber = Double.parseDouble(getRequestParam(AttributeConst.RECI_NUMBER));//入力された人数

        List<JoinIngredient> recordJi = recipe.getJoinIngredient();//レシピからJoinIngredientのリストを取り出す
        List<JoinIngredient> tempJoinIngredientList = new ArrayList<JoinIngredient>();//空のリスト

        double totalAmountReci_b = 0;
        double totalEnergy_b = 0;
        double totalPrice_b = 0;

        for (int i = 0; i < 10 ; i++) {
            JoinIngredient tmpJi = new JoinIngredient();//データを1つずつ詰めるインスタンス
            if ( i <= (recordJi.size() - 1 )) {
                tmpJi = recordJi.get(i);//リストのi番目のデータをtmpに詰める,tmpはリストから取り出した１つのデータ

                int tempJiId = tmpJi.getId();//取得してそのまま詰め直す
                Recipe tempRecipe = tmpJi.getRecipe();//取得してそのまま詰め直す
                String tempAmount_u = tmpJi.getAmount_u();//取得してそのまま詰め直す
                Double tempAmount = tmpJi.getAmount();
                Double CalculatedAmount_b = tempAmount / tempNumber * inputNumber;//再計算された分量
                Double CalculatedAmount = ((double)Math.round(CalculatedAmount_b * 10))/10; // 小数第二位で四捨五入

                tmpJi.setId(tempJiId);//JoinIngredientのインスタンスに格納
                tmpJi.setRecipe(tempRecipe);
                tmpJi.setAmount_u(tempAmount_u);
                tmpJi.setAmount(CalculatedAmount);

                Ingredient recordIngr = tmpJi.getIngredient();//tmpから食材情報を取り出す
                int tempIngrId = recordIngr.getId();//取得してそのまま詰め直す
                String tempIngrName = recordIngr.getName();//取得してそのまま詰め直す
                String tempMaker = recordIngr.getMaker();//取得してそのまま詰め直す
                List<Allergy> tempAllergy = recordIngr.getAllergy();//取得してそのまま詰め直す

                Double tempEnergy = recordIngr.getEnergy();
                Double CalculatedEnergy_b = tempEnergy / 100 * CalculatedAmount;//再計算されたエネルギー
                Double CalculatedEnergy = ((double)Math.round(CalculatedEnergy_b * 10))/10; // 小数第二位で四捨五入
                //1gあたりのエネルギー×使用分量

                Double tempPrice = recordIngr.getPrice();
                Double tempAmountIngr = recordIngr.getAmount();//食材の内容量
                Double CalculatedPrice_b = tempPrice / tempAmountIngr * CalculatedAmount;//再計算された単価
                Double CalculatedPrice = ((double)Math.round(CalculatedPrice_b * 10))/10; // 小数第二位で四捨五入
                //1gあたりの単価×使用分量

                Ingredient calcData = new Ingredient();//インスタンスにセット
                calcData.setEnergy(CalculatedEnergy);
                calcData.setPrice(CalculatedPrice);
                calcData.setId(tempIngrId);
                calcData.setName(tempIngrName);
                calcData.setMaker(tempMaker);
                calcData.setAllergy(tempAllergy);
                calcData.setDeleteFlag(AttributeConst.DEL_FLAG_FALSE.getIntegerValue());

                tmpJi.setIngredient(calcData);//JoinIngredientのインスタンスに格納

                tempJoinIngredientList.add(tmpJi);//JoinIngredientのリストに格納

                totalAmountReci_b = totalAmountReci_b + CalculatedAmount;//合計分量を足していく
                totalEnergy_b = totalEnergy_b + CalculatedEnergy;//合計カロリーを足していく
                totalPrice_b = totalPrice_b + CalculatedPrice;//合計金額を足していく
            }
        }
        Double totalAmountReci = ((double)Math.round(totalAmountReci_b * 10))/10; // 小数第二位で四捨五入
        Double totalEnergy = ((double)Math.round(totalEnergy_b * 10))/10; // 小数第二位で四捨五入
        Double totalPrice = ((double)Math.round(totalPrice_b * 10))/10; // 小数第二位で四捨五入

        int tempId = recipe.getId();
        String tempTitle = recipe.getTitle();
        String tempType = recipe.getType();
        List<ProcessRecipe> tempPr = recipe.getProcessRecipe();

        RecipeView rv = new RecipeView();
        rv.setId(tempId);
        rv.setTitle(tempTitle);
        rv.setType(tempType);
        rv.setNumber(inputNumber);
        rv.setProcessRecipe(tempPr);
        rv.setJoinIngredient(tempJoinIngredientList);
        rv.setDeleteFlag(AttributeConst.DEL_FLAG_FALSE.getIntegerValue());

        putRequestScope(AttributeConst.RECIPE, rv); //取得したレシピデータ
        putRequestScope(AttributeConst.TOTAL_AMOUNT_RECI,totalAmountReci);
        putRequestScope(AttributeConst.TOTAL_ENERGY,totalEnergy);
        putRequestScope(AttributeConst.TOTAL_PRICE,totalPrice);
        putRequestScope(AttributeConst.TOKEN, getTokenId()); //CSRF対策用トークン
        List<Ingredient> ingredients = service.getAllIngredients();
        putRequestScope(AttributeConst.INGREDIENTS, ingredients);

        forward(ForwardConst.FW_RECI_SHOW);
    }



/**
 * 以下工程操作
 *
 */



    /**
     * 工程作成画面を表示する
     * @throws ServletException
     * @throws IOException
     */
    public void entryNewProcess() throws ServletException, IOException {

        //idを条件にレシピデータを取得する
        RecipeView rv = service.findOne(toNumber(getRequestParam(AttributeConst.RECI_ID)));
        if (rv == null || rv.getDeleteFlag() == AttributeConst.DEL_FLAG_TRUE.getIntegerValue()) {
            //該当のレシピデータが存在しない場合はエラー画面を表示
            forward(ForwardConst.FW_ERR_UNKNOWN);

        } else {
            putRequestScope(AttributeConst.TOKEN, getTokenId()); //CSRF対策用トークン
            putRequestScope(AttributeConst.RECIPE, rv); //取得したレシピデータ
        // 編集画面でも利用するため、空のインスタンスを生成
        ProcessRecipeView pv = new ProcessRecipeView();
        // リクエストスコープにセット
        putRequestScope(AttributeConst.PROCESS_RECIPE, pv);

        //新規登録画面を表示
        forward(ForwardConst.FW_PR_NEW);
        }
    }

    /**
     * 工程詳細を表示する
     * @throws ServletException
     * @throws IOException
     */
    public void showProcess() throws ServletException, IOException {

        //idを条件にレシピデータを取得する
        RecipeView rv = service.findOne(toNumber(getRequestParam(AttributeConst.RECI_ID)));
        if (rv == null || rv.getDeleteFlag() == AttributeConst.DEL_FLAG_TRUE.getIntegerValue()) {
            //該当のレシピデータが存在しない場合はエラー画面を表示
            forward(ForwardConst.FW_ERR_UNKNOWN);

        } else {
            putRequestScope(AttributeConst.TOKEN, getTokenId()); //CSRF対策用トークン
            putRequestScope(AttributeConst.RECIPE, rv); //取得したレシピデータ
        //新規登録画面を表示
        forward(ForwardConst.FW_PR_SHOW);
        }
    }

    /**
     * 工程新規登録を行う
     * @throws ServletException
     * @throws IOException
     */
    public void createProcess() throws ServletException, IOException {

        //CSRF対策 tokenのチェック
        if (checkToken()) {
            List<ProcessRecipe> processes = new ArrayList<ProcessRecipe>();//空のリストを生成
            String[] descriptions = getRequestParams(AttributeConst.PR_DESCRIPTION);
            String[] utensils = getRequestParams(AttributeConst.PR_UTENSIL);
            String[] temps = getRequestParams(AttributeConst.PR_TEMPERATURE);
            String[] sps = getRequestParams(AttributeConst.PR_SPEED);
            String[] tms = getRequestParams(AttributeConst.PR_TIME);
            for (int i = 0; i < 9; i++) { //繰り返し
                // 0～9行目の食材IDを1件ずつ取得する
                String description = descriptions[i];
                String utensil = utensils[i];
                String temp = temps[i];
                String speed = sps[i];
                String time = tms[i];
                // ProcessRecipeのインスタンスを生成する
                ProcessRecipe prEntity = new ProcessRecipe();

                if (description == null || description == "") {
                    // 説明文が未入力の場合、データを登録しない
                    continue;  //繰り返しを飛ばして次の処理にうつる
                } else {
                    prEntity.setDescription(description);
                }

                if (utensil != null && !"noSelected".equals(utensil)) {
                    prEntity.setUtensil(utensil);
                }
                if (temp != null && !"".equals(temp)) {
                    prEntity.setTemperature(Double.parseDouble(temp));
                }
                if (speed != null && !"noSelected".equals(speed)) {
                    prEntity.setSpeed(speed);
                }
                if (time != null && !"".equals(time)) {
                    prEntity.setTime(Double.parseDouble(time));
                }
                prEntity.setDeleteFlag(AttributeConst.DEL_FLAG_FALSE.getIntegerValue());
                processes.add(prEntity);
              }
            String id = getRequestParam(AttributeConst.RECI_ID);
            service.createInternalPr(processes,id);

                putRequestScope(AttributeConst.TOKEN, getTokenId()); //CSRF対策用トークン
                putRequestScope(AttributeConst.RECIPE, processes);//入力されたレシピ情報

                //セッションに登録完了のフラッシュメッセージを設定
                putSessionScope(AttributeConst.FLUSH, MessageConst.I_REGISTERED.getMessage());
               //レシピの詳細画面に遷移
                String pram = AttributeConst.RECI_ID.getValue() + "=" + id;
                redirect(ForwardConst.ACT_RECI,ForwardConst.CMD_SHOW_PR, pram);

            }
        }

    /**
     * 工程編集画面を表示する
     * @throws ServletException
     * @throws IOException
     */
    public void editProcess() throws ServletException, IOException {
      //idを条件にレシピデータを取得する
        Recipe recipe = service.findOneInternal(toNumber(getRequestParam(AttributeConst.RECI_ID)));
        if (recipe == null || recipe.getDeleteFlag() == AttributeConst.DEL_FLAG_TRUE.getIntegerValue()) {
            //該当のレシピデータが存在しない場合はエラー画面を表示
            forward(ForwardConst.FW_ERR_UNKNOWN);
        }

        List<ProcessRecipe> record = recipe.getProcessRecipe();//レシピからProcessRecipeのリストを取り出す
        List<ProcessRecipe> tempProcessRecipe = new ArrayList<ProcessRecipe>();//空のリスト

        for (int i = 0; i < 10 ; i++) {
            ProcessRecipe tmp = new ProcessRecipe();//データを1つずつ詰めるインスタンス
            if ( i <record.size() ) {
                tmp = record.get(i);//リストのi番目のデータをtmpに詰める
            }
            tempProcessRecipe.add(tmp);//リストに詰める
        }
        int tempId = recipe.getId();
        String tempTitle = recipe.getTitle();

        RecipeView rv = new RecipeView();
        rv.setId(tempId);
        rv.setTitle(tempTitle);
        rv.setProcessRecipe(tempProcessRecipe);

        putRequestScope(AttributeConst.RECIPE, rv); //取得したレシピデータ
        putRequestScope(AttributeConst.TOKEN, getTokenId()); //CSRF対策用トークン
        //編集画面を表示
        forward(ForwardConst.FW_PR_EDIT);
        }

    /**
     * 工程更新を行う
     * @throws ServletException
     * @throws IOException
     */
    public void updateProcess() throws ServletException, IOException {

        //CSRF対策 tokenのチェック
        if (checkToken()) {
            List<ProcessRecipe> processes = new ArrayList<ProcessRecipe>();//空のリストを生成
            String[] descriptions = getRequestParams(AttributeConst.PR_DESCRIPTION);
            String[] utensils = getRequestParams(AttributeConst.PR_UTENSIL);
            String[] temps = getRequestParams(AttributeConst.PR_TEMPERATURE);
            String[] sps = getRequestParams(AttributeConst.PR_SPEED);
            String[] tms = getRequestParams(AttributeConst.PR_TIME);
            for (int i = 0; i < 9; i++) { //繰り返し
                // 0～9行目のデータを1件ずつ取得する
                String description = descriptions[i];
                String utensil = utensils[i];
                String temp = temps[i];
                String speed = sps[i];
                String time = tms[i];
                // ProcessRecipeのインスタンスを生成する
                ProcessRecipe prEntity = new ProcessRecipe();

                if (description == null || description == "") {
                    // 説明文が未入力の場合、データを登録しない
                    continue;  //繰り返しを飛ばして次の処理にうつる
                } else {
                    prEntity.setDescription(description);
                }

                if (utensil != null && !"noSelected".equals(utensil)) {
                    prEntity.setUtensil(utensil);
                }
                if (temp != null && !"".equals(temp)) {
                    prEntity.setTemperature(Double.parseDouble(temp));
                }
                if (speed != null && !"noSelected".equals(speed)) {
                    prEntity.setSpeed(speed);
                }
                if (time != null && !"".equals(time)) {
                    prEntity.setTime(Double.parseDouble(time));
                }
                prEntity.setDeleteFlag(AttributeConst.DEL_FLAG_FALSE.getIntegerValue());
                processes.add(prEntity);
              }
            String id = getRequestParam(AttributeConst.RECI_ID);
            service.updateInternalPr(processes,id);

            putRequestScope(AttributeConst.TOKEN, getTokenId()); //CSRF対策用トークン
            putRequestScope(AttributeConst.RECIPE, processes);//入力されたレシピ情報

            //セッションに登録完了のフラッシュメッセージを設定
            putSessionScope(AttributeConst.FLUSH, MessageConst.I_UPDATED.getMessage());
            //工程の詳細画面に遷移
            String pram = AttributeConst.RECI_ID.getValue() + "=" + id;
            redirect(ForwardConst.ACT_RECI,ForwardConst.CMD_SHOW_PR, pram);
            }
        }

    /**
     * 工程削除を行う
     * @throws ServletException
     * @throws IOException
     */
    public void destroyProcess() throws ServletException, IOException {

        //CSRF対策 tokenのチェック
        if (checkToken()) { //追記
        String id = getRequestParam(AttributeConst.RECI_ID);
        service.destroyPr(id);

        //セッションに削除完了のフラッシュメッセージを設定
        putSessionScope(AttributeConst.FLUSH, MessageConst.I_DELETED.getMessage());

        //工程の詳細画面に遷移
        String pram = AttributeConst.RECI_ID.getValue() + "=" + id;
        redirect(ForwardConst.ACT_RECI,ForwardConst.CMD_INDEX, pram);

        }
    }
    public void search() throws ServletException, IOException {
        //検索したタイトルに該当するレシピを取得
        int page = getPage();
        String search = getRequestParam(AttributeConst.SEARCH);
        //検索したタイトルに該当するレシピデータを、指定されたページ数の一覧画面に表示する分取得する
        List<RecipeView> recipes = service.getTitleBySearchPerPage(search, page);
        //検索したタイトルに該当するレシピデータの件数を取得
        long myRecipesCount = service.countTitleBySearch(search);
        putRequestScope(AttributeConst.RECIPES, recipes); //タイトルをキーに取得したレシピデータ
        putRequestScope(AttributeConst.RECI_COUNT, myRecipesCount); //検索したレシピの数
        putRequestScope(AttributeConst.PAGE, page); //ページ数
        putRequestScope(AttributeConst.MAX_ROW, JpaConst.ROW_PER_PAGE); //1ページに表示するレコードの数
        //一覧画面を表示
        forward(ForwardConst.FW_RECI_INDEX);
    }
}
