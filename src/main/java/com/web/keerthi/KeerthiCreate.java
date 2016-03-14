package com.web.keerthi;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.*;

/**
 * Created by CrazyNaveen on 3/10/16.
 */
//@WebServlet(urlPatterns = {"/keerthiCreate"})
public class KeerthiCreate extends HttpServlet {
    protected void service(HttpServletRequest req, HttpServletResponse res) throws IOException, ServletException {

        String id = req.getParameter("id");
        String userName = req.getParameter("uname");
        String accNumber = req.getParameter("accNum");
        String balance =req.getParameter("balance");

        //System.out.println("Account holder details are " +userName+ " " +accNumber+ " "+balance+ " );
        createAccount(Integer.parseInt(id), userName, Long.parseLong(accNumber), Double.parseDouble( balance));
        RequestDispatcher rd = req.getRequestDispatcher("/keeAccountList");
        rd.forward(req,res);
    }

    private void createAccount(int id,String name, long accNumber,double balance){

        PreparedStatement preparedStatement = null;
        ResultSet rs = null;
        Connection conn;
        try {
            Class.forName("org.postgresql.Driver");
            conn = DriverManager.getConnection("jdbc:postgresql://localhost:5432/workshop", "postgres", "keerthi");
            preparedStatement = conn.prepareStatement("INSERT INTO " +
                    "accounts(id,name,acc_num,balance)VALUES(?,?,?,?) ");
            preparedStatement.setInt(1, id);
            preparedStatement.setString(2, name);
            preparedStatement.setLong(3, accNumber);
            preparedStatement.setDouble(4, balance);

            int result = preparedStatement.executeUpdate();
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
