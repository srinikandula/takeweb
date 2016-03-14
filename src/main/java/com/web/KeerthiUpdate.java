package com.web;

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
public class KeerthiUpdate extends HttpServlet {

    protected void service(HttpServletRequest req, HttpServletResponse res) throws IOException {
        String accNumber = req.getParameter("accNum");
        String balance = req.getParameter("bal");
        PrintWriter pw = null;
        int result = updateData(Long.parseLong(accNumber), Double.parseDouble(balance));
        if(result == 1){
             pw = res.getWriter();
            pw.write("Amount is deposited successfully...");
        }
        else {
            pw.write("Deposit failed..");
        }
    }

    private int updateData(long accNum, double amount) {
        Scanner scanner = new Scanner(System.in);
        PreparedStatement preparedStatement = null;
        ResultSet rs;
        Connection conn;
        try {
            Class.forName("org.postgresql.Driver");
            conn = DriverManager.getConnection("jdbc:postgresql://localhost:5432/workshop", "postgres", "keerthi");
            preparedStatement = conn.prepareStatement("SELECT * FROM account WHERE acc_num = ?");
            preparedStatement.setLong(1, accNum);
            rs = preparedStatement.executeQuery();
            int balance = 0;
            int initialAmount = 0;
            /*if ((!rs.next())) {
                System.err.println("Account number is invalid");
            } else {
                do {
                    initialAmount = rs.getInt(4);

                }
                while (rs.next());

                initialAmount = initialAmount + balance;

            }*/
            while (rs.next()){
                System.out.println( rs.getString("name")+ rs.getString("acc_num")+rs.getString("balance")+ rs.getString("phone"));

            }
            preparedStatement = conn.prepareStatement("UPDATE account set balance = ? WHERE acc_num = ?");
            preparedStatement.setDouble(1, amount);
            preparedStatement.setLong(2, accNum);
            System.out.println(initialAmount);
            int res = preparedStatement.executeUpdate();
            System.out.println("status of updating records... " + res);

            preparedStatement = conn.prepareStatement("SELECT * FROM account where acc_num = ?");
            preparedStatement.setLong(1, accNum);
            rs = preparedStatement.executeQuery();
            while (rs.next()) {
                System.out.println(rs.getString(1)+ " "+rs.getInt(2)+" "+rs.getDouble(3)+ " "+rs.getLong(4));
            }
            rs.close();
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
        return 1;
    }
}
