package models.validators;

import java.util.ArrayList;
import java.util.List;

import actions.views.ProcessRecipeView;
import constants.MessageConst;

/**
 * 工程説明文インスタンスに設定されている値のバリデーションを行うクラス
 */
public class ProcessRecipeValidator {

    /**
     * 工程説明文インスタンスの各項目についてバリデーションを行う
     * @param rv 工程説明文インスタンス
     * @return エラーのリスト
     */
    public static List<String> validate(ProcessRecipeView rv) {
        List<String> errors = new ArrayList<String>();

        //工程説明文名のチェック
        String descriptionError = validateDescription(rv.getDescription());
        if (!descriptionError.equals("")) {
            errors.add(descriptionError);
        }

        return errors;
    }

    /**
     * 工程説明文名に入力値があるかをチェックし、入力値がなければエラーメッセージを返却
     * @param name 工程説明文名
     * @return エラーメッセージ
     */
    private static String validateDescription(String description) {
        if (description == null || description.equals("")) {
            return MessageConst.E_NO_PR_DESCRIPTION.getMessage();
        }

        //入力値がある場合は空文字を返却
        return "";
    }
}