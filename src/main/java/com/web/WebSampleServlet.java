package com.web;

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

/**
 * Created by devendra on 3/10/2016.
 */
public class WebSampleServlet extends HttpServlet {

    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //super.doGet(req, resp);
        String firstName = req.getParameter("fname");
        String lastname = req.getParameter("lname");
        String balanceString = req.getParameter("balance");
        String bankName = req.getParameter("bname");
        System.out.println("Create Account servlet is called with values " + firstName + lastname + balanceString+bankName);
        webAccount(firstName, lastname, Integer.parseInt(balanceString), bankName);
        PrintWriter pw = resp.getWriter();
        pw.write("Account has created Successfully");
    }

    private void webAccount(String firstName, String lastName, int balance, String bankName) {
        try {
            Class.forName("org.postgresql.Driver");
            Connection conn = DriverManager.getConnection("jdbc:postgresql://localhost:5432/postgis_22_sample","postgres", "123456");
            PreparedStatement preparedStatement = conn.prepareStatement("INSERT INTO webAccount(firstname, lastname, balance, bankname)VALUES (?,?,?,?)");
            preparedStatement.setString(1,firstName);
            preparedStatement.setString(2,lastName);
            preparedStatement.setInt(3,balance);
            preparedStatement.setString(4,bankName);
            int result = preparedStatement.executeUpdate();
            preparedStatement.close();
            conn.close();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


}