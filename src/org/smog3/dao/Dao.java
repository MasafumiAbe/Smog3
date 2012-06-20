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

package org.smog3.dao;

import java.util.List;

import org.slim3.datastore.DaoBase;
import org.slim3.datastore.Datastore;
import org.slim3.datastore.ModelMeta;
import org.slim3.datastore.SortCriterion;

/**
 * Base class of Dao class
 * 
 * @author abemasafumi
 * 
 * @param <T>
 *            model type
 */
public class Dao<T> extends DaoBase<T> {

    /**
     * Select all data.
     * 
     * @param meta
     *            ModelMeta cleass's object
     * @param sorts
     *            the sort criteria.
     * @return result
     */
    public List<T> selectAll(ModelMeta<T> meta, SortCriterion... sorts) {
        return Datastore.query(meta).sort(sorts).asList();
    }

}
