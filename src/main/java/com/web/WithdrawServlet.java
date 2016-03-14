package com.web;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.*;

/**
 * Created by devendra on 3/11/2016.
 */
public class WithdrawServlet extends HttpServlet{

    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String firstName =  req.getParameter("fname");
        String withdrawAmount = req.getParameter("wamount");
        toWithdraw(firstName, Integer.parseInt(withdrawAmount));
        PrintWriter pw= resp.getWriter();
        pw.write("Account Updated");
    }

    public void toWithdraw(String firstname, int withdrawAmount){
        try {
            Class.forName("org.postgresql.Driver");
            Connection conn = DriverManager.getConnection("jdbc:postgresql://localhost:5432/postgis_22_sample", "postgres","123456");
            PreparedStatement preparedStatement = conn.prepareStatement("SELECT * FROM webAccount WHERE firstname=?");
            preparedStatement.setString(1,firstname);
            ResultSet rs = preparedStatement.executeQuery();
            if((!rs.next())) {
                System.err.println("Data Not Valid");
                return;
            }else {
                do{
                    int balance = rs.getInt("balance");
                    if(withdrawAmount > balance){
                        System.err.println("Please enter amount within the balance range");
                    }else {
                        balance = balance-withdrawAmount;
                        System.out.println("The current balance is "+balance);
                        PreparedStatement ps = conn.prepareStatement("UPDATE webAccount set balance=? WHERE firstname=?");
                        ps.setString(1,firstname);
                        ps.setInt(2,balance);
                        int result = ps.executeUpdate();
                        ps.close();
                    }
                }while ((rs.next()));
            }
            rs.close();
            preparedStatement.close();
            conn.close();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }


    }

}
