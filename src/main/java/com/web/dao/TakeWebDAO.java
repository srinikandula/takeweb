package com.web.dao;

import com.web.model.Account;

import java.util.List;

/**
 * Created by skandula on 3/12/16.
 */
public interface TakeWebDAO<T> {
    public void update(T t);
    public void delete(T t);
    public T find(int id);
    public List<T> findAll();
    public void create(T t);

}
