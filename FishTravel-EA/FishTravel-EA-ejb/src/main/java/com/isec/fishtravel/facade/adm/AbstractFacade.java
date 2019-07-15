/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.isec.fishtravel.facade.adm;

import com.isec.fishtravel.dao.AbstractDAO;
import java.util.List;

/**
 *
 * @author ljordao-dev
 * @param <T>
 */
public abstract class AbstractFacade<T> {
    
    protected abstract AbstractDAO<T> getDAO();

    public void create(T entity) {
        getDAO().create(entity);
    }

    public void edit(T entity) {
        getDAO().edit(entity);
    }

    public void remove(T entity) {
        getDAO().remove(entity);
    }

    public T find(Object id) {
        return getDAO().find(id);
    }

    public List<T> findAll() {
        return getDAO().findAll();
    }

    public List<T> findRange(int[] range) {
        return getDAO().findRange(range);
    }

    public int count() {
        return getDAO().count();
    }
    
}
