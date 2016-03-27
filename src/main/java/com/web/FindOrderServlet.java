/**
 * Created by skandula on 3/9/16.
 */
package com.web;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.*;
import com.web.DAO.OrderDAO;
import com.web.DAO.OrderDAOImpl;
import com.web.model.Order;
import org.springframework.context.ApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

public class FindOrderServlet extends HttpServlet{

    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //super.doGet(req, resp);


        //Order order = new Order();

        String orderId = req.getParameter("orderId");

        ServletContext ctxt = req.getSession().getServletContext();
        ApplicationContext appContext = WebApplicationContextUtils.getRequiredWebApplicationContext(ctxt);
        OrderDAO dao = (OrderDAO)appContext.getBean("OrderDAO");
        Order order = (Order)appContext.getBean("Order");

        order.setOrderId(Integer.parseInt(orderId));
        int amount = dao.findOrderAmount(order);
        if(amount == -1){
            //matching account is not found or error finding account balance
            PrintWriter pw = resp.getWriter();
            pw.write("matching account is not found or error finding account balance");
        }else{
            // balance information found
            req.setAttribute("amount", amount);
            RequestDispatcher dispatcher = req.getRequestDispatcher("orderAmount.jsp");
            dispatcher.forward(req, resp);
        }

    }
    /*private int findOrderAmount(int orderId) {
        try {
            //load the driver
            Class.forName("org.postgresql.Driver");
            Connection conn = DriverManager.getConnection("jdbc:postgresql://localhost:5432/postgres", "postgres", "gouthami26");

            PreparedStatement preparedStatement = conn.prepareStatement("SELECT \"Amount\" FROM public.\"ORDER\" WHERE \"orderID\"=?");
            preparedStatement.setInt(1, orderId);
            ResultSet resultSet = preparedStatement.executeQuery();
            int amount = -1;
            if(resultSet.next()){
               amount = resultSet.getInt("Amount");
            }
            resultSet.close();
            preparedStatement.close();
            conn.close();
            return amount;
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return -1;
    }*/
}
