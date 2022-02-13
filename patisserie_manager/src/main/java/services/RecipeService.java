package services;

import java.util.List;

import actions.views.RecipeConverter;
import actions.views.RecipeView;
import constants.JpaConst;
import models.Ingredient;
import models.JoinIngredient;
import models.ProcessRecipe;
import models.Recipe;
import models.validators.RecipeValidator;

/**
 * レシピテーブルの操作に関わる処理を行うクラス
 */
public class RecipeService extends ServiceBase {

    /**
     * 指定されたページ数の一覧画面に表示するレシピデータを取得し、RecipeViewのリストで返却する
     * @param page ページ数
     * @return 一覧画面に表示するデータのリスト
     */
    public List<RecipeView> getAllPerPage(int page) {

        List<Recipe> recipes = em.createNamedQuery(JpaConst.Q_RECI_GET_ALL, Recipe.class)
                .setFirstResult(JpaConst.ROW_PER_PAGE * (page - 1))
                .setMaxResults(JpaConst.ROW_PER_PAGE)
                .getResultList();
        return RecipeConverter.toViewList(recipes);
    }

    /**
     * レシピテーブルのデータの件数を取得し、返却する
     * @return データの件数
     */
    public long countAll() {
        long recipes_count = (long) em.createNamedQuery(JpaConst.Q_RECI_COUNT, Long.class)
                .getSingleResult();
        return recipes_count;
    }


    /**
     * idを条件に取得したデータをRecipeViewのインスタンスで返却する
     * @param id
     * @return 取得データのインスタンス
     */
    public RecipeView findOne(int id) {
        return RecipeConverter.toView(findOneInternal(id));
    }

    /**
     * 画面から入力されたレシピの登録内容を元にデータを1件作成し、レシピテーブルに登録する
     * @param rv レシピの登録内容
     * @return バリデーションで発生したエラーのリスト
     */
    public List<String> create(RecipeView rv) {
        List<String> errors = RecipeValidator.validate(rv);
        if (errors.size() == 0) {
            createInternal(rv);
        }

        //バリデーションで発生したエラーを返却（エラーがなければ0件の空リスト）
        return errors;
    }

    /**
     * 画面から入力されたレシピの登録内容を元に、レシピデータを更新する
     * @param rv レシピの更新内容
     * @return バリデーションで発生したエラーのリスト
     */
    public List<String> update(RecipeView rv) {

        //バリデーションを行う
        List<String> errors = RecipeValidator.validate(rv);

        if (errors.size() == 0) {
            updateInternal(rv);
        }

        //バリデーションで発生したエラーを返却（エラーがなければ0件の空リスト）
        return errors;
    }

    /**
     * idを条件にレシピデータを1件取得する
     * @param id
     * @return 取得データのインスタンス
     */
    public Recipe findOneInternal(int id) {
        return em.find(Recipe.class, id);
    }

    /**
     * レシピデータを1件登録する（食材も）
     * @param rv レシピデータ
     */
    private void createInternal(RecipeView rv) {
        //Recipeクラスのもの
        em.getTransaction().begin();
        Recipe recipe = RecipeConverter.toModel(rv);
        em.persist(recipe);

        // 食材・分量・アレルギー情報を登録
        for (JoinIngredient j : recipe.getJoinIngredient()) {
            j.setRecipe(recipe);
            em.persist(j);
        }
        em.getTransaction().commit();//トランザクションの間で処理が失敗したら全て処理が取り消される
    }

    /**
     * レシピデータを更新する
     * @param rv レシピデータ
     */
    private void updateInternal(RecipeView rv) {

        em.getTransaction().begin();
        Recipe recipe = RecipeConverter.toModel(rv);
        //データの削除を行う
        em.createNamedQuery(JpaConst.Q_RECI_INGR_DELETE)
                .setParameter(JpaConst.JPQL_PARM_RECIPE, recipe)
                .executeUpdate();
        // 食材・分量・アレルギー情報を登録
        for (JoinIngredient a : recipe.getJoinIngredient()) {
            a.setRecipe(recipe);
            em.persist(a);
        }
        em.merge(recipe);
        em.getTransaction().commit();
    }

