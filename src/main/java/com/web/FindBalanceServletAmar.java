package com.web;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.*;

/**
 * Created by Amar on 3/10/2016.
 */
public class FindBalanceServletAmar extends HttpServlet {
    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //super.doGet(req, resp);
        String accountNumber = req.getParameter("accnum");
        System.out.println("Create Account servlet is called with values "+ accountNumber);
        double bal = findAccountBalance(Integer.parseInt(accountNumber));
        if (bal == -1){
            PrintWriter pw = resp.getWriter();
            pw.write("Account number is invalid,error in finding account balance.\n please choose existing account number");
        }else{
            req.setAttribute("bal", bal);
            RequestDispatcher dispatcher = req.getRequestDispatcher("accountBalAmar.jsp");
            dispatcher.forward(req, resp);
        }
    }
    public double findAccountBalance(int accountNumber){
        try {
            Class.forName("org.postgresql.Driver");
            String URL = "jdbc:postgresql://localhost:5432/postgres"; //HOST/DATABASE
            String USER = "postgres";
            String PASS = "Pass1234";
            Connection conn = DriverManager.getConnection(URL, USER, PASS);
            String selectSQL = "SELECT * FROM account WHERE accnum = ?";
            PreparedStatement stmt = conn.prepareStatement(selectSQL);
            stmt.setInt(1, accountNumber);
            ResultSet rs = stmt.executeQuery();
            double bal = -1;
            if ((!rs.next())) {
                System.err.println("Account number is invalid, please choose the option again");

            } else{
                do {
                    bal = rs.getDouble("balance");
                    System.out.println("Balance of your account is "+" $"+bal);
                }while (rs.next());
            }
            rs.close();
            stmt.close();
            conn.close();
            return bal;
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return -1;
    }
}
