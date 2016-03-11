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
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class CreateAccountServlet extends HttpServlet{

    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //super.doGet(req, resp);
        String id = req.getParameter("id");
        String firstName = req.getParameter("fname");
        String lname = req.getParameter("lname");
        String balanceString = req.getParameter("balance");
        System.out.println("Create Account servlet is called with values " + firstName + lname + balanceString);
        createAccount(firstName, lname, Double.parseDouble(balanceString), id);
        RequestDispatcher dispatcher = req.getRequestDispatcher("/accountListServlet");
        dispatcher.forward(req,resp);
    }

    private void createAccount(String firstName, String lastName, double balance, String id) {
        try {
            //load the driver
            Class.forName("org.postgresql.Driver");
            Connection conn = DriverManager.getConnection("jdbc:postgresql://localhost:5432/postgres", "postgres", "postgres");
            PreparedStatement preparedStatement = conn.prepareStatement("INSERT INTO Accounts( firstname, lastname, balance, id) VALUES (?, ?, ?,?)");
            preparedStatement.setString(1, firstName);
            preparedStatement.setString(2, lastName);
            preparedStatement.setDouble(3, balance);
            preparedStatement.setInt(4, Integer.parseInt(id));
            int insertedRecords = preparedStatement.executeUpdate();
            preparedStatement.close();
            conn.close();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
