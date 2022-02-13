package constants;

/**
 * DB関連の項目値を定義するインターフェース
 * ※インターフェイスに定義した変数は public static final 修飾子がついているとみなされる
 */
public interface JpaConst {

    //persistence-unit名
    String PERSISTENCE_UNIT_NAME = "patisserie_manager";

    //データ取得件数の最大値
    int ROW_PER_PAGE = 15; //1ページに表示するレコードの数

    //従業員テーブル
    String TABLE_EMP = "employees"; //テーブル名
    //従業員テーブルカラム
    String EMP_COL_ID = "id"; //id
    String EMP_COL_CODE = "code"; //社員番号
    String EMP_COL_NAME = "name"; //氏名
    String EMP_COL_PASS = "password"; //パスワード
    String EMP_COL_ADMIN_FLAG = "admin_flag"; //管理者権限
    String EMP_COL_CREATED_AT = "created_at"; //登録日時
    String EMP_COL_UPDATED_AT = "updated_at"; //更新日時
    String EMP_COL_DELETE_FLAG = "delete_flag"; //削除フラグ

    int ROLE_ADMIN = 1; //管理者権限ON(管理者)
    int ROLE_GENERAL = 0; //管理者権限OFF(一般)
    int EMP_DEL_TRUE = 1; //削除フラグON(削除済み)
    int EMP_DEL_FALSE = 0; //削除フラグOFF(現役)

    //レシピテーブル
    String TABLE_RECI = "recipes"; //テーブル名
    //レシピテーブルカラム
    String RECI_COL_ID = "id"; //id
    String RECI_COL_TITLE = "title"; //レシピのタイトル
    String RECI_COL_TYPE = "type"; //レシピの分類
    String RECI_COL_NUMBER = "number"; //レシピの人数
    String RECI_COL_J_INGREDIENT = "joinIngredient"; //レシピの食材
    String RECI_COL_PR = "processRecipe";
    String RECI_COL_AMOUNT = "amount"; //レシピの分量
    String RECI_COL_AMOUNT_U = "amount_u"; //レシピの単位
    String RECI_COL_DELETE_FLAG = "delete_flag"; //削除フラグ
    int RECI_DEL_TRUE = 1; //削除フラグON(削除済み)
    int RECI_DEL_FALSE = 0; //削除フラグOFF

    //中間テーブル（レシピ・食材・分量）
    String TABLE_J_INGR = "joinIngredient";
    //カラム
    String J_INGR_COL_ID = "id";
    String J_INGR_COL_RECIPE = "recipe"; //レシピID
    String J_INGR_COL_INGREDIENT = "ingredient"; //食材ID
    String J_INGR_COL_AMOUNT = "amount"; //分量
    String J_INGR_COL_AMOUNT_U = "amount_u"; //単位

    //工程テーブル
    String TABLE_PR = "processRecipe";
    //工程テーブルカラム
    String PR_COL_ID = "id";
    String PR_COL_RECIPE= "recipe";//レシピ
    String PR_COL_R_ID= "r_id";//レシピ名のID
    String PR_COL_R_TITLE= "r_title";//レシピ名
    String PR_COL_DESCRIPTION = "description";//工程説明文
    String PR_COL_UTENSIL = "utensil";//調理器具
    String PR_COL_TEMPERATURE = "temperature";//温度
    String PR_COL_SPEED = "speed";//速度
    String PR_COL_TIME = "time";//時間
    String PR_COL_DELETE_FLAG = "delete_flag"; //削除フラグ
    int PR_DEL_TRUE = 1; //削除フラグON(削除済み)
    int PR_DEL_FALSE = 0; //削除フラグOFF

    //食材テーブル
    String TABLE_INGR = "ingredients"; //食材名
    //食材テーブルカラム
    String INGR_COL_ID = "id";
    String INGR_COL_RECIPE = "recipe";
    String INGR_COL_NAME= "name";
    String INGR_COL_MAKER = "maker";
    String INGR_COL_SALER = "saler";
    String INGR_COL_PRICE = "price";
    String INGR_COL_AMOUNT = "amount";
    String INGR_COL_AMOUNT_U = "amount_u";
    String INGR_COL_ENERGY = "energy";
    String INGR_COL_ALLERGY = "allergy";
    String INGR_COL_DELETE_FLAG = "delete_flag"; //削除フラグ
    int INGR_DEL_TRUE = 1; //削除フラグON(削除済み)
    int INGR_DEL_FALSE = 0; //削除フラグOFF

