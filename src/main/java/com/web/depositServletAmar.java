package com.web;

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
public class depositServletAmar extends HttpServlet {
    @Override
    protected void service(HttpServletRequest request, HttpServletResponse resp) throws ServletException, IOException {
        //super.doGet(req, resp);
        String accountNumberString = request.getParameter("accnum");
        String depositString = request.getParameter("deposit");
        System.out.println("Create Account servlet is called with values "+ accountNumberString+depositString);
        makeDeposit(Integer.parseInt(accountNumberString), Double.parseDouble(depositString));
        PrintWriter pw = resp.getWriter();
        pw.write("Account has updated Successfully");
    }
    public void makeDeposit(int accountNumber, double deposit){
        try {
            Class.forName("org.postgresql.Driver");
            String URL = "jdbc:postgresql://localhost:5432/postgres"; //HOST/DATABASE
            String USER = "postgres";
            String PASS = "Pass1234";
            Connection conn = DriverManager.getConnection(URL, USER, PASS);
            PreparedStatement stmt = conn.prepareStatement("SELECT * from account where accnum =?");
            stmt.setInt(1, accountNumber);
            ResultSet rs = stmt.executeQuery();
            if ((!rs.next())) {
                System.err.println("Account number is invalid, please choose the option again");
                return;
            } else {
                do{
                    double balance = rs.getDouble("balance");
                    balance += deposit;
                    System.out.println("Account balance after deposit: " + "$" + balance);
                    PreparedStatement preparedStatement = conn.prepareStatement("update account set balance =? where accnum =?");
                    preparedStatement.setDouble(1, balance);
                    preparedStatement.setInt(2, accountNumber);
                    int insertedRecords = preparedStatement.executeUpdate();
                    System.out.println("Records inserted :" + insertedRecords);
                    System.out.println("Account is updated successfully");
                    preparedStatement.close();
                }while (rs.next());
            }
            rs.close();
            stmt.close();
            conn.close();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

}
