/*=============================================================
 * Copyright(C) 2012 Masafumi Abe. All Rights Reserved.
 *
 * ------------------------------------------------------------
 * Smog3
 *
 * ------------------------------------------------------------
 * 2012/01/28    Masafumi Abe        新規作成
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

        // フォーム作成
        Form form = null;
        // アノテーションからフォーム名の取得
        String value =
            this
                .getClass()
                .getAnnotation(org.smog3.annotation.Form.class)
                .value();

        // フォームの指定があれば
        if (value != null && value.length() != 0) {
            // パッケージ名の取得
            String pack = this.getClass().getPackage().getName();
            String[] packs = pack.split(Const.PACKAGE_CONTROLLER);
            // フォームパッケージ作成
            pack = packs[0] + Const.PACKAGE_FORM + packs[1] + ".";

            // クラス名からClassを取得
            Class c = Class.forName(pack + value);

            if (c != null) {
                // フォームクラスのインスタンス取得
                form = (Form) c.newInstance();
            }
        }

        if (form != null) {
            // フォーム初期化
            validate(form);
            // validate メソッドの実行
            if (errors == null) {
                errors = new Errors();
            }
            form.validate(errors);
        }

        request.setAttribute("f", form);
        request.setAttribute(Const.ERROR_MESSAGE, errors == null
            ? new Errors()
            : errors);
        // ヘッダーからコンテキストパスを取得
        request.setAttribute("contextPath", request.getHeader("HOST"));

        // 下位クラスのメソッド実行
        String path = run(form);

        Navigation nav = path != null ? forward(path) : null;
        return nav;
    }

    /**
     * Validate parameters specified by annotation</br> アノテーション指定がある変数のチェック
     * 
     * @param form
     *            Form class instance
     */
    private void validate(Form form) {
        Validators v = new Validators(request);

        // 変数の取得
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
            // formへ代入
            BeanUtil.copy(request, form);
        } else {
            // エラーの取得
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

        if (type == Type.NUMBER) {
            return v.numberType("###");
        } else if (type == Type.BYTE) {
            return v.byteType();
        } else if (type == Type.SHORT) {
            return v.shortType();
        } else if (type == Type.INTEGER) {
            return v.integerType();
        } else if (type == Type.LONG) {
            return v.longType();
        } else if (type == Type.FLOAT) {
            return v.floatType();
        } else if (type == Type.DOUBLE) {
            return v.doubleType();
        } else if (type == Type.STRING) {
            return null;
        } else if (type == Type.CHARACTER) {
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
