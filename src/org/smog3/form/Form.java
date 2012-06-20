/*=============================================================
 * Copyright(C) 2012 Masafumi Abe. All Rights Reserved.
 *
 * ------------------------------------------------------------
 * Smog3
 *
 * ------------------------------------------------------------
 * 2012/01/28    Masafumi Abe        êVãKçÏê¨
 *
===============================================================*/
package org.smog3.form;

import java.io.Serializable;

import org.slim3.controller.validator.Errors;

/**
 * This class is the super class of Form class and used to map HTTP form
 * parameters.</br> All Form classes have to extend this class.
 * 
 * @author abemasafumi
 * 
 */
@SuppressWarnings("serial")
public abstract class Form implements Serializable {

    /**
     * Validate the parameter. This method does not run. If you want to validate
     * the parameters, override this method.
     * 
     * @param errors
     *            Error message
     */
    public void validate(Errors errors) {
    }

}
