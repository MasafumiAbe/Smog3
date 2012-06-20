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

/**
 * This annotation is used to specify the type to form parameter.
 * 
 * @author abemasafumi
 * 
 */
public @interface Type {

    /**
     * Specify the value type
     * 
     * @return
     */
    public String type();

    /** This is used if you want to check only whether value is number. */
    public static String NUMBER = "number";

    /** byte type */
    public static String BYTE = "byte";

    /** short type */
    public static String SHORT = "short";

    /** int type */
    public static String INTEGER = "integer";

    /** long type */
    public static String LONG = "long";

    /** float type */
    public static String FLOAT = "float";

    /** double type */
    public static String DOUBLE = "double";

    /** string type */
    public static String STRING = "string";

    /** char type */
    public static String CHARACTER = "char";

    /** e-mail type */
    public static String E_MAIL = "e-mail";

}
