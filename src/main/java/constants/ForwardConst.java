package constants;

/**
 * リクエストパラメーターの変数名、変数値、jspファイルの名前等画面遷移に関わる値を定義するEnumクラス
 *
 */
public enum ForwardConst {

    //action
    ACT("action"),
    ACT_TOP("Top"),
    ACT_EMP("Employee"),
    ACT_RECI("Recipe"),
    ACT_PR("ProcessRecipe"),
    ACT_UT("Utensil"),
    ACT_INGR("Ingredient"),
    ACT_EQ("Equipment"),
    ACT_ORDER("Order"),
    ACT_ODH("OderHistory"),
    ACT_AUTH("Auth"),
    ACT_SEARCH("Search"),
    ACT_UNFINISHED("Unfinished"),

    //command
    CMD("command"),
    CMD_NONE(""),
    CMD_INDEX("index"),
    CMD_INDEX_ODH("indexOrderHistory"),
    CMD_SHOW("show"),
    CMD_SHOW_INGR("showIngr"),
    CMD_SHOW_EQ("showEq"),
    CMD_SHOW_PR("showProcess"),
    CMD_SHOW_LOGIN("showLogin"),
    CMD_SHOW_ORDER("showOrder"),
    CMD_SHOW_ODH("showOrderHistory"),
    CMD_LOGIN("login"),
    CMD_LOGOUT("logout"),
    CMD_NEW("entryNew"),
    CMD_NEW_PR("entryNewProcess"),
    CMD_CREATE("create"),
    CMD_CREATE_PR("createProcess"),
    CMD_EDIT("edit"),
    CMD_EDIT_PR("editProcess"),
    CMD_UPDATE("update"),
    CMD_UPDATE_PR("updateProcess"),
    CMD_DESTROY("destroy"),
    CMD_DESTROY_PR("destroyProcess"),
    CMD_SEARCH("search"),
    CMD_PROCESS("process"),
    CMD_CALCULATE("calculate"),


    //jsp（フォルダ名/jsp名 ）
    FW_ERR_UNKNOWN("error/unknown"),
    FW_ERR_UNFINISHED("error/unfinished"),
    FW_TOP_INDEX("topPage/index"),
    FW_LOGIN("login/login"),

    FW_EMP_INDEX("employees/index"),
    FW_EMP_SHOW("employees/show"),
    FW_EMP_NEW("employees/new"),
    FW_EMP_EDIT("employees/edit"),

    FW_RECI_INDEX("recipes/index"),
    FW_RECI_SHOW("recipes/show"),
    FW_RECI_NEW("recipes/new"),
    FW_RECI_EDIT("recipes/edit"),

    FW_PR_NEW("processes/new"),
    FW_PR_SHOW("processes/show"),
    FW_PR_EDIT("processes/edit"),

    FW_INGR_INDEX("ingredients/index"),
    FW_INGR_SHOW("ingredients/show"),
    FW_INGR_NEW("ingredients/new"),
    FW_INGR_EDIT("ingredients/edit"),

    FW_EQ_INDEX("equipments/index"),
    FW_EQ_SHOW("equipments/show"),
    FW_EQ_NEW("equipments/new"),
    FW_EQ_EDIT("equipments/edit"),

    FW_ORDER_INDEX("orders/index"),
    FW_ORDER_SHOW("orders/show"),
    FW_ORDER_SHOW_INGR("orders/showIngr"),
    FW_ORDER_SHOW_EQ("orders/showEq"),
    FW_ORDER_INDEX_ODH("orders/indexOrderHistory"),
    FW_ORDER_SHOW_ODH("orders/showOrderHistory");



    /**
     * 文字列
     */
    private final String text;

    /**
     * コンストラクタ
     */
    private ForwardConst(final String text) {
        this.text = text;
    }

    /**
     * 値(文字列)取得
     */
    public String getValue() {
        return this.text;
    }

}
