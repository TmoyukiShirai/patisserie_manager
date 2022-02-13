
package actions.views;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import models.Ingredient;

/**
 *　発注履歴情報について画面の入力値・出力値を扱うViewモデル
 *
 */
@Getter //全てのクラスフィールドについてgetterを自動生成する(Lombok)
@Setter //全てのクラスフィールドについてsetterを自動生成する(Lombok)
@NoArgsConstructor //引数なしコンストラクタを自動生成する(Lombok)
@AllArgsConstructor //全てのクラスフィールドを引数にもつ引数ありコンストラクタを自動生成する(Lombok)
public class OrderHistoryView {

    /**
     * id
     */
    private Integer id;

    /**
     * 発注日
     */
    private String orderedDate;
    /**
     * 納品日
     */
    private String deliDate;

    /**
     * 食材名
     */
    private Ingredient ingredient;

    /**
     * 注文数
     */
    private Double numberOfOrder;

    /**
     * 金額
     */
    private Double price;

    /**
     * 発注者
     */
    private EmployeeView orderedEmp;

}
