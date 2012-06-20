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
package org.smog3.annotation;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

/**
 * Annotation used to specify Form class to Controller class
 * 
 * @author abemasafumi
 * 
 */
@Retention(RetentionPolicy.RUNTIME)
public @interface Form {
    public String value() default "";
}
