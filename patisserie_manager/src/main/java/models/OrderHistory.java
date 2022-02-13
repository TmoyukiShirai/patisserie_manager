
package models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import constants.JpaConst;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * 発注履歴データのDTOモデル
 *
 */
@Table(name = JpaConst.TABLE_ODH)
@NamedQueries({
    @NamedQuery(
            name = JpaConst.Q_ODH_GET_ALL_ODD,
            query = JpaConst.Q_ODH_GET_ALL_ODD_DEF),
    @NamedQuery(
            name = JpaConst.Q_ODH_COUNT_ALL_ODD,
            query = JpaConst.Q_ODH_COUNT_ALL_ODD_DEF),

})

@Getter //全てのクラスフィールドについてgetterを自動生成する(Lombok)
@Setter //全てのクラスフィールドについてsetterを自動生成する(Lombok)
@NoArgsConstructor //引数なしコンストラクタを自動生成する(Lombok)
@AllArgsConstructor //全てのクラスフィールドを引数にもつ引数ありコンストラクタを自動生成する(Lombok)
@Entity
public class OrderHistory {
    /**
     * id
     */
    @Id
    @Column(name = JpaConst.ODH_COL_ID)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    /**
     * 発注日
     */
    @Column(name = JpaConst.ODH_COL_ODD_DATE, nullable = false)
    private String orderedDate;

    /**
     * 納品日
     */
    @Column(name = JpaConst.ODH_COL_DELI_DATE, nullable = false)
    private String deliDate;

    /**
     * 食材
     *
     */
    @OneToOne
    @JoinColumn(name = JpaConst.ODH_COL_INGREDIENT, nullable = true)
    private Ingredient ingredient;

    /**
     * 注文数
     */
    @Column(name = JpaConst.ODH_COL_NUM_OF_ORDER, nullable = true)
    private Double numberOfOrder;

    /**
     * 金額
     */
    @Column(name = JpaConst.ODH_COL_PRICE, nullable = true)
    private Double price;

    /**
     * 発注者
     */
    @ManyToOne
    @JoinColumn(name = JpaConst.ODH_COL_ODD_EMP)
    private Employee orderedEmp;

}