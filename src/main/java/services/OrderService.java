package services;

import java.util.List;

import actions.views.OrderHistoryConverter;
import actions.views.OrderHistoryView;
import constants.JpaConst;
import models.Employee;
import models.Ingredient;
import models.JoinOrderHistory;
import models.OrderHistory;



/**
 * 食材テーブルの操作に関わる処理を行うクラス
 */
public class OrderService extends ServiceBase {

    /**
     * 全発注履歴を取得
     * @return 一覧画面に表示するデータのリスト
     */
    public List<JoinOrderHistory> getAllSaler(String saler) {

        List<JoinOrderHistory> odhs = em.createNamedQuery(JpaConst.Q_J_ODH_GET_ALL_SALER, JoinOrderHistory.class)
                .setParameter(JpaConst.JPQL_PARM_SALER, saler)
                .getResultList();
        return odhs;
    }

    /**
     * 件数を取得し、返却する
     * @return データの件数
     */
    public long countAllSaler(String saler) {
        long odhs_count = (long) em.createNamedQuery(JpaConst.Q_J_ODH_COUNT_ALL_SALER, Long.class)
                .setParameter(JpaConst.JPQL_PARM_SALER, saler)
                .getSingleResult();
        return odhs_count;
    }

    /**
     * 発注書に表示する食材データを取得し、OrderIngrViewのリストで返却する
     * @param page ページ数
     * @return 一覧画面に表示するデータのリスト
     */
    public List<OrderHistoryView> getAllOdhsOfDate(String orderedDate) {

        List<OrderHistory> odhs = em.createNamedQuery(JpaConst.Q_ODH_GET_ALL_ODD, OrderHistory.class)
                .setParameter(JpaConst.JPQL_PARM_ODH_ODD_DATE, orderedDate)
                .getResultList();
        return OrderHistoryConverter.toViewList(odhs);

    }

    /**
     * 食材テーブルのデータの件数を取得し、返却する
     * @return データの件数
     */
    public long countAllOdhsOfDate(String orderedDate) {
        long odhs_count = (long) em.createNamedQuery(JpaConst.Q_ODH_COUNT_ALL_ODD, Long.class)
                .setParameter(JpaConst.JPQL_PARM_ODH_ODD_DATE, orderedDate)
                .getSingleResult();
        return odhs_count;
    }

    /**
     * idを条件に食材データを1件取得する
     * @param id
     * @return 取得データのインスタンス
     */
    public Ingredient findOne(int id) {
        return em.find(Ingredient.class, id);
    }

    /**
     * 発注履歴を登録する
     * @param processes 工程データ
     */
    public void create(List<OrderHistoryView> odhList,String oddDate, String saler,Employee oddEmp) {

        em.getTransaction().begin();

        JoinOrderHistory j = new JoinOrderHistory();
        j.setOrderedDate(oddDate);
        j.setSaler(saler);
        j.setOrderedEmp(oddEmp);
        em.persist(j);

        for (OrderHistoryView ov : odhList) {
            OrderHistory o = OrderHistoryConverter.toModel(ov);
            em.persist(o);
        }
        em.getTransaction().commit();
    }
}

