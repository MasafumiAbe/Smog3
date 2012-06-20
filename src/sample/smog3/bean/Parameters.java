package sample.smog3.bean;

import org.smog3.form.Form;

/**
 * パラメータ保存用bean
 * 
 * @author abemasafumi
 * 
 */
public class Parameters {

    /** フォーム */
    private Form form;

    /**
     * formを取得する
     * 
     * @return form
     */
    public Form getForm() {
        return form;
    }

    /**
     * formを設定する
     * 
     * @param form
     */
    public void setForm(Form form) {
        this.form = form;
    }

}
