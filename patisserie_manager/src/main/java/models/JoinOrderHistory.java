
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
@Table(name = JpaConst.TABLE_J_ODH)
@NamedQueries({
    @NamedQuery(
            name = JpaConst.Q_J_ODH_GET_ALL_SALER,
            query = JpaConst.Q_J_ODH_GET_ALL_SALER_DEF),
    @NamedQuery(
            name = JpaConst.Q_J_ODH_COUNT_ALL_SALER,
            query = JpaConst.Q_J_ODH_COUNT_ALL_SALER_DEF),
})

@Getter //全てのクラスフィールドについてgetterを自動生成する(Lombok)
@Setter //全てのクラスフィールドについてsetterを自動生成する(Lombok)
@NoArgsConstructor //引数なしコンストラクタを自動生成する(Lombok)
@AllArgsConstructor //全てのクラスフィールドを引数にもつ引数ありコンストラクタを自動生成する(Lombok)
@Entity
public class JoinOrderHistory {//発注書を日付別にまとめて一覧表示するために必要なクラス
    /**
     * id
     */
    @Id
    @Column(name = JpaConst.J_ODH_COL_ID)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    /**
     * 発注日
     */
    @Column(name = JpaConst.J_ODH_COL_ODD_DATE, nullable = false)
    private String orderedDate;

    /**
     * 販売店
     */
    @Column(name = JpaConst.J_ODH_COL_SALER, nullable = true)
    private String saler;

    /**
     * 発注者
     */
    @ManyToOne
    @JoinColumn(name = JpaConst.J_ODH_COL_ODD_EMP)
    private Employee orderedEmp;


}