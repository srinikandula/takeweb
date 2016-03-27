package com.web.keerthi;

import java.util.List;

/**
 * Created by CrazyNaveen on 3/13/16.
 */
public interface DaoInterface<T> {

    public void update(T t);
    public void delete(T t);
    public T find(int id);
    public List<T> findAll();
    public void create(T t);



}
