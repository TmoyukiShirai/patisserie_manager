package models.validators;

import java.util.ArrayList;
import java.util.List;

import actions.views.RecipeView;
import constants.MessageConst;

/**
 * レシピインスタンスに設定されている値のバリデーションを行うクラス
 */
public class RecipeValidator {

    /**
     * レシピインスタンスの各項目についてバリデーションを行う
     * @param rv レシピインスタンス
     * @return エラーのリスト
     */
    public static List<String> validate(RecipeView rv) {
        List<String> errors = new ArrayList<String>();

        //レシピ名のチェック
        String titleError = validateTitle(rv.getTitle());
        if (!titleError.equals("")) {
            errors.add(titleError);
        }
       //人数のチェック
        String numberError = validateNumber(rv.getNumber());
        if (!numberError.equals("")) {
            errors.add(numberError);
        }

        return errors;
    }

    /**
     * レシピ名に入力値があるかをチェックし、入力値がなければエラーメッセージを返却
     * @param title レシピ名
     * @return エラーメッセージ
     */
    private static String validateTitle(String title) {
        if (title == null || title.equals("")) {
            return MessageConst.E_NO_RECI_TITLE.getMessage();
        }

        //入力値がある場合は空文字を返却
        return "";
    }
    /**
     * 人数に入力値があるかをチェックし、入力値がなければエラーメッセージを返却
     * @param number 人数
     * @return エラーメッセージ
     */
    private static String validateNumber(Double number) {
        if (number == null || number.equals(0.0)) {
            return MessageConst.E_NO_RECI_NUMBER.getMessage();
        }

        //入力値がある場合は空文字を返却
        return "";
    }
}