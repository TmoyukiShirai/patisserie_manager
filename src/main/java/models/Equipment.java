
package models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

import constants.JpaConst;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * レシピデータのDTOモデル
 *
 */
@Table(name = JpaConst.TABLE_EQ)
@NamedQueries({
    @NamedQuery(
            name = JpaConst.Q_EQ_GET_ALL,
            query = JpaConst.Q_EQ_GET_ALL_DEF),
    @NamedQuery(
            name = JpaConst.Q_EQ_COUNT,
            query = JpaConst.Q_EQ_COUNT_DEF),
    @NamedQuery(
            name = JpaConst.Q_EQ_GET_NAME_BY_SEARCH,
            query = JpaConst.Q_EQ_GET_NAME_BY_SEARCH_DEF),
    @NamedQuery(
            name = JpaConst.Q_EQ_COUNT_NAME_BY_SEARCH,
            query = JpaConst.Q_EQ_COUNT_NAME_BY_SEARCH_DEF),
    @NamedQuery(
            name = JpaConst.Q_EQS_GET_ALL_SALER,
            query = JpaConst.Q_EQS_GET_ALL_SALER_DEF),
    @NamedQuery(
            name = JpaConst.Q_EQS_COUNT_ALL_SALER,
            query = JpaConst.Q_EQS_COUNT_ALL_SALER_DEF),

})

@Getter //全てのクラスフィールドについてgetterを自動生成する(Lombok)
@Setter //全てのクラスフィールドについてsetterを自動生成する(Lombok)
@NoArgsConstructor //引数なしコンストラクタを自動生成する(Lombok)
@AllArgsConstructor //全てのクラスフィールドを引数にもつ引数ありコンストラクタを自動生成する(Lombok)
@Entity
public class Equipment {
    /**
     * id
     */
    @Id
    @Column(name = JpaConst.EQ_COL_ID)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    /**
     * 備品名
     */
    @Column(name = JpaConst.EQ_COL_NAME, length = 255, nullable = false)
    private String name;

    /**
     * メーカー
     */
    @Column(name = JpaConst.EQ_COL_MAKER, nullable = true)
    private String maker;

    /**
     * 販売店
     */
    @Column(name = JpaConst.EQ_COL_SALER, nullable = true)
    private String saler;

    /**
     * 単価
     */
    @Column(name = JpaConst.EQ_COL_PRICE, nullable = true)
    private Double price;

    /**
     * 内容量
     */
    @Column(name = JpaConst.EQ_COL_AMOUNT, nullable = true)
    private Double amount;

    /**
     * 内容量の単位
     */
    @Column(name = JpaConst.EQ_COL_AMOUNT_U, nullable = true)
    private String amount_u;

    /**
     * 削除された従業員かどうか（現役：0、削除済み：1）
     */
    @Column(name = JpaConst.EQ_COL_DELETE_FLAG, nullable = false)
    private Integer deleteFlag;
}