package com.web;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.*;
import java.util.Scanner;

/**
 * Created by devendra on 3/10/2016.
 */
public class DepositServlet extends HttpServlet {

    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String firstName = req.getParameter("fname");
        String depositAmount = req.getParameter("damount");
        toDeposit(firstName, Integer.parseInt(depositAmount));
        PrintWriter pw= resp.getWriter();
        pw.write("Updated");
    }

    public void toDeposit(String firstname, int amount){
        try {
            Class.forName("org.postgresql.Driver");
            Connection conn = DriverManager.getConnection("jdbc:postgresql://localhost:5432/postgis_22_sample", "postgres","123456");
            PreparedStatement preparedStatement = conn.prepareStatement("SELECT  * from webAccount where firstname=?");
            preparedStatement.setString(1,firstname);
            ResultSet rs = preparedStatement.executeQuery();
            if((!rs.next())) {
                System.err.println("Data not valid");
                return;
            } else {
                do{
                    int balance = rs.getInt("balance");
                    balance=balance+amount;
                    System.out.println("Current balance is"+balance);
                    PreparedStatement ps = conn.prepareStatement("UPDATE webAccount set balance=? WHERE firstname=?");
                    ps.setInt(1,balance);
                    ps.setString(2,firstname);
                    int result1= ps.executeUpdate();
                    ps.close();
                }while ((rs.next()));

            }
            rs.close();
            preparedStatement.close();
            conn.close();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
