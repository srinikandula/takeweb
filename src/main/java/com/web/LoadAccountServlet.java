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
@WebServlet(urlPatterns = {"/loadAccountServletAmar"})
public class LoadAccountServlet extends HttpServlet {
    @Override
    protected void service(HttpServletRequest request, HttpServletResponse resp) throws ServletException, IOException {
        String accountNumberString = request.getParameter("accnum");
        AmarAccount acc =loadAccount(Integer.parseInt(accountNumberString));
        request.setAttribute("accounts", acc);
        RequestDispatcher dispatcher = request.getRequestDispatcher("loadAccountsListAmar.jsp");
        dispatcher.forward(request, resp);
    }
    private AmarAccount loadAccount(int accountNumber) {
            AmarAccount account = new AmarAccount();
        try {
            //load the driver
            Class.forName("org.postgresql.Driver");
            Connection conn = DriverManager.getConnection("jdbc:postgresql://localhost:5432/postgres", "postgres", "Pass1234");
            PreparedStatement preparedStatement = conn.prepareStatement("select * from account where accnum = ?");
            preparedStatement.setDouble(1, accountNumber);
            ResultSet rs = preparedStatement.executeQuery();
            while(rs.next()){
                account.setAccountNumber(rs.getInt("accnum"));
                account.setFirstName(rs.getString("firstName"));
                account.setLastName(rs.getString("lastName"));
                account.setBalance(rs.getDouble("balance"));
            }
            rs.close();
            preparedStatement.close();
            conn.close();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return account;
    }
}
