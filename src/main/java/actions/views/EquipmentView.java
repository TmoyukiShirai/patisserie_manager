
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
public class EquipmentView {

    /**
     * id
     */
    private Integer id;

    /**
     * 食材名
     */
    private String name;

    /**
     * メーカー
     */
    private String maker;

    /**
     * 販売店
     */
    private String saler;

    /**
     * 単価
     */
    private Double price;

    /**
     * 内容量
     */
    private Double amount;

    /**
     * 内容量の単位
     */
    private String amount_u;

    /**
     * 削除された従業員かどうか（現役：0、削除済み：1）
     */
    private Integer deleteFlag;

}