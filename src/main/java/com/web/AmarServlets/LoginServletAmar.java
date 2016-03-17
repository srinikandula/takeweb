package com.web.AmarServlets;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by Amar on 3/16/2016.
 */
@WebServlet(urlPatterns = {"/loginServletAmar",})
public class LoginServletAmar extends HttpServlet {
    @Override
    public void init(ServletConfig servletConfig) throws ServletException {

        System.out.println("Login Servlet: init() is called");
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse resp) throws ServletException, IOException {
        System.out.println("Login Servlet: goPost() is called");
        String username = request.getParameter("uname");
        String password = request.getParameter("pwd");
        System.out.println(username+" "+password);
        /*ServletContext ctxt = req.getSession().getServletContext();
        //ApplicationContext appContext= WebApplicationContextUtils.getRequiredWebApplicationContext(ctxt);

        //EmployeeJDBCDAO dao =(EmployeeJDBCDAO)appContext.getBean("empDao");
        AccountDAO accountDAO = new AccountDAO();
        List<Account> accounts = accountDAO.findAll();
        req.setAttribute("accounts", accounts);*/
        RequestDispatcher dispatcher = request.getRequestDispatcher("HomePageAmar.html");
        dispatcher.forward(request, resp);
    }

    @Override
    public void destroy() {
        System.out.println("AccountListServlet: destroy() is called");
    }
}

