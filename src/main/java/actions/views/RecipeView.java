
package actions.views;

import java.util.List;

//import java.time.LocalDate;
//import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import models.JoinIngredient;
import models.ProcessRecipe;

/**
 * レシピ情報について画面の入力値・出力値を扱うViewモデル
 *
 */
@Getter //全てのクラスフィールドについてgetterを自動生成する(Lombok)
@Setter //全てのクラスフィールドについてsetterを自動生成する(Lombok)
@NoArgsConstructor //引数なしコンストラクタを自動生成する(Lombok)
@AllArgsConstructor //全てのクラスフィールドを引数にもつ引数ありコンストラクタを自動生成する(Lombok)
public class RecipeView {

    /**
     * id
     */
    private Integer id;

    /**
     * レシピのタイトル
     */
    private String title;

    /**
     * レシピの分類
     */
    private String type;

    /**
     * レシピの人数
     */
    private Double number;

    /**
     * 食材
     */
    private List<JoinIngredient> joinIngredient;

    /**
     * 工程
     */
    private List<ProcessRecipe> processRecipe;

    /**
     * 削除されたレシピかどうか（現役：0、削除済み：1）
     */
    private Integer deleteFlag;


}