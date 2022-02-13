package actions.views;

import java.util.ArrayList;
import java.util.List;

import models.Equipment;

/**
 * 食材データのDTOモデル⇔Viewモデルの変換を行うクラス
 *
 */
public class EquipmentConverter {

    /**
     * ViewモデルのインスタンスからDTOモデルのインスタンスを作成する
     * @param rv EquipmentViewのインスタンス
     * @return Equipmentのインスタンス
     */
    public static Equipment toModel(EquipmentView iv) {
        return new Equipment(
                iv.getId(),
                iv.getName(),
                iv.getMaker(),
                iv.getSaler(),
                iv.getPrice(),
                iv.getAmount(),
                iv.getAmount_u(),
                iv.getDeleteFlag());
    }

    /**
     * DTOモデルのインスタンスからViewモデルのインスタンスを作成する
     * @param r Equipmentのインスタンス
     * @return EquipmentViewのインスタンス
     */
    public static EquipmentView toView(Equipment i) {

        if (i == null) {
            return null;
        }

        return new EquipmentView(
                i.getId(),
                i.getName(),
                i.getMaker(),
                i.getSaler(),
                i.getPrice(),
                i.getAmount(),
                i.getAmount_u(),
                i.getDeleteFlag());

    }

    /**
     * DTOモデルのリストからViewモデルのリストを作成する
     * @param list DTOモデルのリスト
     * @return Viewモデルのリスト
     */
    public static List<EquipmentView> toViewList(List<Equipment> list) {
        List<EquipmentView> evs = new ArrayList<>();

        for (Equipment i : list) {
            evs.add(toView(i));
        }

        return evs;
    }

    /**
     * Viewモデルの全フィールドの内容をDTOモデルのフィールドにコピーする
     * @param r DTOモデル(コピー先)
     * @param rv Viewモデル(コピー元)
     */
    public static void copyViewToModel(Equipment i, EquipmentView iv) {
        i.setId(iv.getId());
        i.setName(iv.getName());
        i.setMaker(iv.getMaker());
        i.setSaler(iv.getSaler());
        i.setPrice(iv.getPrice());
        i.setAmount(iv.getAmount());
        i.setAmount_u(iv.getAmount_u());
        i.setDeleteFlag(iv.getDeleteFlag());

    }

}