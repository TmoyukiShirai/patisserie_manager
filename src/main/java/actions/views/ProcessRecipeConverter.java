package actions.views;

import java.util.ArrayList;
import java.util.List;

import models.ProcessRecipe;
import models.Recipe;

/**
 * 食材データのDTOモデル⇔Viewモデルの変換を行うクラス
 *
 */
public class ProcessRecipeConverter {

    /**
     * ViewモデルのインスタンスからDTOモデルのインスタンスを作成する
     * @param rv ProcessRecipeViewのインスタンス
     * @return ProcessRecipeのインスタンス
     */
    public static ProcessRecipe toModel(ProcessRecipeView iv) {
        Recipe recipe = new Recipe();
        recipe.setId(Integer.parseInt(iv.getR_id()));

        return new ProcessRecipe(
                iv.getId(),
                recipe,
                iv.getDescription(),
                iv.getUtensil(),
                iv.getTemperature(),
                iv.getSpeed(),
                iv.getTime(),
                iv.getDeleteFlag());
    }

    /**
     * DTOモデルのインスタンスからViewモデルのインスタンスを作成する
     * @param r ProcessRecipeのインスタンス
     * @return ProcessRecipeViewのインスタンス
     */
    public static ProcessRecipeView toView(ProcessRecipe i) {

        if (i == null) {
            return null;
        }

        return new ProcessRecipeView(
                i.getId(),
                String.valueOf(i.getRecipe().getId()), //ProcessRecipeViewのr_idが文字列型で、ProcessRecipe のidが数値型のため(正確にはIngredient.javaの中のidなので、数値型）、文字列型に変換する
                i.getDescription(),
                i.getUtensil(),
                i.getTemperature(),
                i.getSpeed(),
                i.getTime(),
                i.getDeleteFlag());
    }

    /**
     * DTOモデルのリストからViewモデルのリストを作成する
     * @param list DTOモデルのリスト
     * @return Viewモデルのリスト
     */
    public static List<ProcessRecipeView> toViewList(List<ProcessRecipe> list) {
        List<ProcessRecipeView> evs = new ArrayList<>();

        for (ProcessRecipe i : list) {
            evs.add(toView(i));
        }

        return evs;
    }

    /**
     * Viewモデルの全フィールドの内容をDTOモデルのフィールドにコピーする
     * @param r DTOモデル(コピー先)
     * @param rv Viewモデル(コピー元)
     */
    public static void copyViewToModel(ProcessRecipe i, ProcessRecipeView iv) {
        Recipe recipe = new Recipe();
        recipe.setId(Integer.parseInt(iv.getR_id()));   //Recipe をインスタンス化(new)して、Recipeのidにセットして、インスタンスをRecipe にセット
        i.setId(iv.getId());
        i.setRecipe(recipe);
        i.setDescription(iv.getDescription());
        i.setUtensil(iv.getUtensil());
        i.setTemperature(iv.getTemperature());
        i.setSpeed(iv.getSpeed());
        i.setTime(iv.getTime());
        i.setDeleteFlag(iv.getDeleteFlag());
    }
}
