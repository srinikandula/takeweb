package com.web;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.*;

/**
 * Created by devendra on 3/11/2016.
 */
public class DeleteServlet extends HttpServlet {
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String firstname = req.getParameter("fname");
        delete( firstname);
        PrintWriter pw = resp.getWriter();
        pw.write("Account updated");
    }
    public void delete(String firstname){
        try {
            Class.forName("org.postgresql.Driver");
            Connection conn = DriverManager.getConnection("jdbc:postgresql://localhost:5432/postgis_22_sample", "postgres","123456");
            PreparedStatement preparedStatement = conn.prepareStatement("SELECT * FROM webAccount WHERE firstname=?");
            preparedStatement.setString(1,firstname);
            ResultSet rs= preparedStatement.executeQuery();
            if((!rs.next())){
                System.err.println("Account not found");
                return;
            } else {
                PreparedStatement ps = conn.prepareStatement("DELETE  from webAccount WHERE firstname=?");
                ps.setString(1,firstname);
                ps.executeUpdate();
                System.out.println("Account deleted");
                ps.close();
            }
            rs.close();
            preparedStatement.close();
            conn.close();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }


    }
}
