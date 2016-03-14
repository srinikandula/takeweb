package com.web.keerthi;

import com.web.model.KeerthiAccount;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by CrazyNaveen on 3/11/16.
 */
@WebServlet(urlPatterns = {"/keeLoadList"})
public class LoadServlet extends HttpServlet {
    @Override
    protected void service(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {

        String id = req.getParameter("id");

        List<KeerthiAccount> accounts = loadAccounts(Integer.parseInt(id));
        req.setAttribute("accounts", accounts);
        RequestDispatcher rd = req.getRequestDispatcher("loadAccount.jsp");
        PrintWriter pw = res.getWriter();
        pw.write("This is load servlet");
        rd.forward(req, res);
    }

    private List loadAccounts(int id) {
        PreparedStatement preparedStatement = null;
        ResultSet rs;
        Connection conn;
        List<KeerthiAccount> list = new ArrayList<>();
        try {
            Class.forName("org.postgresql.Driver");
            conn = DriverManager.getConnection("jdbc:postgresql://localhost:5432/workshop", "postgres", "keerthi");
            preparedStatement = conn.prepareStatement("SELECT * FROM accounts WHERE id = ?");
            preparedStatement.setInt(1,id);
            rs = preparedStatement.executeQuery();

            KeerthiAccount account = new KeerthiAccount();
            while (rs.next()) {
                account.setId(rs.getInt("id"));
                account.setUserName(rs.getString("name"));
                account.setAccNumber(rs.getLong("acc_num"));
                account.setBalance(rs.getDouble("balance"));
                list.add(account);
            }

            rs.close();
            preparedStatement.close();
            conn.close();
            return list;
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

        return list;
    }
}


