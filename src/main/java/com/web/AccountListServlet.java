package com.web;


import com.web.model.Account;

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
 * Created by skandula on 3/10/16.
 */
@WebServlet(urlPatterns = {"/accountListServlet"})
public class AccountListServlet extends HttpServlet {
    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<Account> accounts = findAllAccounts();
        req.setAttribute("accounts", accounts);
        RequestDispatcher dispatcher = req.getRequestDispatcher("accountsList.jsp");
        dispatcher.forward(req, resp);
    }
    private List<Account> findAllAccounts() {
        List<Account> accounts = new ArrayList<>();
        try {
            //load the driver
            Class.forName("org.postgresql.Driver");
            Connection conn = DriverManager.getConnection("jdbc:postgresql://localhost:5432/postgres", "postgres", "postgres");
            Statement statement = conn.createStatement();
            ResultSet rs = statement.executeQuery("select * from accounts");
            while(rs.next()){
                Account account = new Account();
                account.setFirstName(rs.getString("firstName"));
                account.setLastName(rs.getString("lastName"));
                account.setId(rs.getInt("id"));
                account.setBalance(rs.getDouble("balance"));
                accounts.add(account);
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
