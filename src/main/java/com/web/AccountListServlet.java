package com.web;


import com.web.DAO.AccountDAO;
import com.web.model.Account;
import org.springframework.context.ApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

/**
 * Created by skandula on 3/10/16.
 */
@WebServlet(urlPatterns = {"/accountListServlet",})
public class AccountListServlet extends HttpServlet {

    @Override
    public void init(ServletConfig servletConfig) throws ServletException {

        System.out.println("AccountListServlet: init() is called");
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        System.out.println("AccountListServlet: goGet() is called");
        ServletContext ctxt = req.getSession().getServletContext();
        //ApplicationContext appContext= WebApplicationContextUtils.getRequiredWebApplicationContext(ctxt);

        //EmployeeJDBCDAO DAO =(EmployeeJDBCDAO)appContext.getBean("empDao");

        AccountDAO accountDAO = new AccountDAO();
        List<Account> accounts = accountDAO.findAll();
        req.setAttribute("accounts", accounts);
        RequestDispatcher dispatcher = req.getRequestDispatcher("accountsList.jsp");
        dispatcher.forward(req, resp);
    }

    @Override
    public void destroy() {
        System.out.println("AccountListServlet: destroy() is called");
    }
}
