package com.web;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.*;

/**
 * Created by Amar on 3/11/2016.
 */
//@WebServlet(urlPatterns = {"/updateAccountServletAmar"})
public class UpdateServletAmar extends HttpServlet {
    @Override
    protected void service(HttpServletRequest request, HttpServletResponse resp) throws ServletException, IOException {
        //super.doGet(req, resp);
        String accountNumberString = request.getParameter("accnum");
        String firstName = request.getParameter("fname");
        String lastName = request.getParameter("lname");
        String balanceString = request.getParameter("balance");
        System.out.println("Update Account servlet is called with values "+ accountNumberString+firstName+lastName + balanceString);
        updateAccount(Integer.parseInt(accountNumberString), firstName, lastName, Double.parseDouble(balanceString));
        RequestDispatcher dispatcher = request.getRequestDispatcher("/accountListServletAmar");
        dispatcher.forward(request,resp);
    }
   public void updateAccount(int accountNumber, String firstName, String lastName, double balance){
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
               System.err.println("Account number is invalid, doesn't exist");
               return;
           } else {
               do{
                   PreparedStatement preparedStatement = conn.prepareStatement("update account set firstname =?, lastname =?, balance =? where accnum =?");
                   preparedStatement.setString(1, firstName);
                   preparedStatement.setString(2, lastName);
                   preparedStatement.setDouble(3, balance);
                   preparedStatement.setInt(4, accountNumber);
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
