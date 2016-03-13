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
 * Created by CrazyNaveen on 3/11/16.
 */
@WebServlet(urlPatterns = {"/keerthiUpdate"})
public class KeerthiUpdateServlet extends HttpServlet {
    @Override
    protected void service(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        String id = req.getParameter("id");
        String name = req.getParameter("uname");
        String accNum = req.getParameter("accNum");
        String bal = req.getParameter("balance");

        updateAccount(Integer.parseInt(id), name, Long.parseLong(accNum), Double.parseDouble(bal));

        RequestDispatcher rd = req.getRequestDispatcher("/keeAccountList");
        rd.forward(req, res);
    }

    private int updateAccount(int id,String name,long accNumber,double balance){
        PreparedStatement preparedStatement = null;
        ResultSet rs;
        Connection conn;
        try {
            Class.forName("org.postgresql.Driver");
            conn = DriverManager.getConnection("jdbc:postgresql://localhost:5432/workshop", "postgres", "keerthi");

            preparedStatement = conn.prepareStatement("UPDATE accounts set name=?, acc_num=?, balance =?  WHERE id = ? ");

            preparedStatement.setInt(4, id);
            preparedStatement.setString(1, name);
            preparedStatement.setLong(2, accNumber);
            preparedStatement.setDouble(3, balance);

            int result = preparedStatement.executeUpdate();
            System.out.println("records status "+result);
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
