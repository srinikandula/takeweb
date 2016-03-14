package com.web;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by devendra on 3/12/2016.
 */
@WebServlet(urlPatterns = {"/MyAccountListServlet"})
public class MyAccountListServlet extends HttpServlet {

    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<MyAccount> accounts = checkAccounts();
        req.setAttribute("accounts", accounts);
        RequestDispatcher requestDispatcher = req.getRequestDispatcher("MyAccountList.jsp");
        requestDispatcher.forward(req,resp);
    }

    private List<MyAccount> checkAccounts(){
        List<MyAccount> accounts = new ArrayList<>();
        try {
            Class.forName("org.postgresql.Driver");
            Connection conn = DriverManager.getConnection("jdbc:postgresql://localhost:5432/postgis_22_sample", "postgres", "123456");
            Statement statement = conn.createStatement();
            ResultSet rs = statement.executeQuery("SELECT * FROM myAccount");
            while (rs.next()) {
                MyAccount myAccount = new MyAccount();
                myAccount.setId(rs.getInt("Id"));
                myAccount.setFirstName(rs.getString("firstName"));
                myAccount.setLastName(rs.getString("lastName"));
                myAccount.setBalance(rs.getInt("balance"));
                myAccount.setBankName(rs.getString("bankName"));
                accounts.add(myAccount);
            }
            statement.close();
            conn.close();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return accounts;
    }

}
