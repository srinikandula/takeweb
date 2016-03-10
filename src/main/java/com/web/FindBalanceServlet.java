/**
 * Created by skandula on 3/9/16.
 */
package com.web;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.*;

public class FindBalanceServlet extends HttpServlet{

    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //super.doGet(req, resp);
        String firstName = req.getParameter("fname");
        String lname = req.getParameter("lname");
        double balance = findAccountBalance(firstName, lname);
        if(balance == -1){
            //matching account is not found or error finding account balance
            PrintWriter pw = resp.getWriter();
            pw.write("matching account is not found or error finding account balance");
        }else{
            // balance information found
            req.setAttribute("balance", balance);
            RequestDispatcher dispatcher = req.getRequestDispatcher("accountBalance.jsp");
            dispatcher.forward(req, resp);
        }

    }
    private double findAccountBalance(String firstName, String lastName) {
        try {
            //load the driver
            Class.forName("org.postgresql.Driver");
            Connection conn = DriverManager.getConnection("jdbc:postgresql://localhost:5432/postgres", "postgres", "postgres");
            PreparedStatement preparedStatement = conn.prepareStatement("select balance from Account where firstname=? and lastname =?");
            preparedStatement.setString(1, firstName);
            preparedStatement.setString(2, lastName);
            ResultSet resultSet = preparedStatement.executeQuery();
            double balance = -1;
            if(resultSet.next()){
               balance = Double.parseDouble(resultSet.getString("balance"));
            }
            resultSet.close();
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
