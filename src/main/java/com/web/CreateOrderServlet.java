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
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import com.web.DAO.OrderDAO;
import com.web.DAO.OrderDAOImpl;
import com.web.model.Order;
import com.web.model.User;
import org.springframework.context.ApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

public class CreateOrderServlet extends HttpServlet{

    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //super.doGet(req, resp);


        //Order order = new Order();

        String orderId = req.getParameter("orderId");
        String amount = req.getParameter("amount");
        String quantity = req.getParameter("quantity");
        String orderprice = req.getParameter("orderprice");

        User user = (User) (req.getSession().getAttribute("user"));
        String username = user.getUserName();



        System.out.println("Create order servlet is called with values "+ orderId+orderprice+quantity + amount);
        ServletContext ctxt = req.getSession().getServletContext();
        ApplicationContext appContext = WebApplicationContextUtils.getRequiredWebApplicationContext(ctxt);
        OrderDAO dao = (OrderDAO)appContext.getBean("OrderDAO");

        Order order = (Order)appContext.getBean("Order");
        order.setOrderId(Integer.parseInt(orderId));
        order.setAmount(Integer.parseInt(amount));
        order.setQuantity(Integer.parseInt(quantity));
        order.setOrderprice(Integer.parseInt(orderprice));


        dao.createOrder(order , username);
        RequestDispatcher rd = req.getRequestDispatcher("/OrderListServlet");
        rd.forward(req,resp);
       /* PrintWriter pw = resp.getWriter();
        if(insertedRecords == 1){

            pw.write("Order is placed Successfully");
        }
        else {
            pw.write("Error in placing the order");
        }
        //RequestDispatcher dispatcher = req.getRequestDispatcher("/accountListServlet");
        //dispatcher.forward(req,resp);
        //ntWriter pw = res.getWriter();
        //write("Servlet is creates successfully");*/
    }

   /* private int createOrder(int orderId,int amount,int quantity,int orderprice){
        try {
            //load the driver
            Class.forName("org.postgresql.Driver");
            Connection conn = DriverManager.getConnection("jdbc:postgresql://localhost:5432/postgres", "postgres", "gouthami26");
            PreparedStatement preparedStatement = conn.prepareStatement("INSERT INTO public.\"ORDER\"(\"orderID\", \"Amount\", quantity, orderprice)VALUES (?, ?, ?, ?)");
            preparedStatement.setInt(1, orderId);
            preparedStatement.setInt(2, amount);
            preparedStatement.setInt(3, quantity);
            preparedStatement.setInt(4, orderprice);
            int insertedRecords = preparedStatement.executeUpdate();


            preparedStatement.close();
            conn.close();
            return insertedRecords;
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    return 0;
}*/

}