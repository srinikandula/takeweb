/**
 * Created by skandula on 3/9/16.
 */
package com.web;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import com.web.DAO.OrderDAO;
import com.web.DAO.OrderDAOImpl;
import com.web.model.Order;
import org.springframework.context.ApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

/**
 * Created by skandula on 3/10/16.
 */
//@WebServlet(urlPatterns = {"/deleteAccountServlet"})
public class DeleteOrderServlet extends HttpServlet{

    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //super.doGet(req, resp);

//        Order order = new Order();

        String orderId = req.getParameter("orderId");


        ServletContext ctxt = req.getSession().getServletContext();
        ApplicationContext appContext = WebApplicationContextUtils.getRequiredWebApplicationContext(ctxt);
        OrderDAO dao = (OrderDAO)appContext.getBean("OrderDAO");
        Order order = (Order)appContext.getBean("Order");

        order.setOrderId(Integer.parseInt(orderId));

        dao.deleteOrder(order);
        RequestDispatcher dispatcher = req.getRequestDispatcher("/OrderListServlet");
        dispatcher.forward(req,resp);
    }

   /* private void deleteOrder(int orderId) {
        try {
            //load the driver
            Class.forName("org.postgresql.Driver");
            Connection conn = DriverManager.getConnection("jdbc:postgresql://localhost:5432/postgres", "postgres", "gouthami26");
            PreparedStatement preparedStatement = conn.prepareStatement("DELETE FROM public.\"ORDER\" WHERE \"orderID\"=?");
            preparedStatement.setInt(1, orderId);
            int insertedRecords = preparedStatement.executeUpdate();
            preparedStatement.close();
            conn.close();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }*/
}
