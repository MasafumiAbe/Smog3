/*=============================================================
 * Copyright(C) 2012 Masafumi Abe. All Rights Reserved.
 *
 * ------------------------------------------------------------
 * Smog3
 *
 * ------------------------------------------------------------
 * 2012/01/28    Masafumi Abe        �V�K�쐬
 *
===============================================================*/
package org.smog3.controller;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

import org.slim3.controller.Navigation;
import org.slim3.controller.validator.AbstractValidator;
import org.slim3.controller.validator.Errors;
import org.slim3.controller.validator.Validators;
import org.slim3.util.BeanUtil;
import org.smog3.annotation.Require;
import org.smog3.annotation.Type;
import org.smog3.common.Const;
import org.smog3.form.Form;

/**
 * The base Controller
 * 
 * @author abemasafumi
 * 
 */
@SuppressWarnings("rawtypes")
public abstract class Controller extends org.slim3.controller.Controller {

    @Override
    protected final Navigation run() throws Exception {

        // �t�H�[���쐬
        Form form = null;
        // �A�m�e�[�V���������t�H�[�����̎擾
        String value =
            this
                .getClass()
                .getAnnotation(org.smog3.annotation.Form.class)
                .value();

        // �t�H�[���̎w�肪������
        if (value != null && value.length() != 0) {
            // �p�b�P�[�W���̎擾
            String pack = this.getClass().getPackage().getName();
            String[] packs = pack.split(Const.PACKAGE_CONTROLLER);
            // �t�H�[���p�b�P�[�W�쐬
            pack = packs[0] + Const.PACKAGE_FORM + packs[1] + ".";

            // �N���X������Class���擾
            Class c = Class.forName(pack + value);

            if (c != null) {
                // �t�H�[���N���X�̃C���X�^���X�擾
                form = (Form) c.newInstance();
            }
        }

        if (form != null) {
            // �t�H�[��������
            validate(form);
            // validate ���\�b�h�̎��s
            if (errors == null) {
                errors = new Errors();
            }
            form.validate(errors);
        }

        request.setAttribute("f", form);
        request.setAttribute(Const.ERROR_MESSAGE, errors == null
            ? new Errors()
            : errors);
        // �w�b�_�[�����R���e�L�X�g�p�X���擾
        request.setAttribute("contextPath", request.getHeader("HOST"));

        // ���ʃN���X�̃��\�b�h���s
        String path = run(form);

        Navigation nav = path != null ? forward(path) : null;
        return nav;
    }

    /**
     * Validate parameters specified by annotation</br> �A�m�e�[�V�����w�肪�����ϐ��̃`�F�b�N
     * 
     * @param form
     *            Form class instance
     */
    private void validate(Form form) {
        Validators v = new Validators(request);

        // �ϐ��̎擾
        Field[] fields = form.getClass().getDeclaredFields();
        List<AbstractValidator> validators = new ArrayList<AbstractValidator>();

        for (Field field : fields) {
            Require require = field.getAnnotation(Require.class);
            Type t = field.getAnnotation(Type.class);

            if (require != null) {
                validators.add(v.required());
            }

            // Type check
            if (t != null) {
                validators.add(checkValidatorType(v, t.type()));
            }

            // add to validator
            v.add(
                field.getName(),
                validators.toArray(new AbstractValidator[] {}));

            validators.clear();
        }

        if (v.validate()) {
            // form�֑���
            BeanUtil.copy(request, form);
        } else {
            // �G���[�̎擾
            errors = v.getErrors();
        }
    }

    /**
     * Check the type and return AbstractValidator
     * 
     * @param v
     * @param type
     * @return
     */
    private AbstractValidator checkValidatorType(Validators v, String type) {

        if (type.equals(Type.NUMBER)) {
            return v.numberType("###");
        } else if (type.equals(Type.BYTE)) {
            return v.byteType();
        } else if (type.equals(Type.SHORT)) {
            return v.shortType();
        } else if (type.equals(Type.INTEGER)) {
            return v.integerType();
        } else if (type.equals(Type.LONG)) {
            return v.longType();
        } else if (type.equals(Type.FLOAT)) {
            return v.floatType();
        } else if (type.equals(Type.DOUBLE)) {
            return v.doubleType();
        } else if (type.equals(Type.STRING)) {
            return null;
        } else if (type.equals(Type.CHARACTER)) {
            return null;
        }

        return null;
    }

    /**
     * Override to run this class
     * 
     * @param form
     *            Form class instance that has request parameter. If Form class
     *            is not specified by Form annotation, this parameter is null.
     * @return path the controller-relative path
     * @throws Exception
     */
    protected abstract String run(Form form) throws Exception;

}
