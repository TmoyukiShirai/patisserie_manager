package actions.views;

import java.util.ArrayList;
import java.util.List;

import models.Recipe;

/**
 * レシピデータのDTOモデル⇔Viewモデルの変換を行うクラス
 *
 */
public class RecipeConverter {

    /**
     * ViewモデルのインスタンスからDTOモデルのインスタンスを作成する
     * @param rv ReportViewのインスタンス
     * @return Reportのインスタンス
     */
    public static Recipe toModel(RecipeView rv) {
        return new Recipe(
                rv.getId(),
                rv.getTitle(),
                rv.getType(),
                rv.getNumber(),
                rv.getJoinIngredient(),
                rv.getProcessRecipe(),
                rv.getDeleteFlag());
    }

    /**
     * DTOモデルのインスタンスからViewモデルのインスタンスを作成する
     * @param r Reportのインスタンス
     * @return ReportViewのインスタンス
     */
    public static RecipeView toView(Recipe r) {

        if (r == null) {
            return null;
        }

        return new RecipeView(
                r.getId(),
                r.getTitle(),
                r.getType(),
                r.getNumber(),
                r.getJoinIngredient(),
                r.getProcessRecipe(),
                r.getDeleteFlag());
        }

    /**
     * DTOモデルのリストからViewモデルのリストを作成する
     * @param list DTOモデルのリスト
     * @return Viewモデルのリスト
     */
    public static List<RecipeView> toViewList(List<Recipe> list) {
        List<RecipeView> evs = new ArrayList<>();

        for (Recipe r : list) {
            evs.add(toView(r));
        }

        return evs;
    }

    /**
     * Viewモデルの全フィールドの内容をDTOモデルのフィールドにコピーする
     * @param r DTOモデル(コピー先)
     * @param rv Viewモデル(コピー元)
     */
    public static void copyViewToModel(Recipe r, RecipeView rv) {
        r.setId(rv.getId());
        r.setTitle(rv.getTitle());
        r.setType(rv.getType());
        r.setNumber(rv.getNumber());
        r.setJoinIngredient(rv.getJoinIngredient());
        r.setProcessRecipe(rv.getProcessRecipe());
        r.setDeleteFlag(rv.getDeleteFlag());

    }

}