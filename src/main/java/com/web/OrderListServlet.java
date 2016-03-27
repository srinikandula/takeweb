package com.web;


import com.web.model.Order;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import com.web.DAO.OrderDAO;
import com.web.DAO.OrderDAOImpl;
import com.web.model.Order;
import org.springframework.context.ApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

/**
 * Created by skandula on 3/10/16.
 */
//@WebServlet(urlPatterns = {"/accountListServlet"})
public class OrderListServlet extends HttpServlet {
    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        ServletContext ctxt = req.getSession().getServletContext();
        ApplicationContext appContext = WebApplicationContextUtils.getRequiredWebApplicationContext(ctxt);
        OrderDAO dao = (OrderDAO)appContext.getBean("OrderDAO");


        List<Order> orders = dao.findOrders();
        req.setAttribute("orders", orders);

        RequestDispatcher dispatcher = req.getRequestDispatcher("orderList.jsp");
        dispatcher.forward(req, resp);
    }
   /* private List<Order> findAllOrders() {
        List<Order> orders = new ArrayList<>();
        try {
            //load the driver
            Class.forName("org.postgresql.Driver");
            Connection conn = DriverManager.getConnection("jdbc:postgresql://localhost:5432/postgres", "postgres", "gouthami26");
            Statement statement = conn.createStatement();
            ResultSet rs = statement.executeQuery("select * from public.\"ORDER\"");
            while(rs.next()){
                Order order = new Order();
                order.setOrderId(rs.getInt("orderId"));
                order.setAmount(rs.getInt("Amount"));
                order.setQuantity(rs.getInt("quantity"));
                order.setOrderprice(rs.getInt("orderprice"));
               orders.add(order);
            }
            rs.close();
            statement.close();
            conn.close();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return orders;
    }*/
}
