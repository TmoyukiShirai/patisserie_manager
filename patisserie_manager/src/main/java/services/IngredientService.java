package services;

import java.util.List;

import actions.views.IngredientConverter;
import actions.views.IngredientView;
import constants.AttributeConst;
import constants.JpaConst;
import models.Allergy;
import models.AllergyItem;
import models.Ingredient;
import models.validators.IngredientValidator;



/**
 * 食材テーブルの操作に関わる処理を行うクラス
 */
public class IngredientService extends ServiceBase {

    /**
     * 指定されたページ数の一覧画面に表示する食材データを取得し、IngredientViewのリストで返却する
     * @param page ページ数
     * @return 一覧画面に表示するデータのリスト
     */
    public List<IngredientView> getAllPerPage(int page) {

        List<Ingredient> ingredients = em.createNamedQuery(JpaConst.Q_INGR_GET_ALL, Ingredient.class)
                .setFirstResult(JpaConst.ROW_PER_PAGE * (page - 1))
                .setMaxResults(JpaConst.ROW_PER_PAGE)
                .getResultList();
        return IngredientConverter.toViewList(ingredients);

    }

    /**
     * 食材テーブルのデータの件数を取得し、返却する
     * @return データの件数
     */
    public long countAll() {
        long ingredients_count = (long) em.createNamedQuery(JpaConst.Q_INGR_COUNT, Long.class)
                .getSingleResult();
        return ingredients_count;
    }

    /**
     * idを条件に取得したデータをIngredientViewのインスタンスで返却する
     * @param id
     * @return 取得データのインスタンス
     */
    public IngredientView findOne(int id) {
        return IngredientConverter.toView(findOneInternal(id));
    }

    /**
     * 画面から入力された食材の登録内容を元にデータを1件作成し、食材テーブルに登録する
     * @param rv 食材の登録内容
     * @return バリデーションで発生したエラーのリスト
     */
    public List<String> create(IngredientView iv) {

        List<String> errors = IngredientValidator.validate(iv);//アレルギーは無い場合もあるのでバリデーション不要
        if (errors.size() == 0) {
            createInternal(iv);
        }

        //バリデーションで発生したエラーを返却（エラーがなければ0件の空リスト）
        return errors;
    }

    /**
     * 画面から入力された食材の登録内容を元に、食材データを更新する
     * @param rv 食材の更新内容
     * @return バリデーションで発生したエラーのリスト
     */
    public List<String> update(IngredientView iv) {

        //バリデーションを行う
        List<String> errors = IngredientValidator.validate(iv);

        if (errors.size() == 0) {
            updateInternal(iv);
        }

        //バリデーションで発生したエラーを返却（エラーがなければ0件の空リスト）
        return errors;
    }

    /**
     * idを条件にデータを1件取得する
     * @param id
     * @return 取得データのインスタンス
     */
    private Ingredient findOneInternal(int id) {
        return em.find(Ingredient.class, id);
    }

    /**
     * 食材データを1件登録する（アレルギーも）
     * @param rv 食材データ
     */
    private void createInternal(IngredientView iv) {

        em.getTransaction().begin();
        Ingredient ingredient = IngredientConverter.toModel(iv);
        em.persist(ingredient);

        // アレルギーの情報を登録
        for (Allergy a : ingredient.getAllergy()) {
            a.setIngredient(ingredient);
            em.persist(a);
        }
        em.getTransaction().commit();//トランザクションの間で処理が失敗したら全て処理が取り消される
    }

    /**
     * 食材データを更新する
     * @param rv 食材データ
     */
    private void updateInternal(IngredientView iv) {

        em.getTransaction().begin();

        Ingredient ingredient = IngredientConverter.toModel(iv);

        //データの削除を行う
        em.createNamedQuery(JpaConst.Q_INGR_ALGY_DELETE)
                .setParameter(JpaConst.JPQL_PARM_INGREDIENT, ingredient)
                .executeUpdate();


        // アレルギーの情報を登録
        for (Allergy a : ingredient.getAllergy()) {
            a.setIngredient(ingredient);
            em.persist(a);
        }

        em.merge(ingredient);

        em.getTransaction().commit();
    }


    /**
     * 検索した食材名に該当する食材データを、指定されたページ数の一覧画面に表示する分取得しIngredientViewのリストで返却する
     * @param name 食材名
     * @param page ページ数
     * @return 一覧画面に表示するデータのリスト
     */

    public List<IngredientView> getNameBySearchPerPage(String name, int page) {

        List<Ingredient> ingredients = em.createNamedQuery(JpaConst.Q_INGR_GET_NAME_BY_SEARCH, Ingredient.class)
                .setParameter(JpaConst.JPQL_PARM_NAME, "%" + name + "%")
                //setParameter(〇〇, △△)　〇〇を△△に置き換える処理、
                .setFirstResult(JpaConst.ROW_PER_PAGE * (page - 1))
                .setMaxResults(JpaConst.ROW_PER_PAGE)
                .getResultList();
        return IngredientConverter.toViewList(ingredients);
    }

    /**
     * 検索した食材名に該当する食材データの件数を取得し、返却する
     * @param name
     * @return 食材データの件数
     */
    public long countNameBySearch(String name) {

        long count = (long) em.createNamedQuery(JpaConst.Q_INGR_COUNT_NAME_BY_SEARCH, Long.class)
                .setParameter(JpaConst.JPQL_PARM_NAME, "%" + name + "%")
                .getSingleResult();

        return count;
    }

    /**
     * アレルギーの選択肢を取得し、返却する
     * @param なし
     * @return アレルギーの選択肢
     */
    public List<AllergyItem> getAllAllergy() {

        List<AllergyItem> allergy = em.createNamedQuery(JpaConst.Q_ALGYITME_GET_ALL, AllergyItem.class)
                .getResultList();
        return allergy;
    }
    /**
     * idを条件に食材データを論理削除する
     * @param id
     */
    public void destroy(Integer id) {

        //idを条件に登録済みの食材情報を取得する
        IngredientView savedIngr = findOne(id);
        //論理削除フラグをたてる
        savedIngr.setDeleteFlag(JpaConst.INGR_DEL_TRUE);
        //更新処理を行う
        update(savedIngr);

    }

    /**
     * 発注書に画面に表示する食材データを取得し、Ingredientのリストで返却する
     * @param page ページ数
     * @return 一覧画面に表示するデータのリスト
     */
    String saler = AttributeConst.INGR_SALER.getValue();
    public List<Ingredient> getAllIngrsOfSaler(String saler, int page) {

        List<Ingredient> ingredients = em.createNamedQuery(JpaConst.Q_INGRS_GET_ALL_SALER, Ingredient.class)
                .setParameter(JpaConst.JPQL_PARM_SALER, saler)
                .setFirstResult(JpaConst.ROW_PER_PAGE * (page - 1))
                .setMaxResults(JpaConst.ROW_PER_PAGE)
                .getResultList();
        return ingredients;

    }

    /**
     * 発注書に画面に表示する食材データの件数を取得し、返却する
     * @param page ページ数
     * @return 一覧画面に表示するデータのリスト
     */

    public long countAllIngrsOfSaler(String saler) {

        long count = (long) em.createNamedQuery(JpaConst.Q_INGRS_COUNT_ALL_SALER, Long.class)
                .setParameter(JpaConst.JPQL_PARM_SALER, saler)
                .getSingleResult();

        return count;
    }


}

