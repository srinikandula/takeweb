package com.web;

import com.web.DAO.LoginDAO;
import com.web.model.User;
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

/**
 * Created by njonnala on 3/22/2016.
 */
@WebServlet(urlPatterns = {"/signup"})
public class SignUpServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String firstName = request.getParameter("firstname");
        String lastName = request.getParameter("lastname") ;
        String userName = request.getParameter("username");
        String password = request.getParameter("password");

        ServletContext ctxt = request.getSession().getServletContext();
        ApplicationContext appContext = WebApplicationContextUtils.getRequiredWebApplicationContext(ctxt);
        LoginDAO dao = (LoginDAO)appContext.getBean("LoginDAO");
        User users = (User)appContext.getBean("User");
        users.setFirstName(firstName);
        users.setLastName(lastName);
        users.setPassword(password);
        users.setUserName(userName);



        dao.createUser(users);
        RequestDispatcher rd = request.getRequestDispatcher("/login.jsp");
        rd.forward(request,response);

        System.out.println("isnside signup");
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
