/**
 * Created by skandula on 3/9/16.
 */
package com.web.servlet;

import com.web.dao.AccountDAO;
import org.springframework.context.ApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
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
 * Created by skandula on 3/10/16.
 */
@WebServlet(urlPatterns = {"/deleteAccountServlet"})
public class DeleteAccountServlet extends HttpServlet{

    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //super.doGet(req, resp);
        String id = req.getParameter("id");
        ServletContext context = req.getServletContext();
        ApplicationContext appContext= WebApplicationContextUtils.getRequiredWebApplicationContext(context);
        AccountDAO accountDAO = (AccountDAO)appContext.getBean("accountDAO");
        accountDAO.deleteAccount(Integer.parseInt(id));
        RequestDispatcher dispatcher = req.getRequestDispatcher("/accountListServlet");
        dispatcher.forward(req,resp);
    }

}