    //アレルギーテーブル
    String TABLE_ALGY = "allergy";
    //アレルギーテーブルカラム
    String ALGY_COL_ID = "id";
    String ALGY_COL_INGREDIENT= "ingredient";//食材
    String ALGY_COL_I_ID= "i_id";//食材名のID
    String ALGY_COL_I_NAME= "i_name";//食材名
    String ALGY_COL_ITEM = "item";//アレルギー品目

    //アレルギー選択肢テーブル
    String TABLE_ALGYITEM = "allergy_item";
    //アレルギーテーブルカラム
    String ALGYITEM_COL_ID = "id";
    String ALGYITEM_COL_ITEMNAME = "item_name";//アレルギー品目

    //備品テーブル
    String TABLE_EQ = "equipments"; //備品名
    //備品テーブルカラム
    String EQ_COL_ID = "id";
    String EQ_COL_NAME= "name";
    String EQ_COL_MAKER = "maker";
    String EQ_COL_SALER = "saler";
    String EQ_COL_PRICE = "price";
    String EQ_COL_AMOUNT = "amount";
    String EQ_COL_AMOUNT_U = "amount_u";
    String EQ_COL_DELETE_FLAG = "delete_flag"; //削除フラグ
    int EQ_DEL_TRUE = 1; //削除フラグON(削除済み)
    int EQ_DEL_FALSE = 0; //削除フラグOFF

    //発注履歴中間テーブル
    String TABLE_J_ODH = "JoinOrderHistories"; //発注履歴名
    //発注履歴中間テーブルカラム
    String J_ODH_COL_ID = "id";
    String J_ODH_COL_ODH = "orderHistory";
    String J_ODH_COL_SALER = "saler";
    String J_ODH_COL_ODD_EMP = "orderedEmp";
    String J_ODH_COL_ODD_DATE = "orderedDate";

    //発注履歴テーブル
    String TABLE_ODH = "orderHistories"; //発注履歴
    //発注履歴テーブルカラム
    String ODH_COL_ID = "id";
    String ODH_COL_J_ODH = "joinOrderHistory";
    String ODH_COL_ODD_DATE = "orderedDate"; //発注日
    String ODH_COL_DELI_DATE = "deliDate"; //納品日
    String ODH_COL_INGREDIENT = "ingredient";
    String ODH_COL_NUM_OF_ORDER = "numberOfOrder";
    String ODH_COL_PRICE = "price";//発注金額
    String ODH_COL_ODD_EMP = "orderedEmp";//発注者

    //Entity名
    String ENTITY_EMP = "employee"; //従業員
    String ENTITY_RECI = "recipe"; //レシピ
    String ENTITY_PR = "processRecipe"; //工程
    String ENTITY_J_INGR = "joinIngredient"; //中間テーブル
    String ENTITY_INGR = "ingredient"; //食材
    String ENTITY_ALGY = "allergy"; //アレルギー
    String ENTITY_ALGYITEM = "allergy_item"; //アレルギー選択肢
    String ENTITY_EQ = "equipment"; //備品
    String ENTITY_POI = "perchaseOrder"; //発注書（食材）
    String ENTITY_J_ODH = "joinOrderHistory"; //発注履歴
    String ENTITY_ODH = "orderHistory"; //発注履歴
    //JPQL内パラメータ
    String JPQL_PARM_CODE = "code"; //社員番号
    String JPQL_PARM_PASSWORD = "password"; //パスワード
    String JPQL_PARM_EMPLOYEE = "employee"; //従業員
    String JPQL_PARM_TITLE = "title"; //レシピ名
    String JPQL_PARM_TYPE = "type"; //レシピ分類
    String JPQL_PARM_RECIPE = "recipe"; //レシピid（工程テーブル）
    String JPQL_PARM_NAME = "name"; //食材名（食材テーブル）
    String JPQL_PARM_SALER = "saler"; //取扱店
    String JPQL_PARM_INGREDIENT = "ingredient"; //食材名（アレルギーテーブル）
    String JPQL_PARM_ITEM = "item"; //アレルギー品目
    String JPQL_PARM_EQ_NAME = "eq_name"; //備品名
    String JPQL_PARM_ODH_ODD_DATE = "orderedDate"; //発注日

