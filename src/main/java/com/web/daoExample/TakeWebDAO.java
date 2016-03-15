package com.web.daoExample;


import java.util.List;

/**
 * Created by devendra on 3/13/2016.
 */

    public  interface TakeWebDAO <T> {
    public  void update(T t);
    public  void delete(T t);
    public  T find (int id);
    public List<T> finaAll();
    public void create(T t);
}
