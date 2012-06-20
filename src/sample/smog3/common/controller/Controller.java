package sample.smog3.common.controller;

import org.smog3.form.Form;

import sample.smog3.bean.Parameters;

/**
 * ��{�R���g���[���[�N���X
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
     * ���s����
     * 
     * @param params
     *            �p�����[�^�Q
     * @return �J�ڐ�̃p�X
     */
    protected abstract String run(Parameters params);

}
