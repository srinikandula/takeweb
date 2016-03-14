package com.web;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
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
@WebServlet(urlPatterns = {"/deleteAccountServletAmar"})
public class DeleteAccountServletAmar extends HttpServlet {
    @Override
    protected void service(HttpServletRequest request, HttpServletResponse resp) throws ServletException, IOException {
        //super.doGet(req, resp);
        String accountNumberString = request.getParameter("accnum");
        System.out.println("Create Account servlet is called with values "+ accountNumberString);
        deleteAccount(Integer.parseInt(accountNumberString));
        RequestDispatcher dispatcher = request.getRequestDispatcher("/accountListServletAmar");
        dispatcher.forward(request,resp);
    }
    public void deleteAccount(int accountNumber){
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
                PreparedStatement smt = conn.prepareStatement("DELETE from account where accnum = ?");
                smt.setInt(1, accountNumber);
                smt.executeUpdate();
                System.out.println("Account is deleted successfully");
                smt.close();
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
