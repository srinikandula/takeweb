package com.web;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.*;
import java.util.Scanner;


/**
 * Created by CrazyNaveen on 3/10/16.
 */
public class KeerthiCreateServlet extends HttpServlet{

    protected void service(HttpServletRequest req, HttpServletResponse res) throws IOException {

        String userName = req.getParameter("name");
        String accNumber = req.getParameter("accNum");
        String balance = req.getParameter("bal");
        String phoneNumber =req.getParameter("phone");

        System.out.println("Account holder details are " +userName+ " " +accNumber+ " "+balance+ " "+ phoneNumber);

        createAccount(userName, Integer.parseInt(accNumber), Double.parseDouble( balance), Long.parseLong(phoneNumber));
        PrintWriter pw = res.getWriter();

        pw.write("Account has created Successfully");
    }


    private void createAccount(String name, int accNumber, double balance, long phone){


            PreparedStatement preparedStatement = null;
            ResultSet rs = null;
            Connection conn;
            try {
                Class.forName("org.postgresql.Driver");
                conn = DriverManager.getConnection("jdbc:postgresql://localhost:5432/workshop", "postgres", "keerthi");
                preparedStatement = conn.prepareStatement("INSERT INTO " +
                        "account(name,acc_num,balance,phone)VALUES(?,?,?,?) ");

                preparedStatement.setString(1, name);
                preparedStatement.setInt(2, accNumber);
                preparedStatement.setDouble(3, balance);
                preparedStatement.setLong(4, phone);
                int result = preparedStatement.executeUpdate();
                System.out.println(result);
                System.out.println("Account is successfully created...");
                preparedStatement.close();
                conn.close();
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            } catch (SQLException e) {
                e.printStackTrace();
            } finally {
                try {
                    if (!(preparedStatement.isClosed())) {
                        preparedStatement.close();
                    }
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }




    }


}
