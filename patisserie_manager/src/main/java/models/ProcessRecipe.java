
package models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
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
 * 工程データのDTOモデル
 *
 */
@Table(name = JpaConst.TABLE_PR)
@NamedQueries({
    @NamedQuery(
            name = JpaConst.Q_PR_GET_ALL,
            query = JpaConst.Q_PR_GET_ALL_DEF),
    @NamedQuery(
            name = JpaConst.Q_PR_GET_ALL_MINE,
            query = JpaConst.Q_PR_GET_ALL_MINE_DEF),

})

@Getter //全てのクラスフィールドについてgetterを自動生成する(Lombok)
@Setter //全てのクラスフィールドについてsetterを自動生成する(Lombok)
@NoArgsConstructor //引数なしコンストラクタを自動生成する(Lombok)
@AllArgsConstructor //全てのクラスフィールドを引数にもつ引数ありコンストラクタを自動生成する(Lombok)
@Entity
public class ProcessRecipe {
    /**
     * id
     */
    @Id
    @Column(name = JpaConst.PR_COL_ID)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    /**
     * レシピid
     */
    @ManyToOne
    @JoinColumn(name = JpaConst.PR_COL_RECIPE, nullable = false)
    private Recipe recipe;
    /**
     * 工程説明文
     */
    @Lob
    @Column(name = JpaConst.PR_COL_DESCRIPTION, nullable = true)
    private String description;

    /**
     * 調理器具名
     */
    @Column(name = JpaConst.PR_COL_UTENSIL, nullable = true)
    private String utensil;

    /**
     * 温度
     */
    @Column(name = JpaConst.PR_COL_TEMPERATURE, nullable = true)
    private Double temperature;

    /**
     * 速度(１〜４速）
     */
    @Column(name = JpaConst.PR_COL_SPEED, nullable = true)
    private String speed;

    /**
     * 時間
     */
    @Column(name = JpaConst.PR_COL_TIME, nullable = true)
    private Double time;

    /**
     * 削除されたかどうか（現役：0、削除済み：1）
     */
    @Column(name = JpaConst.PR_COL_DELETE_FLAG, nullable = false)
    private Integer deleteFlag;

}