    //NamedQueryの nameとquery
    //★EMPLOYEE
    //全ての従業員をidの降順に取得する
    String Q_EMP_GET_ALL = ENTITY_EMP + ".getAll"; //name
    String Q_EMP_GET_ALL_DEF = "SELECT e FROM Employee AS e ORDER BY e.id DESC"; //query
    //全ての従業員の件数を取得する
    String Q_EMP_COUNT = ENTITY_EMP + ".count";
    String Q_EMP_COUNT_DEF = "SELECT COUNT(e) FROM Employee AS e";
    //社員番号とハッシュ化済パスワードを条件に未削除の従業員を取得する
    String Q_EMP_GET_BY_CODE_AND_PASS = ENTITY_EMP + ".getByCodeAndPass";
    String Q_EMP_GET_BY_CODE_AND_PASS_DEF = "SELECT e FROM Employee AS e WHERE e.deleteFlag = 0 AND e.code = :" + JPQL_PARM_CODE + " AND e.password = :" + JPQL_PARM_PASSWORD;
    //指定した社員番号を保持する従業員の件数を取得する
    String Q_EMP_COUNT_RESISTERED_BY_CODE = ENTITY_EMP + ".countRegisteredByCode";
    String Q_EMP_COUNT_RESISTERED_BY_CODE_DEF = "SELECT COUNT(e) FROM Employee AS e WHERE e.code = :" + JPQL_PARM_CODE;


    //★RECIPE
    //全てのレシピをidの降順に取得する
    String Q_RECI_GET_ALL = ENTITY_RECI + ".getAll";
    String Q_RECI_GET_ALL_DEF = "SELECT r FROM Recipe AS r WHERE r.deleteFlag = 0" + "ORDER BY r.id DESC";
    //全てのレシピの件数を取得する
    String Q_RECI_COUNT = ENTITY_RECI + ".count";
    String Q_RECI_COUNT_DEF = "SELECT COUNT(r) FROM Recipe AS r";


    //検索したタイトルに該当するレシピを全件idの降順で取得する
    String Q_RECI_GET_TITLE_BY_SEARCH = ENTITY_RECI + ".getTitleBySearch";
    String Q_RECI_GET_TITLE_BY_SEARCH_DEF = "SELECT r FROM Recipe AS r WHERE r.title LIKE : " + JPQL_PARM_TITLE + " ORDER BY r.id DESC";
    //検索したタイトルに該当するレシピの件数を取得する
    String Q_RECI_COUNT_TITLE_BY_SEARCH = ENTITY_RECI + ".countTitleBySearch";
    String Q_RECI_COUNT_TITLE_BY_SEARCH_DEF = "SELECT COUNT(r) FROM Recipe AS r WHERE r.title LIKE : " + JPQL_PARM_TITLE;
    //検索した分類に該当するレシピを全件idの降順で取得する
    String Q_RECI_GET_TYPE_BY_SEARCH = ENTITY_RECI + ".getTypeBySearch";
    String Q_RECI_GET_TYPE_BY_SEARCH_DEF = "SELECT r FROM Recipe AS r WHERE r.type LIKE : " + JPQL_PARM_TYPE + " ORDER BY r.id DESC";
    //検索した分類に該当するレシピの件数を取得する
    String Q_RECI_COUNT_TYPE_BY_SEARCH = ENTITY_RECI + ".countTypeBySearch";
    String Q_RECI_COUNT_TYPE_BY_SEARCH_DEF = "SELECT COUNT(r) FROM Recipe AS r WHERE r.type LIKE : " + JPQL_PARM_TYPE;
    //レシピIDに一致する食材データを削除する（更新の時、食材データのみ、一旦既存のデータを削除して再登録する）
    String Q_RECI_INGR_DELETE = ENTITY_RECI + ".reciIngrDelete";
    String Q_RECI_INGR_DELETE_DEF = "DELETE FROM JoinIngredient AS a WHERE a.recipe = :" + JPQL_PARM_RECIPE ;
    //レシピIDに一致する工程データを削除する（更新の時、工程データのみ、一旦既存のデータを削除して再登録する）
    String Q_RECI_PR_DELETE = ENTITY_RECI + ".reciPrDelete";
    String Q_RECI_PR_DELETE_DEF = "DELETE FROM ProcessRecipe AS a WHERE a.recipe = :" + JPQL_PARM_RECIPE ;

