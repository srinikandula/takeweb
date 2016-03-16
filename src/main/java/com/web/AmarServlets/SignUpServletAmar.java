package com.web.AmarServlets;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * Created by Amar on 3/16/2016.
 */
@WebServlet(urlPatterns = {"/signUpServletAmar",})
public class SignUpServletAmar extends HttpServlet {
    @Override
    public void init(ServletConfig servletConfig) throws ServletException {

        System.out.println("AccountListServlet: init() is called");
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse resp) throws ServletException, IOException {
        System.out.println("Login Servlet: goPost() is called");
        String firstName = request.getParameter("fname");
        String lastName = request.getParameter("lname");
        System.out.println(firstName+" "+lastName);
        /*ServletContext ctxt = req.getSession().getServletContext();
        //ApplicationContext appContext= WebApplicationContextUtils.getRequiredWebApplicationContext(ctxt);

        //EmployeeJDBCDAO dao =(EmployeeJDBCDAO)appContext.getBean("empDao");
        AccountDAO accountDAO = new AccountDAO();
        List<Account> accounts = accountDAO.findAll();
        req.setAttribute("accounts", accounts);*/
        PrintWriter pw = resp.getWriter();
        pw.write("Successful Registration \n Please Login to go to Home Page");
        RequestDispatcher dispatcher = request.getRequestDispatcher("LoginPageAmar.html");
        dispatcher.forward(request, resp);
    }

    @Override
    public void destroy() {
        System.out.println("AccountListServlet: destroy() is called");
    }
}
