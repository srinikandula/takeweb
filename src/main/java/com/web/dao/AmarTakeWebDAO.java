package com.web.dao;

import java.util.List;

/**
 * Created by Amar on 3/14/2016.
 */
public interface AmarTakeWebDAO<T> {
    public void update(T t);
    public void delete(T t);
    public T find(int accnum);
    public List<T> findAll();
    public void create(T t);
}
