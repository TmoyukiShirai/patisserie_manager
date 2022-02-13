package models.validators;

import java.util.ArrayList;
import java.util.List;

import actions.views.IngredientView;
import constants.MessageConst;

/**
 * 食材インスタンスに設定されている値のバリデーションを行うクラス
 */
public class IngredientValidator {

    /**
     * 食材インスタンスの各項目についてバリデーションを行う
     * @param rv 食材インスタンス
     * @return エラーのリスト
     */
    public static List<String> validate(IngredientView iv) {
        List<String> errors = new ArrayList<String>();

        //食材名のチェック
        String nameError = validateName(iv.getName());
        if (!nameError.equals("")) {
            errors.add(nameError);
        }

        return errors;
    }

    /**
     * 食材名に入力値があるかをチェックし、入力値がなければエラーメッセージを返却
     * @param name 食材名
     * @return エラーメッセージ
     */
    private static String validateName(String name) {
        if (name == null || name.equals("")) {
            return MessageConst.E_NO_INGR_NAME.getMessage();
        }

        //入力値がある場合は空文字を返却
        return "";
    }


}