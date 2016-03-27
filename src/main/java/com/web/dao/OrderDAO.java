package com.web.DAO;

import java.util.List;


public interface OrderDAO<T> {

    public void updateOrder(T t);
    public void deleteOrder(T t);
    public int findOrderAmount(T t);
    public List<T> findOrders();
    public void createOrder(T t, String username);


    int checkID(int orderId);
}