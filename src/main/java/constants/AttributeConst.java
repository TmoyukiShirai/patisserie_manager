package constants;

/**
 * 画面の項目値等を定義するEnumクラス
 *
 */
public enum AttributeConst {

    //フラッシュメッセージ
    FLUSH("flush"),

    //一覧画面共通
    MAX_ROW("maxRow"),
    PAGE("page"),

    //入力フォーム共通
    TOKEN("_token"),
    ERR("errors"),

    //ログイン中の従業員
    LOGIN_EMP("login_employee"),

    //ログイン画面
    LOGIN_ERR("loginError"),

    //従業員管理
    EMPLOYEE("employee"),
    EMPLOYEES("employees"),
    EMP_COUNT("employees_count"),
    EMP_ID("id"),
    EMP_CODE("code"),
    EMP_PASS("password"),
    EMP_NAME("name"),
    EMP_ADMIN_FLG("admin_flag"),

    //管理者フラグ
    ROLE_ADMIN(1),
    ROLE_GENERAL(0),

    //削除フラグ
    DEL_FLAG_TRUE(1),
    DEL_FLAG_FALSE(0),

    //レシピ管理
    CHECK("check"),
    SEARCH("search"),
    RECIPE("recipe"),
    RECIPES("recipes"),
    RECI_COUNT("recipes_count"),
    RECI_ID("id"),
    RECI_TITLE("title"),
    RECI_TYPE("type"),
    RECI_NUMBER("number"),
    RECI_J_INGR("joinIngredient"),
    RECI_INGREDIENT("ingredient"),
    RECI_PR("processRecipe"),
    RECI_AMOUNT("amount"),
    RECI_AMOUNT_U("amount_u"),
    TOTAL_AMOUNT_RECI("totalAmountReci"),
    TOTAL_ENERGY("totalEnergy"),
    TOTAL_PRICE("totalPrice"),

    //中間テーブル（レシピ・食材）
    JOIN_INGREDIENT("joinIngredient"),
    J_INGR_ID("id"),
    J_INGR_RECIPE("recipe"), //レシピID
    J_INGR_INGREDIENT("ingredient"), //食材ID
    J_INGR_AMOUNT("amount"), //分量
    J_INGR_AMOUNT_U("amount_u"), //単位
    J_INGR_INGREDIENTS("j_ingr_ingredients"),

    //工程
    PROCESS_RECIPE("processRecipe"),
    PR_ID("id"),
    PR_R_ID("r_id"),
    PR_R_TITLE("r_title"),
    PR_DESCRIPTION("description"),
    PR_UTENSIL("utensil"),
    PR_TEMPERATURE("temperature"),
    PR_SPEED("speed"),
    PR_TIME("time"),
    PROCESSES("processes"),

    //食材
    INGREDIENT("ingredient"),
    INGREDIENTS("ingredients"),
    INGR_COUNT("ingredients_count"),
    INGR_ID("id"),
    INGR_NAME("name"),
    INGR_MAKER("maker"),
    INGR_SALER("saler"),
    INGR_PRICE("price"),
    INGR_AMOUNT("amount"),
    INGR_AMOUNT_U("amount_u"),
    INGR_ENERGY("energy"),
    INGR_ALLERGY("allergy"),

    //食材メーカー、販売店
    TAKANASHI("タカナシ乳業"),
    MEIJI("明治乳業"),
    NIHONSEIHUN("日本製粉"),
    YAMAUTIBOUEKI("山内貿易"),
    YAMAUTINOUJOU("山内農場"),
    OTHER("その他"),
    KARYO("カリョー"),
    HUKUYAMAYAOYA("福山八百屋"),
    NAGANOBUSSAN("永野物産"),
    SANRITSU("サンリツ"),
    KOKUYO("KOKUYO"),
    DAIOUSEISHI("大王製紙"),
    LION("ライオン"),
    WATANABESYOUTEN("渡辺商店"),
    //レシピ分類リスト
    CREAM("クリーム"),
    KIJI("生地"),
    MOUSE("ムース"),
    YAKIGASHI("焼き菓子"),
    CONFITURE("コンフィチュール"),
    //調理器具
    TENABE("手鍋"),
    DOUNABE("銅鍋"),
    MIXER("卓上ミキサー"),
    MIXER30("30コートミキサー"),
    OVEN("オーブン"),

    //食材内容量の単位
    G("g"),
    ML("ml"),
    HONN("本"),
    KO("個"),
    MAI("枚"),

    //アレルギー
    ALLERGY("allergy"),
    ALGY_ID("id"),
    ALGY_ITEM("item"),
    ITEM_NAME("itemName"),
    ALGY_ITEMS("allergy_items"),

    //備品
    EQUIPMENT("equipment"),
    EQUIPMENTS("equipments"),
    EQ_COUNT("equipments_count"),
    EQ_ID("id"),
    EQ_NAME("name"),
    EQ_MAKER("maker"),
    EQ_SALER("saler"),
    EQ_PRICE("price"),
    EQ_AMOUNT("amount"),
    EQ_AMOUNT_U("amount_u"),

    //発注履歴中間テーブル
     J_ODHS("JoinOrderHistories"),
     J_ODHS_COUNT("JoinOrderHistories_count"),
     J_ODH_ID("id"),
     J_ODH_ODH("orderHistory"),
     J_ODH_SALER("saler"),
     J_ODH_ODD_EMP("orderedEmp"),
     J_ODH_ODD_DATE("orderedDate"),

    //発注
    ORDER_DATE_1("orderDate1"),
    ORDER_DATE_2("orderDate2"),
    DELI_DATE("deliDate"),

    NUM_OF_ORDER("numberOfOrder"),
    //発注履歴
    ORDER_HISTORY("orderHistory"),
    ODHS("orderHistories"),
    ODHS_COUNT("orderHistories_count"),
    ODH_ID("id"),
    ODH_ODD_DATE("orderedDate"),
    ODH_DELI_DATE("deliDate"),
    ODH_INGREDIENT("ingredient"),
    ODH_NUM_OF_ORDER("numberOfOrder"),
    ODH_PRICE("price"),
    ODH_TOTAL_PRICE("totalPrice"),
    ODH_ODD_EMP("orderedEmp"),

    //発注書未入力
    NUM_ERR("numError");

    private final String text;
    private final Integer i;
    private final Double d;

    private AttributeConst(final String text) {
        this.text = text;
        this.i = null;
        this.d = null;
    }

    private AttributeConst(final Integer i) {
        this.text = null;
        this.i = i;
        this.d = null;
    }

    private AttributeConst(final Double d) {
        this.text = null;
        this.i = null;
        this.d = d;
    }

    public String getValue() {
        return this.text;
    }

    public Integer getIntegerValue() {
        return this.i;
    }

    public Double getDoubleValue() {
        return this.d;
    }

}