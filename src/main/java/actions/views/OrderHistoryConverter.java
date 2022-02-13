package actions.views;

import java.util.ArrayList;
import java.util.List;

import models.OrderHistory;

/**
 * 食材データのDTOモデル⇔Viewモデルの変換を行うクラス
 *
 */
public class OrderHistoryConverter {

    /**
     * ViewモデルのインスタンスからDTOモデルのインスタンスを作成する
     * @param rv OrderHistoryViewのインスタンス
     * @return OrderHistoryのインスタンス
     */
    public static OrderHistory toModel(OrderHistoryView ov) {

        return new OrderHistory(
                ov.getId(),
                ov.getOrderedDate(),
                ov.getDeliDate(),
                ov.getIngredient(),
                ov.getNumberOfOrder(),
                ov.getPrice(),
                EmployeeConverter.toModel(ov.getOrderedEmp()));
    }

    /**
     * DTOモデルのインスタンスからViewモデルのインスタンスを作成する
     * @param r OrderHistoryのインスタンス
     * @return OrderHistoryViewのインスタンス
     */
    public static OrderHistoryView toView(OrderHistory o) {

        if (o == null) {
            return null;
        }

        return new OrderHistoryView(
                o.getId(),
                o.getOrderedDate(),
                o.getDeliDate(),
                o.getIngredient(),
                o.getNumberOfOrder(),
                o.getPrice(),
                EmployeeConverter.toView(o.getOrderedEmp()));
    }

    /**
     * DTOモデルのリストからViewモデルのリストを作成する
     * @param list DTOモデルのリスト
     * @return Viewモデルのリスト
     */
    public static List<OrderHistoryView> toViewList(List<OrderHistory> list) {
        List<OrderHistoryView> evs = new ArrayList<>();

        for (OrderHistory i : list) {
            evs.add(toView(i));
        }

        return evs;
    }

    /**
     * Viewモデルの全フィールドの内容をDTOモデルのフィールドにコピーする
     * @param r DTOモデル(コピー先)
     * @param rv Viewモデル(コピー元)
     */
    public static void copyViewToModel(OrderHistory o, OrderHistoryView ov) {

        o.setId(ov.getId());
        o.setOrderedDate(ov.getOrderedDate());
        o.setDeliDate(ov.getDeliDate());
        o.setIngredient(ov.getIngredient());
        o.setOrderedEmp(EmployeeConverter.toModel(ov.getOrderedEmp()));
    }
}
