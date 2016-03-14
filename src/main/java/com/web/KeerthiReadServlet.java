package com.web;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.*;


/**
 * Created by CrazyNaveen on 3/10/16.
 */
public class KeerthiReadServlet extends HttpServlet{


    protected void service (HttpServletRequest req, HttpServletResponse res) throws IOException {
        String userName = req.getParameter("uname");
        String accNumber = req.getParameter("accNum");

        //System.out.println("Balance available in "+accNumber +" "+ "is" +" ");
        Double balance = checkBalance(userName,Long.parseLong(accNumber));
        PrintWriter pw ;
        if(balance == -1){
             pw = res.getWriter();
            pw.write("Enter the valid account details");
        }
        else {

             pw = res.getWriter();
            pw.write("Balance available in " +accNumber+ " "+"is" +" "+ balance);

        }

    }


    private double checkBalance(String userName, long accNumber){
        PreparedStatement preparedStatement = null;
        ResultSet rs = null;
        Connection conn;
        try {
            Class.forName("org.postgresql.Driver");
            conn = DriverManager.getConnection("jdbc:postgresql://localhost:5432/workshop", "postgres", "keerthi");
            preparedStatement = conn.prepareStatement("SELECT balance from account WHERE  name = ? and acc_num = ?");
            preparedStatement.setLong(2, accNumber);
            preparedStatement.setString(1, userName);
            rs = preparedStatement.executeQuery();
           double balance = -1;
            while (rs.next()) {
                balance = Double.parseDouble(rs.getString("balance"));
            }
            rs.close();
            preparedStatement.close();
            conn.close();
            return balance;
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (!(rs.isClosed())) {
                    rs.close();
                }
                if (!(preparedStatement.isClosed())) {
                    preparedStatement.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return  -1;

    }


}
