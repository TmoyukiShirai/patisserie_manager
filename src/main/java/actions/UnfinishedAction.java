package actions;

import java.io.IOException;

import javax.servlet.ServletException;

import constants.ForwardConst;

/**
 * エラー発生時の処理行うActionクラス
 *
 */
public class UnfinishedAction extends ActionBase {

    /**
     * 共通エラー画面「未完成ページ。製作中です。見つかりませんでした。」を表示する
     */
    @Override
    public void process() throws ServletException, IOException {

        //エラー画面を表示
        forward(ForwardConst.FW_ERR_UNFINISHED);

    }
}
