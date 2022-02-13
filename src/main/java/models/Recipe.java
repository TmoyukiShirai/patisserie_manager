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
@Table(name = JpaConst.TABLE_RECI)
@NamedQueries({
    @NamedQuery(
            name = JpaConst.Q_RECI_GET_ALL,
            query = JpaConst.Q_RECI_GET_ALL_DEF),
    @NamedQuery(
            name = JpaConst.Q_RECI_COUNT,
            query = JpaConst.Q_RECI_COUNT_DEF),
    @NamedQuery(
            name = JpaConst.Q_RECI_GET_TITLE_BY_SEARCH,
            query = JpaConst.Q_RECI_GET_TITLE_BY_SEARCH_DEF),
    @NamedQuery(
            name = JpaConst.Q_RECI_COUNT_TITLE_BY_SEARCH,
            query = JpaConst.Q_RECI_COUNT_TITLE_BY_SEARCH_DEF),
    @NamedQuery(
            name = JpaConst.Q_RECI_GET_TYPE_BY_SEARCH,
            query = JpaConst.Q_RECI_GET_TYPE_BY_SEARCH_DEF),
    @NamedQuery(
            name = JpaConst.Q_RECI_COUNT_TYPE_BY_SEARCH,
            query = JpaConst.Q_RECI_COUNT_TYPE_BY_SEARCH_DEF),
    @NamedQuery(
            name = JpaConst.Q_RECI_INGR_DELETE,
            query = JpaConst.Q_RECI_INGR_DELETE_DEF),
    @NamedQuery(
            name = JpaConst.Q_RECI_PR_DELETE,
            query = JpaConst.Q_RECI_PR_DELETE_DEF),
})

@Getter //全てのクラスフィールドについてgetterを自動生成する(Lombok)
@Setter //全てのクラスフィールドについてsetterを自動生成する(Lombok)
@NoArgsConstructor //引数なしコンストラクタを自動生成する(Lombok)
@AllArgsConstructor //全てのクラスフィールドを引数にもつ引数ありコンストラクタを自動生成する(Lombok)
@Entity
public class Recipe {
    /**
     * id
     */
    @Id
    @Column(name = JpaConst.RECI_COL_ID)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    /**
     * レシピ名
     */
    @Column(name = JpaConst.RECI_COL_TITLE, length = 255, nullable = false)
    private String title;

    /**
     * 分類
     */
    @Column(name = JpaConst.RECI_COL_TYPE, nullable = false)
    private String type;

    /**
     * 人数
     */
    @Column(name = JpaConst.RECI_COL_NUMBER, nullable = false)
    private Double number;

    /**
     * 食材・分量
     *
     */
    @OneToMany(mappedBy = "recipe")
    @Column(name = JpaConst.RECI_COL_J_INGREDIENT, nullable = true)
    private List<JoinIngredient> joinIngredient;
  //@OneToManyにすることで、
    //Recipeクラスを取得するだけで、recipeテーブルと関連するjoinIngredientテーブルのデータを取得することが可能です。
    //（joinIngredientテーブルに関連するデータがない場合amount,amount_uのフィールドはnullになります)

    @OneToMany(mappedBy = "recipe")
    @Column(name = JpaConst.RECI_COL_PR, nullable = true)
    private List<ProcessRecipe> processRecipe;
    /**
     * 削除されたレシピかどうか（現役：0、削除済み：1）
     */
    @Column(name = JpaConst.RECI_COL_DELETE_FLAG, nullable = false)
    private Integer deleteFlag;

}

