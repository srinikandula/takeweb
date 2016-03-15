package com.web;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.URL;
import java.sql.*;

/**
 * Created by CrazyNaveen on 3/10/16.
 */

public class KeerthiDeleteAccount extends HttpServlet {

    protected void service(HttpServletRequest req, HttpServletResponse res) throws IOException {

        String accNum = req.getParameter("accNum");

 deleteAccount(Long.parseLong(accNum));
        PrintWriter pw;

     pw = res.getWriter();
    pw.write("Account deleted Successfully....");



    }

    private void deleteAccount(long accNumber) {
        PreparedStatement preparedStatement = null;
        ResultSet rs = null;
        Connection conn;
        try {
            Class.forName("org.postgresql.Driver");
            conn = DriverManager.getConnection("jdbc:postgresql://localhost:5432/workshop", "postgres", "keerthi");
            preparedStatement = conn.prepareStatement("DELETE from account where acc_num = ?");
            preparedStatement.setLong(1, accNumber);
            int deleteStatus = preparedStatement.executeUpdate();
            System.out.println("Record is deleted successfully..." + deleteStatus);


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


