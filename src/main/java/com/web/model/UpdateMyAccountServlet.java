package com.web.model;

import com.web.daoExample.UpdateDao;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.*;

/**
 * Created by devendra on 3/13/2016.
 */
@WebServlet(urlPatterns = {"/updateMyAccountServlet"})
public class UpdateMyAccountServlet extends HttpServlet {

    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String id = req.getParameter("id");
        String firtName = req.getParameter("FirstName");
        String lastName = req.getParameter("LastName");
        String balance = req.getParameter("Balance");
        String bankName = req.getParameter("BankName");
        toUpdate(Integer.parseInt(id), firtName, lastName, Integer.parseInt(balance), bankName);
        RequestDispatcher requestDispatcher = req.getRequestDispatcher("/MyAccountListServlet");
        requestDispatcher.forward(req, resp);
    }
        public int toUpdate(int id,String firstName,String lastName, int balance, String bankName){
    try {
        Class.forName("org.postgresql.Driver");
        Connection conn = DriverManager.getConnection("jdbc:postgresql://localhost:5432/postgis_22_sample", "postgres", "123456");
        PreparedStatement preparedStatement = conn.prepareStatement("UPDATE myAccount SET firstname=?, lastname=?, balance=?, bankname=? WHERE id=?");
        preparedStatement.setInt(5,id);
        preparedStatement.setString(1,firstName);
        preparedStatement.setString(2,lastName);
        preparedStatement.setInt(3,balance);
        preparedStatement.setString(4,bankName);
        int result = preparedStatement.executeUpdate();
        System.out.println(result);
        preparedStatement.close();
        conn.close();
    } catch (ClassNotFoundException e) {
        e.printStackTrace();
    } catch (SQLException e) {
        e.printStackTrace();
    }
    return 1;
}
}
