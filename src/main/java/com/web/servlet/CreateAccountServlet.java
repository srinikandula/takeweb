/**
 * Created by skandula on 3/9/16.
 */
package com.web.servlet;

import com.web.dao.AccountDAO;
import com.web.dao.AccountDAOImpl;
import com.web.dao.TakeWebDAO;
import com.web.model.Account;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
@WebServlet(urlPatterns = {"/createAccount"})
public class CreateAccountServlet extends HttpServlet{
    @Override
    public void init(ServletConfig servletConfig) throws ServletException {
        System.out.println("CreateAccountServlet:init() is called ");
    }

    TakeWebDAO<Account> dao = new AccountDAOImpl();
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        System.out.println("CreateAccountServlet:doPost() is called ");
        //super.doGet(req, resp);
        String id = req.getParameter("id");
        String firstName = req.getParameter("fname");
        String lname = req.getParameter("lname");
        String balanceString = req.getParameter("balance");
        System.out.println("Create Account servlet is called with values " + firstName + lname + balanceString);
        Account account = new Account();
        account.setFirstName(firstName);
        account.setLastName(lname);
        account.setBalance(Double.parseDouble(balanceString));

        AccountDAO accountDAO = new AccountDAO();
        accountDAO.createAccount(firstName, lname, Double.parseDouble(balanceString), id);
        dao.create(account);

        RequestDispatcher dispatcher = req.getRequestDispatcher("/accountListServlet");
        dispatcher.forward(req,resp);
    }

    @Override
    public void destroy() {
        System.out.println("CreateAccountServlet:doPost() is called ");
    }
}