    /**
     * idを条件にレシピデータを論理削除する
     * @param id
     */
    public void destroy(Integer id) {

        //idを条件に登録済みのレシピ情報を取得する
        RecipeView savedReci = findOne(id);
        //論理削除フラグをたてる
        savedReci.setDeleteFlag(JpaConst.RECI_DEL_TRUE);
        //更新処理を行う
        update(savedReci);
    }
    
    //以下検索


    /**
     * 検索したタイトルに該当するレシピデータを、指定されたページ数の一覧画面に表示する分取得しRecipeViewのリストで返却する
     * @param title タイトル
     * @param page ページ数
     * @return 一覧画面に表示するデータのリスト
     */

    public List<RecipeView> getTitleBySearchPerPage(String title, int page) {

        List<Recipe> recipes = em.createNamedQuery(JpaConst.Q_RECI_GET_TITLE_BY_SEARCH, Recipe.class)
                .setParameter(JpaConst.JPQL_PARM_TITLE, "%" + title + "%")
                //setParameter(〇〇, △△)　〇〇を△△に置き換える処理、
                .setFirstResult(JpaConst.ROW_PER_PAGE * (page - 1))
                .setMaxResults(JpaConst.ROW_PER_PAGE)
                .getResultList();
        return RecipeConverter.toViewList(recipes);
    }

    /**
     * 検索したタイトルに該当するレシピデータの件数を取得し、返却する
     * @param title
     * @return レシピデータの件数
     */
    public long countTitleBySearch(String title) {

        long count = (long) em.createNamedQuery(JpaConst.Q_RECI_COUNT_TITLE_BY_SEARCH, Long.class)
                .setParameter(JpaConst.JPQL_PARM_TITLE, "%" + title + "%")
                .getSingleResult();

        return count;
    }


    //以下食材


    /**
     * 食材情報を取得し、返却する
     * @param なし
     * @return 食材情報
     */
    public List<Ingredient> getAllIngredients() {

        List<Ingredient> ingredients = em.createNamedQuery(JpaConst.Q_INGR_GET_ALL, Ingredient.class)
                .getResultList();
        return ingredients;
    }

    /**
     * 食材中間テーブル情報を取得し、返却する
     * @param なし
     * @return 食材情報
     */
    public List<JoinIngredient> getAllJoinIngredients() {

        List<JoinIngredient> JoinIngredients = em.createNamedQuery(JpaConst.Q_J_INGR_GET_ALL, JoinIngredient.class)
                .getResultList();
        return JoinIngredients;
    }


    //以下工程

    /**
     * idを条件に工程データを1件取得する
     * @param id
     * @return 取得データのインスタンス
     */
    public ProcessRecipe findOneInternalPr(int id) {
        return em.find(ProcessRecipe.class, id);
    }

    /**
     * 工程データを登録する
     * @param processes 工程データ
     */
    public void createInternalPr(List<ProcessRecipe> processes,String id) {

        em.getTransaction().begin();
        for (ProcessRecipe p : processes) {
            Recipe recipe = em.find(Recipe.class,Integer.parseInt(id));
            p.setRecipe(recipe);
            em.merge(recipe);
            em.persist(p);
        }
        em.getTransaction().commit();
    }

    /**
     * 工程データを更新する
     * @param rv 工程データ
     */
    public void updateInternalPr(List<ProcessRecipe> processes,String id) {

        em.getTransaction().begin();
        Recipe recipe = em.find(Recipe.class,Integer.parseInt(id));
        //データの削除を行う
        em.createNamedQuery(JpaConst.Q_RECI_PR_DELETE)
                .setParameter(JpaConst.JPQL_PARM_RECIPE, recipe)
                .executeUpdate();
        for (ProcessRecipe p : processes) {
        p.setRecipe(recipe);
        em.merge(recipe);
        em.persist(p);
        }
        em.getTransaction().commit();
    }

    /**
     * idを条件に工程データを削除する
     * @param id
     */
    public void destroyPr(String id) {

        em.getTransaction().begin();
        Recipe recipe = em.find(Recipe.class,Integer.parseInt(id));
        //データの削除を行う
        em.createNamedQuery(JpaConst.Q_RECI_PR_DELETE)
                .setParameter(JpaConst.JPQL_PARM_RECIPE, recipe)
                .executeUpdate();
        em.persist(recipe);
        em.getTransaction().commit();
    }
}