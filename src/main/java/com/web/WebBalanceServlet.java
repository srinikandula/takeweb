package com.web;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.xml.ws.Service;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.*;

/**
 * Created by devendra on 3/10/2016.
 */
public class WebBalanceServlet extends HttpServlet {

    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String firstName =req.getParameter("fname");
        String bankName = req.getParameter("bname");
        int balance = checkBalance(firstName, bankName);
        if(balance == -1){
            PrintWriter pw = resp.getWriter();
            pw.write("Records does not exits with those details");
        }else {
            req.setAttribute("balance",balance);
            RequestDispatcher requestDispatcher = req.getRequestDispatcher("balance.jsp");
            requestDispatcher.forward(req,resp);
        }
    }

    public int checkBalance (String firstName, String bankName){
        try {
            Class.forName("org.postgresql.Driver");
            Connection conn = DriverManager.getConnection("jdbc:postgresql://localhost:5432/postgis_22_sample", "postgres", "123456");
            PreparedStatement preparedStatement = conn.prepareStatement("SELECT balance FROM webAccount WHERE firstname=? and bankname=? ");
            preparedStatement.setString(1, firstName);
            preparedStatement.setString(2, bankName);
            ResultSet rs = preparedStatement.executeQuery();
            int balance = -1;
            while(rs.next()){
                balance = Integer.parseInt(rs.getString("balance"));
            }

            rs.close();
            preparedStatement.close();
            conn.close();
            return balance;
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
            return -1;
    }
}
