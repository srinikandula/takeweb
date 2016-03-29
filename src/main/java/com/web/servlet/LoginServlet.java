package com.web.servlet;

import com.web.DAO.LoginDAO;
import com.web.DAO.OrderDAO;
import com.web.DAO.OrderDAOImpl;
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
 * Created by skandula on 3/14/16.
 */
@WebServlet(urlPatterns = {"/login"})
public class LoginServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) throws ServletException, IOException {
        //super.doPost(httpServletRequest, httpServletResponse);
        String userName = httpServletRequest.getParameter("username");
        String password = httpServletRequest.getParameter("password");
        ServletContext ctxt = httpServletRequest.getSession().getServletContext();
<<<<<<< HEAD:src/main/java/com/web/LoginServlet.java
        ApplicationContext appContext = WebApplicationContextUtils.getRequiredWebApplicationContext(ctxt);
        LoginDAO dao = (LoginDAO)appContext.getBean("LoginDAO");
        User user = dao.checklogin(userName , password);
        if(user != null) { //login success

            httpServletRequest.getSession().setAttribute("user", user);
            RequestDispatcher dispatcher = httpServletRequest.getRequestDispatcher("createOrder.jsp");
            dispatcher.forward(httpServletRequest, httpServletResponse);
        }
        else {
            httpServletRequest.setAttribute("message" , "Invalid");
            RequestDispatcher dispatcher = httpServletRequest.getRequestDispatcher("login.jsp");
            dispatcher.forward(httpServletRequest, httpServletResponse);

        }
=======
        //get bean factory
        ApplicationContext appContext= WebApplicationContextUtils.getRequiredWebApplicationContext(ctxt);
        UserDAO userDAO = (UserDAO)appContext.getBean("userDAO");
        User user = userDAO.findUser(userName, password);
        if(user != null) { //login success
            httpServletRequest.getSession().setAttribute("user", user);
            RequestDispatcher dispatcher = httpServletRequest.getRequestDispatcher("accountListServlet");
            dispatcher.forward(httpServletRequest, httpServletResponse);
        }
>>>>>>> e9afa4a5644ac6e192f3bfc3a4a809aad5b24c05:src/main/java/com/web/servlet/LoginServlet.java
    }
}