    //工程
    //全てのレシピの工程表リストを取得する
    String Q_PR_GET_ALL = ENTITY_PR + ".getAllProcesses";
    String Q_PR_GET_ALL_DEF = "SELECT p FROM ProcessRecipe AS p WHERE p.deleteFlag = 0" + " ORDER BY p.id DESC";
    //指定したレシピの工程表リストを取得する
    String Q_PR_GET_ALL_MINE = ENTITY_PR + ".getAllMine";
    String Q_PR_GET_ALL_MINE_DEF = "SELECT p FROM ProcessRecipe AS p WHERE p.recipe = :" + JPQL_PARM_RECIPE + " ORDER BY p.id DESC";
    //                                                  ↑Entity名は頭大文字       ↑フィールド名なのでキャメルケースでの表記(AllegyEntity内のrecipeフィールド)

    //中間テーブルJoinIngredient(amount,amount_u)
    //全ての情報(レシピid,食材id,amount,amount_u)をidの降順に取得する
    String Q_J_INGR_GET_ALL = ENTITY_J_INGR + ".getAll";
    String Q_J_INGR_GET_ALL_DEF = "SELECT i FROM JoinIngredient AS i ORDER BY i.id DESC";
    //指定したレシピの食材情報(レシピid,食材id,amount,amount_u)をidの降順に取得する
    String Q_J_INGR_GET_ITS = ENTITY_J_INGR + ".getItsJoinIngredients";
    String Q_J_INGR_GET_ITS_DEF = "SELECT j FROM JoinIngredient AS j WHERE j.recipe = :" + JPQL_PARM_RECIPE + " ORDER BY j.id DESC";

    //★INGREDIENT
    //全ての食材をidの降順に取得する(削除していないもののみ）
    String Q_INGR_GET_ALL = ENTITY_INGR + ".getAll";
    String Q_INGR_GET_ALL_DEF = "SELECT i FROM Ingredient AS i WHERE i.deleteFlag = 0" + "ORDER BY i.id DESC";
    //全ての食材の件数を取得する                                                 ↑フィールド名はキャメルケースで
    String Q_INGR_COUNT = ENTITY_INGR + ".count";
    String Q_INGR_COUNT_DEF = "SELECT COUNT(i) FROM Ingredient AS i";
    //検索した食材名に該当する食材を全件idの降順で取得する
    String Q_INGR_GET_NAME_BY_SEARCH = ENTITY_INGR + ".getNameBySearch";
    String Q_INGR_GET_NAME_BY_SEARCH_DEF = "SELECT i FROM Ingredient AS i WHERE i.name LIKE : " + JPQL_PARM_NAME + " ORDER BY i.id DESC";
//  　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　↑Entity名は頭大文字     ↑フィールド名なのでキャメルケースでの表記(AllegyEntity内のingredientフィールド)
    //検索した食材名に該当する食材の件数を取得する
    String Q_INGR_COUNT_NAME_BY_SEARCH = ENTITY_INGR + ".countNameBySearch";
    String Q_INGR_COUNT_NAME_BY_SEARCH_DEF = "SELECT COUNT(i) FROM Ingredient AS i WHERE i.name LIKE : " + JPQL_PARM_NAME;
    //検索した販売店に該当する食材を全件idの降順で取得する
    String Q_INGR_GET_SALER_BY_SEARCH = ENTITY_INGR + ".getSalerBySearch";
    String Q_INGR_GET_SALER_BY_SEARCH_DEF = "SELECT i FROM Ingredient AS i WHERE i.saler LIKE : " + JPQL_PARM_SALER + " ORDER BY i.id DESC";
    //検索した販売店に該当する食材の件数を取得する
    String Q_INGR_COUNT_SALER_BY_SEARCH = ENTITY_INGR + ".countSalerBySearch";
    String Q_INGR_COUNT_SALER_BY_SEARCH_DEF = "SELECT COUNT(i) FROM Ingredient AS i WHERE i.saler LIKE : " + JPQL_PARM_SALER;
    //アレルギーデータを削除する（更新の時、アレルギーデータのみ、一旦既存のデータを削除して再登録する）
    String Q_INGR_ALGY_DELETE = ENTITY_ALGY + ".ingrAlgyDelete";
    String Q_INGR_ALGY_DELETE_DEF = "DELETE FROM Allergy AS a WHERE a.ingredient = :" + JPQL_PARM_INGREDIENT ;

    // アレルギーの選択肢
    String Q_ALGYITME_GET_ALL = ENTITY_ALGYITEM + ".getAll";
    // 全てのアレルギーの選択肢をidの昇順に取得する
    String Q_ALGYITME_GET_ALL_DEF = "SELECT a FROM AllergyItem AS a ORDER BY a.id";

