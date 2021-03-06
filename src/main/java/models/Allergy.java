
package models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
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
@Table(name = JpaConst.TABLE_ALGY)

@Getter //全てのクラスフィールドについてgetterを自動生成する(Lombok)
@Setter //全てのクラスフィールドについてsetterを自動生成する(Lombok)
@NoArgsConstructor //引数なしコンストラクタを自動生成する(Lombok)
@AllArgsConstructor //全てのクラスフィールドを引数にもつ引数ありコンストラクタを自動生成する(Lombok)
@Entity
public class Allergy {
    /**
     * id
     */
    @Id
    @Column(name = JpaConst.ALGY_COL_ID)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    /**
     * 食材ID
     */
    @ManyToOne
    @JoinColumn(name = JpaConst.ALGY_COL_INGREDIENT, nullable = false)
    private Ingredient ingredient; //IngredientクラスのIDを持たせる

    /**
     * アレルギー品目ID
     */
    @ManyToOne
    @JoinColumn(name = JpaConst.ALGY_COL_ITEM, nullable = true)
    private AllergyItem item; //AllergyItemクラスのIDを持たせる

   }
