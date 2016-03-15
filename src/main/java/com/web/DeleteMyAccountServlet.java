package com.web;

import com.web.daoExample.MyAccountDAOImpl;
import com.web.daoExample.TakeWebDAO;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 * Created by devendra on 3/12/2016.
 */
@WebServlet(urlPatterns = {"/deleteMyAccountServlet"})
public class DeleteMyAccountServlet extends HttpServlet {

    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        String id = req.getParameter("id");
        MyAccount account=new MyAccount();
        account.setId(Integer.parseInt(id));
        TakeWebDAO myAccountDAO = new MyAccountDAOImpl();
        myAccountDAO.delete(account);
        //delete(Integer.parseInt(id));
        RequestDispatcher requestDispatcher = req.getRequestDispatcher("/MyAccountListServlet");
        requestDispatcher.forward(req,resp);
    }

    /*private void toDelete(int id) {
        try {
            Class.forName("org.postgresql.Driver");
            Connection conn = DriverManager.getConnection("jdbc:postgresql://localhost:5432/postgis_22_sample", "postgres", "123456");
            PreparedStatement preparedStatement = conn.prepareStatement("DELETE from myAccount WHERE id=?");
            preparedStatement.setInt(1, id);
            int result = preparedStatement.executeUpdate();
            preparedStatement.close();
            conn.close();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }*/
}
