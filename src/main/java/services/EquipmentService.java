package services;

import java.util.List;

import actions.views.EquipmentConverter;
import actions.views.EquipmentView;
import constants.AttributeConst;
import constants.JpaConst;
import models.Equipment;
import models.validators.EquipmentValidator;

/**
 * 備品テーブルの操作に関わる処理を行うクラス
 */
public class EquipmentService extends ServiceBase {

    /**
     * 指定されたページ数の一覧画面に表示する備品データを取得し、EquipmentViewのリストで返却する
     * @param page ページ数
     * @return 一覧画面に表示するデータのリスト
     */
    public List<EquipmentView> getAllPerPage(int page) {

        List<Equipment> equipments = em.createNamedQuery(JpaConst.Q_EQ_GET_ALL, Equipment.class)
                .setFirstResult(JpaConst.ROW_PER_PAGE * (page - 1))
                .setMaxResults(JpaConst.ROW_PER_PAGE)
                .getResultList();
        return EquipmentConverter.toViewList(equipments);
    }

    /**
     * 備品テーブルのデータの件数を取得し、返却する
     * @return データの件数
     */
    public long countAll() {
        long equipments_count = (long) em.createNamedQuery(JpaConst.Q_EQ_COUNT, Long.class)
                .getSingleResult();
        return equipments_count;
    }

    /**
     * idを条件に取得したデータをEquipmentViewのインスタンスで返却する
     * @param id
     * @return 取得データのインスタンス
     */
    public EquipmentView findOne(int id) {
        return EquipmentConverter.toView(findOneInternal(id));
    }

    /**
     * 画面から入力された備品の登録内容を元にデータを1件作成し、備品テーブルに登録する
     * @param rv 備品の登録内容
     * @return バリデーションで発生したエラーのリスト
     */
    public List<String> create(EquipmentView rv) {
        List<String> errors = EquipmentValidator.validate(rv);
        if (errors.size() == 0) {
            createInternal(rv);
        }

        //バリデーションで発生したエラーを返却（エラーがなければ0件の空リスト）
        return errors;
    }

    /**
     * 画面から入力された備品の登録内容を元に、備品データを更新する
     * @param rv 備品の更新内容
     * @return バリデーションで発生したエラーのリスト
     */
    public List<String> update(EquipmentView rv) {

        //バリデーションを行う
        List<String> errors = EquipmentValidator.validate(rv);

        if (errors.size() == 0) {
            updateInternal(rv);
        }

        //バリデーションで発生したエラーを返却（エラーがなければ0件の空リスト）
        return errors;
    }

    /**
     * idを条件にデータを1件取得する
     * @param id
     * @return 取得データのインスタンス
     */
    private Equipment findOneInternal(int id) {
        return em.find(Equipment.class, id);
    }

    /**
     * 備品データを1件登録する
     * @param rv 備品データ
     */
    private void createInternal(EquipmentView rv) {

        em.getTransaction().begin();
        em.persist(EquipmentConverter.toModel(rv));
        em.getTransaction().commit();

    }

    /**
     * 備品データを更新する
     * @param rv 備品データ
     */
    private void updateInternal(EquipmentView rv) {

        em.getTransaction().begin();
        Equipment r = findOneInternal(rv.getId());
        EquipmentConverter.copyViewToModel(r, rv);
        em.getTransaction().commit();

    }

    /**
     * 検索した備品名に該当する備品データを、指定されたページ数の一覧画面に表示する分取得しEquipmentViewのリストで返却する
     * @param title タイトル
     * @param page ページ数
     * @return 一覧画面に表示するデータのリスト
     */

    public List<EquipmentView> getNameBySearchPerPage(String name, int page) {

        List<Equipment> equipments = em.createNamedQuery(JpaConst.Q_EQ_GET_NAME_BY_SEARCH, Equipment.class)
                .setParameter(JpaConst.JPQL_PARM_NAME, "%" + name + "%")
                //setParameter(〇〇, △△)　〇〇を△△に置き換える処理、
                .setFirstResult(JpaConst.ROW_PER_PAGE * (page - 1))
                .setMaxResults(JpaConst.ROW_PER_PAGE)
                .getResultList();
        return EquipmentConverter.toViewList(equipments);
    }

    /**
     * 検索しに該当する備品データの件数を取得し、返却する
     * @param title
     * @return 備品データの件数
     */
    public long countNameBySearch(String name) {

        long count = (long) em.createNamedQuery(JpaConst.Q_EQ_COUNT_NAME_BY_SEARCH, Long.class)
                .setParameter(JpaConst.JPQL_PARM_NAME, "%" + name + "%")
                .getSingleResult();

        return count;
    }

    /**
     * idを条件に備品データを論理削除する
     * @param id
     */
    public void destroy(Integer id) {

        //idを条件に登録済みの備品情報を取得する
        EquipmentView savedEq = findOne(id);
        //論理削除フラグをたてる
        savedEq.setDeleteFlag(JpaConst.EQ_DEL_TRUE);
        //更新処理を行う
        update(savedEq);

    }
    /**
     * 発注書に画面に表示する備品データを取得し、Equipmentのリストで返却する
     * @param page ページ数
     * @return 一覧画面に表示するデータのリスト
     */
    String saler = AttributeConst.EQ_SALER.getValue();
    public List<Equipment> getAllEqsOfSaler(String saler, int page) {

        List<Equipment> equipments = em.createNamedQuery(JpaConst.Q_EQS_GET_ALL_SALER, Equipment.class)
                .setParameter(JpaConst.JPQL_PARM_SALER, saler)
                .setFirstResult(JpaConst.ROW_PER_PAGE * (page - 1))
                .setMaxResults(JpaConst.ROW_PER_PAGE)
                .getResultList();
        return equipments;

    }

    /**
     * 発注書に画面に表示する備品データの件数を取得し、返却する
     * @param page ページ数
     * @return 一覧画面に表示するデータのリスト
     */

    public long countAllEqsOfSaler(String saler) {

        long count = (long) em.createNamedQuery(JpaConst.Q_EQS_COUNT_ALL_SALER, Long.class)
                .setParameter(JpaConst.JPQL_PARM_SALER, saler)
                .getSingleResult();

        return count;
    }

}