package com.web.model;

import com.web.MyAccount;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.lang.ref.ReferenceQueue;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by devendra on 3/13/2016.
 */
@WebServlet(urlPatterns = {"/LoadMyServlet"})
public class LoadServlet extends HttpServlet{

    protected  void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
       String id = req.getParameter("id");
        MyAccount accounts = loadMyAccount(Integer.parseInt(id));
        req.setAttribute("accounts",accounts);
        RequestDispatcher requestDispatcher = req.getRequestDispatcher("loadMyAccount.jsp");
        PrintWriter printWriter = resp.getWriter();
        printWriter.write("Servlet Loaded");
        requestDispatcher.forward(req, resp);
    }

    private MyAccount loadMyAccount(int id) {
        List<MyAccount> list = new ArrayList<>();
        MyAccount account = new MyAccount();
        try {
            Class.forName("org.postgresql.Driver");
            Connection conn = DriverManager.getConnection("jdbc:postgresql://localhost:5432/postgis_22_sample", "postgres", "123456");
            PreparedStatement preparedStatement = conn.prepareStatement("SELECT * FROM  myAccount WHERE id=?");
            preparedStatement.setInt(1,id);
            ResultSet rs = preparedStatement.executeQuery();

            while(rs.next()) {
                account.setId(rs.getInt("Id"));
                account.setFirstName(rs.getString("FirstName"));
                account.setLastName(rs.getString("LastName"));
                account.setBalance(rs.getInt("Balance"));
                account.setBankName(rs.getString("BankName"));
                list.add(account);
            }
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
            return  account;
    }

}
