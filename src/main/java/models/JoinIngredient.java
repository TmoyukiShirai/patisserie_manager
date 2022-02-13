
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
 * アレルギーデータのDTOモデル
 *
 */

@Table(name = JpaConst.TABLE_J_INGR)
@NamedQueries({
    @NamedQuery(
            name = JpaConst.Q_J_INGR_GET_ALL,
            query = JpaConst.Q_J_INGR_GET_ALL_DEF),
    @NamedQuery(
            name = JpaConst.Q_J_INGR_GET_ITS,
            query = JpaConst.Q_J_INGR_GET_ITS_DEF),
})

@Getter //全てのクラスフィールドについてgetterを自動生成する(Lombok)
@Setter //全てのクラスフィールドについてsetterを自動生成する(Lombok)
@NoArgsConstructor //引数なしコンストラクタを自動生成する(Lombok)
@AllArgsConstructor //全てのクラスフィールドを引数にもつ引数ありコンストラクタを自動生成する(Lombok)
@Entity
public class JoinIngredient {
    /**
     * id
     */
    @Id
    @Column(name = JpaConst.J_INGR_COL_ID)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    /**
     * レシピID
     */
    @ManyToOne
    @JoinColumn(name = JpaConst.J_INGR_COL_RECIPE, nullable = false)
    private Recipe recipe; //RecipeクラスのIDを持たせる

    /**
     * 食材名
     */
    @ManyToOne
    @JoinColumn(name = JpaConst.J_INGR_COL_INGREDIENT, nullable = false)
    private Ingredient ingredient;

    /**
     * レシピ上の分量
     */
    @Column(name = JpaConst.J_INGR_COL_AMOUNT, nullable = true)
    private Double amount;

    /**
     * レシピ上の分量の単位
     */
    @Column(name = JpaConst.J_INGR_COL_AMOUNT_U, nullable = true)
    private String amount_u;



   }
