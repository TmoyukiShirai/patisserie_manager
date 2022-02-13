
package actions.views;


//import java.time.LocalDate;
//import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 *　食材情報について画面の入力値・出力値を扱うViewモデル
 *
 */
@Getter //全てのクラスフィールドについてgetterを自動生成する(Lombok)
@Setter //全てのクラスフィールドについてsetterを自動生成する(Lombok)
@NoArgsConstructor //引数なしコンストラクタを自動生成する(Lombok)
@AllArgsConstructor //全てのクラスフィールドを引数にもつ引数ありコンストラクタを自動生成する(Lombok)
public class ProcessRecipeView {

    /**
     * id
     */
    private Integer id;

    /**
     * レシピid
     */
    private String r_id;

    /**
     * 工程説明文
     */
    private String description;

    /**
     * 調理器具名
     */
    private String utensil;

    /**
     * 温度
     */
    private Double temperature;

    /**
     * 速度(１〜４速）
     */
    private String speed;

    /**
     * 時間
     */
    private Double time;

    /**
     * 削除されたかどうか（現役：0、削除済み：1）
     */
    private Integer deleteFlag;

}