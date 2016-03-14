package com.web.model;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;


/**
 * Created by devendra on 3/12/2016.
 */
public class CreateMyAccountServlet extends HttpServlet {

    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String id = req.getParameter("id");
        String firstName = req.getParameter("fname");
        String lastname = req.getParameter("lname");
        String balanceString = req.getParameter("balance");
        String bankName = req.getParameter("bname");
        System.out.println("Create Account servlet is called with values " + firstName + lastname + balanceString);
        createAccount(firstName, lastname, Integer.parseInt(balanceString), Integer.parseInt(id), bankName);
        RequestDispatcher dispatcher = req.getRequestDispatcher("/MyAccountListServlet");
        dispatcher.forward(req, resp);
    }

    private void createAccount(String firstName, String lastName, int balance, int id, String bankName) {
        try {
            Class.forName("org.postgresql.Driver");
            Connection conn = DriverManager.getConnection("jdbc:postgresql://localhost:5432/postgis_22_sample", "postgres", "123456");
            PreparedStatement preparedStatement = conn.prepareStatement("INSERT  INTO myAccount(id, firstname, lastname, balance, bankname) VALUES (?,?,?,?,?)");
            preparedStatement.setString(2, firstName);
            preparedStatement.setString(3, lastName);
            preparedStatement.setInt(4, balance);
            preparedStatement.setString(5, bankName);
            preparedStatement.setInt(1, id);
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
