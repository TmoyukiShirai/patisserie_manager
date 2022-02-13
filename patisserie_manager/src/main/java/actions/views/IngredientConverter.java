package actions.views;

import java.util.ArrayList;
import java.util.List;

import models.Ingredient;

/**
 * 食材データのDTOモデル⇔Viewモデルの変換を行うクラス
 *
 */
public class IngredientConverter {

    /**
     * ViewモデルのインスタンスからDTOモデルのインスタンスを作成する
     * @param rv IngredientViewのインスタンス
     * @return Ingredientのインスタンス
     */
    public static Ingredient toModel(IngredientView iv) {
        return new Ingredient(
                iv.getId(),
                iv.getName(),
                iv.getMaker(),
                iv.getSaler(),
                iv.getPrice(),
                iv.getAmount(),
                iv.getAmount_u(),
                iv.getEnergy(),
                iv.getAllergy(),
                iv.getDeleteFlag());
    }

    /**
     * DTOモデルのインスタンスからViewモデルのインスタンスを作成する
     * @param r Ingredientのインスタンス
     * @return IngredientViewのインスタンス
     */
    public static IngredientView toView(Ingredient i) {

        if (i == null) {
            return null;
        }

        return new IngredientView(
                i.getId(),
                i.getName(),
                i.getMaker(),
                i.getSaler(),
                i.getPrice(),
                i.getAmount(),
                i.getAmount_u(),
                i.getEnergy(),
                i.getAllergy(),
                i.getDeleteFlag());
    }

    /**
     * DTOモデルのリストからViewモデルのリストを作成する
     * @param list DTOモデルのリスト
     * @return Viewモデルのリスト
     */
    public static List<IngredientView> toViewList(List<Ingredient> list) {
        List<IngredientView> evs = new ArrayList<>();

        for (Ingredient i : list) {
            evs.add(toView(i));
        }

        return evs;
    }

    /**
     * Viewモデルの全フィールドの内容をDTOモデルのフィールドにコピーする
     * @param r DTOモデル(コピー先)
     * @param rv Viewモデル(コピー元)
     */
    public static void copyViewToModel(Ingredient i, IngredientView iv) {
        i.setId(iv.getId());
        i.setName(iv.getName());
        i.setMaker(iv.getMaker());
        i.setSaler(iv.getSaler());
        i.setPrice(iv.getPrice());
        i.setAmount(iv.getAmount());
        i.setAmount_u(iv.getAmount_u());
        i.setEnergy(iv.getEnergy());
        i.setAllergy(iv.getAllergy());
        i.setDeleteFlag(iv.getDeleteFlag());
    }

}