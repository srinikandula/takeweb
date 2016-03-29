package com.web.servlet;


import com.web.daoExample.MyAccountDAO;
import com.web.daoExample.UserTableDAO;
import com.web.model.MyUser;
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
 * Created by devendra on 3/17/2016.
 */
@WebServlet(urlPatterns = {"/loginPage"})
public class LoginPageServlet extends HttpServlet {

    protected void doPost (HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) throws ServletException, IOException {
        String userName = httpServletRequest.getParameter("username");
        String password = httpServletRequest.getParameter("password");
        ServletContext servletContext = httpServletRequest.getSession().getServletContext();
        ApplicationContext applicationContext = WebApplicationContextUtils.getRequiredWebApplicationContext(servletContext);
        UserTableDAO tableDAO = (UserTableDAO) applicationContext.getBean("userTableDAO");
        MyUser myUser=tableDAO.findUser(userName, password);
        //UserTableDAO userTableDAO = new UserTableDAO();
       // MyUser myUser =userTableDAO.findUser(userName, password);


        if(myUser != null) {
            httpServletRequest.getSession().setAttribute("myUser", myUser);
            RequestDispatcher requestDispatcher = httpServletRequest.getRequestDispatcher("MyAccountListServlet");
            requestDispatcher.forward(httpServletRequest, httpServletResponse);

        }

    }
}
