package constants;

/**
 * 各出力メッセージを定義するEnumクラス
 *
 */
public enum MessageConst {

    //認証
    I_LOGINED("ログインしました"),
    E_LOGINED("ログインに失敗しました。"),
    I_LOGOUT("ログアウトしました。"),

    //DB更新
    I_REGISTERED("登録が完了しました。"),
    I_ORDERED("注文が完了しました。"),
    I_UPDATED("更新が完了しました。"),
    I_DELETED("削除が完了しました。"),

    //バリデーション
    E_NONAME("氏名を入力してください。"),
    E_NO_RECI_TITLE("レシピ名を入力してください。"),
    E_NO_RECI_NUMBER("人数を入力してください。"),
    E_NO_RECI_J_INGR("食材情報を入力してください。"),
    E_NO_PR_DESCRIPTION("工程説明文を入力してください。"),
    E_NO_UT_NAME("調理器具名を入力してください。"),
    E_NO_INGR_NAME("食材名を入力してください。"),
    E_NO_ALGY_ITEM("アレルギー品目を入力してください。"),
    E_NO_EQ_NAME("備品名を入力してください。"),
    E_NOPASSWORD("パスワードを入力してください。"),
    E_NOEMP_CODE("社員番号を入力してください。"),
    E_EMP_CODE_EXIST("入力された社員番号の情報は既に存在しています。"),
    E_NOTITLE("タイトルを入力してください。"),
    E_NOCONTENT("内容を入力してください。");



    /**
     * 文字列
     */
    private final String text;

    /**
     * コンストラクタ
     */
    private MessageConst(final String text) {
        this.text = text;
    }

    /**
     * 値(文字列)取得
     */
    public String getMessage() {
        return this.text;
    }
}