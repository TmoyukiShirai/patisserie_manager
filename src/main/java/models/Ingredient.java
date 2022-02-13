
package models;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
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
@Table(name = JpaConst.TABLE_INGR)
@NamedQueries({
    @NamedQuery(
            name = JpaConst.Q_INGR_GET_ALL,
            query = JpaConst.Q_INGR_GET_ALL_DEF),
    @NamedQuery(
            name = JpaConst.Q_INGR_COUNT,
            query = JpaConst.Q_INGR_COUNT_DEF),
    @NamedQuery(
            name = JpaConst.Q_INGR_GET_NAME_BY_SEARCH,
            query = JpaConst.Q_INGR_GET_NAME_BY_SEARCH_DEF),
    @NamedQuery(
            name = JpaConst.Q_INGR_COUNT_NAME_BY_SEARCH,
            query = JpaConst.Q_INGR_COUNT_NAME_BY_SEARCH_DEF),
    @NamedQuery(
            name = JpaConst.Q_INGR_GET_SALER_BY_SEARCH,
            query = JpaConst.Q_INGR_GET_SALER_BY_SEARCH_DEF),
    @NamedQuery(
            name = JpaConst.Q_INGR_COUNT_SALER_BY_SEARCH,
            query = JpaConst.Q_INGR_COUNT_SALER_BY_SEARCH_DEF),
    @NamedQuery(
            name = JpaConst.Q_INGR_ALGY_DELETE,
            query = JpaConst.Q_INGR_ALGY_DELETE_DEF),
    @NamedQuery(
            name = JpaConst.Q_INGRS_GET_ALL_SALER,
            query = JpaConst.Q_INGRS_GET_ALL_SALER_DEF),
    @NamedQuery(
            name = JpaConst.Q_INGRS_COUNT_ALL_SALER,
            query = JpaConst.Q_INGRS_COUNT_ALL_SALER_DEF),

})

@Getter //全てのクラスフィールドについてgetterを自動生成する(Lombok)
@Setter //全てのクラスフィールドについてsetterを自動生成する(Lombok)
@NoArgsConstructor //引数なしコンストラクタを自動生成する(Lombok)
@AllArgsConstructor //全てのクラスフィールドを引数にもつ引数ありコンストラクタを自動生成する(Lombok)
@Entity
public class Ingredient {
    /**
     * id
     */
    @Id
    @Column(name = JpaConst.INGR_COL_ID)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    /**
     * 食材名
     */
    @Column(name = JpaConst.INGR_COL_NAME, length = 255, nullable = false)
    private String name;

    /**
     * メーカー
     */
    @Column(name = JpaConst.INGR_COL_MAKER, nullable = true)
    private String maker;

    /**
     * 販売店
     */
    @Column(name = JpaConst.INGR_COL_SALER, nullable = true)
    private String saler;

    /**
     * 単価
     */
    @Column(name = JpaConst.INGR_COL_PRICE, nullable = true)
    private Double price;

    /**
     * 内容量
     */
    @Column(name = JpaConst.INGR_COL_AMOUNT, nullable = true)
    private Double amount;

    /**
     * 内容量の単位
     */
    @Column(name = JpaConst.INGR_COL_AMOUNT_U, nullable = true)
    private String amount_u;


    /**
     * エネルギー
     */
    @Column(name = JpaConst.INGR_COL_ENERGY, nullable = true)
    private Double energy;

    /**
     * アレルギー
     */
    @OneToMany(mappedBy = "ingredient")
    @Column(name = JpaConst.INGR_COL_ALLERGY, nullable = true)
    private List<Allergy> allergy;
    //@OneToManyにすることで、
    //Ingredientクラスを取得するだけで、ingredientテーブルと関連するallergyテーブルのデータを取得することが可能です。
    //（allergyテーブルに関連するデータがない場合アレルギーのフィールドはnullになります)

    /**
     * 削除された従業員かどうか（現役：0、削除済み：1）
     */
    @Column(name = JpaConst.INGR_COL_DELETE_FLAG, nullable = false)
    private Integer deleteFlag;
}
