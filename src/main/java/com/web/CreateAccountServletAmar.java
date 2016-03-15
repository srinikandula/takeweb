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
 * Created by Amar on 3/10/2016.
 */
public class CreateAccountServletAmar extends HttpServlet {
    @Override
    protected void service(HttpServletRequest request, HttpServletResponse resp) throws ServletException, IOException {
        //super.doGet(req, resp);
        String accountNumberString = request.getParameter("accnum");
        String firstName = request.getParameter("fname");
        String lastName = request.getParameter("lname");
        String balanceString = request.getParameter("balance");
        System.out.println("Create Account servlet is called with values "+ accountNumberString+firstName+lastName + balanceString);
        createAccount(Integer.parseInt(accountNumberString), firstName, lastName, Double.parseDouble(balanceString));
        RequestDispatcher dispatcher = request.getRequestDispatcher("/accountListServletAmar");
        dispatcher.forward(request,resp);
    }
    private void createAccount(int accountNumber, String firstName, String lastName, double balance) {
        try {
            Class.forName("org.postgresql.Driver");
            //System.out.println("print1");
            String URL = "jdbc:postgresql://localhost:5432/postgres"; //HOST/DATABASE
            String USER = "postgres";
            String PASS = "Pass1234";
            Connection conn = DriverManager.getConnection(URL, USER, PASS);
            //System.out.println("print2");
            PreparedStatement stmt = conn.prepareStatement("SELECT * from account where accnum =?");
            stmt.setInt(1, accountNumber);
            ResultSet rs = stmt.executeQuery();
            if ((!rs.next())) {
                PreparedStatement preparedStatement = conn.prepareStatement("INSERT INTO Account(accnum, firstname, lastname, balance) VALUES (?, ?, ?, ?)");
                preparedStatement.setInt(1, accountNumber);
                preparedStatement.setString(2, firstName);
                preparedStatement.setString(3, lastName);
                preparedStatement.setDouble(4, balance);
                int insertedRecords = preparedStatement.executeUpdate();
                System.out.println("Records inserted :" + insertedRecords);
                preparedStatement.close();
            } else {
                System.err.println("Account number already exists, please choose the option again");
                return;
            }
            rs.close();
            stmt.close();
            conn.close();
        }catch(ClassNotFoundException e) {
            e.printStackTrace();
            System.exit(1);
        }catch(SQLException e) {
            e.printStackTrace();
            System.exit(2);
        }

    }

}
