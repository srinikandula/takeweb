package com.web;

import com.web.model.AmarAccount;

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
 * Created by Amar on 3/11/2016.
 */
@WebServlet(urlPatterns = {"/accountListServletAmar"})
public class AccountListServletAmar extends HttpServlet{
        @Override
        protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
            List<AmarAccount> acc = findAllAccounts();
            req.setAttribute("accounts", acc);
            RequestDispatcher dispatcher = req.getRequestDispatcher("accountsListAmar.jsp");
            dispatcher.forward(req, resp);
        }
        private List<AmarAccount> findAllAccounts() {
            List<AmarAccount> accounts = new ArrayList<>();
            try {
                //load the driver
                Class.forName("org.postgresql.Driver");
                Connection conn = DriverManager.getConnection("jdbc:postgresql://localhost:5432/postgres", "postgres", "Pass1234");
                Statement statement = conn.createStatement();
                ResultSet rs = statement.executeQuery("select * from account");
                while(rs.next()){
                    AmarAccount account = new AmarAccount();
                    account.setAccountNumber(rs.getInt("accnum"));
                    account.setFirstName(rs.getString("firstName"));
                    account.setLastName(rs.getString("lastName"));
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