    //★EQUIPMENT
    //全ての備品をidの降順に取得する
    String Q_EQ_GET_ALL = ENTITY_EQ + ".getAll";
    String Q_EQ_GET_ALL_DEF = "SELECT e FROM Equipment AS e WHERE e.deleteFlag = 0" + "ORDER BY e.id DESC";
    //全ての備品の件数を取得する
    String Q_EQ_COUNT = ENTITY_EQ + ".count";
    String Q_EQ_COUNT_DEF = "SELECT COUNT(e) FROM Equipment AS e";
    //検索した備品名に該当する備品を全件idの降順で取得する
    String Q_EQ_GET_NAME_BY_SEARCH = ENTITY_EQ + ".getNameBySearch";
    String Q_EQ_GET_NAME_BY_SEARCH_DEF = "SELECT e FROM Equipment AS e WHERE e.name LIKE : " + JPQL_PARM_NAME + " ORDER BY e.id DESC";
    //検索した備品名に該当する備品の件数を取得する
    String Q_EQ_COUNT_NAME_BY_SEARCH = ENTITY_EQ + ".countNameBySearch";
    String Q_EQ_COUNT_NAME_BY_SEARCH_DEF = "SELECT COUNT(e) FROM Equipment AS e WHERE e.name LIKE : " + JPQL_PARM_NAME;

    //発注書のデータ取得
    //指定した販売店に該当する食材を全件idの降順で取得する
    String Q_INGRS_GET_ALL_SALER= ENTITY_INGR + ".getAllIngrsOfSaler";
    String Q_INGRS_GET_ALL_SALER_DEF = "SELECT i FROM Ingredient AS i WHERE i.saler = : " + JPQL_PARM_SALER + " ORDER BY i.id DESC";
    //指定した販売店に該当する食材の件数を取得する
    String Q_INGRS_COUNT_ALL_SALER= ENTITY_INGR + ".countAllIngrsOfSaler";
    String Q_INGRS_COUNT_ALL_SALER_DEF = "SELECT COUNT(i) FROM Ingredient AS i WHERE i.saler = : " + JPQL_PARM_SALER;

    //指定した販売店に該当する備品を全件idの降順で取得する
    String Q_EQS_GET_ALL_SALER= ENTITY_EQ + ".getAllEqsOfSaler";
    String Q_EQS_GET_ALL_SALER_DEF = "SELECT e FROM Equipment AS e WHERE e.saler = : " + JPQL_PARM_SALER + " ORDER BY e.id DESC";
    //指定した販売店に該当する備品の件数を取得する
    String Q_EQS_COUNT_ALL_SALER= ENTITY_EQ + ".countAllEqsOfSaler";
    String Q_EQS_COUNT_ALL_SALER_DEF = "SELECT COUNT(e) FROM Equipment AS e WHERE e.saler = : " + JPQL_PARM_SALER;

    //JoinOrderHistory
    //指定した販売店の全ての発注履歴をidの降順に取得する
    String Q_J_ODH_GET_ALL_SALER = ENTITY_J_ODH + ".getAllSaler";
    String Q_J_ODH_GET_ALL_SALER_DEF = "SELECT o FROM JoinOrderHistory AS o WHERE o.saler = : " + JPQL_PARM_SALER + " ORDER BY o.id DESC";
    //指定した販売店の全ての発注履歴の件数を取得する
    String Q_J_ODH_COUNT_ALL_SALER = ENTITY_J_ODH + ".countAllSaler";
    String Q_J_ODH_COUNT_ALL_SALER_DEF = "SELECT COUNT(o) FROM JoinOrderHistory AS o WHERE o.saler = : " + JPQL_PARM_SALER;


    //★ORDERHISTORY
    //指定した日付の全ての発注履歴をidの降順に取得する
    String Q_ODH_GET_ALL_ODD = ENTITY_ODH + ".getAllOdhsOfDate";
    String Q_ODH_GET_ALL_ODD_DEF = "SELECT o FROM OrderHistory AS o WHERE o.orderedDate = :" + JPQL_PARM_ODH_ODD_DATE + " ORDER BY o.id DESC";
    //指定した日付の全ての発注履歴の件数を取得する
    String Q_ODH_COUNT_ALL_ODD = ENTITY_ODH + ".countAllOdhsOfDate";
    String Q_ODH_COUNT_ALL_ODD_DEF = "SELECT COUNT(o) FROM OrderHistory AS o WHERE o.orderedDate = :" + JPQL_PARM_ODH_ODD_DATE;

}