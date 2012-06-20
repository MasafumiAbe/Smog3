package sample.smog3.common.controller;

import org.smog3.form.Form;

import sample.smog3.bean.Parameters;

/**
 * 基本コントローラークラス
 * 
 * @author abemasafumi
 * 
 */
public abstract class Controller extends
        org.smog3.controller.Controller {

    @Override
    protected String run(Form form) throws Exception {
        Parameters params = new Parameters();
        params.setForm(form);

        return run(params);
    }

    /**
     * 実行処理
     * 
     * @param params
     *            パラメータ群
     * @return 遷移先のパス
     */
    protected abstract String run(Parameters params);

}
