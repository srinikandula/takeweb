package com.web.AmarServlets;

import com.web.dao.AmarAccountImpl;
import com.web.dao.AmarTakeWebDAO;
import com.web.model.AmarAccount;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by Amar on 3/14/2016.
 */

    @WebServlet(urlPatterns = {"/updateAccountServletAmar"})
    public class UpdateServletAmar extends HttpServlet {
    AmarTakeWebDAO<AmarAccount> dao = new AmarAccountImpl();
        @Override
        protected void service(HttpServletRequest request, HttpServletResponse resp) throws ServletException, IOException {
            //super.doGet(req, resp);
            String accountNumberString = request.getParameter("accnum");
            String firstName = request.getParameter("fname");
            String lastName = request.getParameter("lname");
            String balanceString = request.getParameter("balance");
            AmarAccount account = new AmarAccount();
            account.setAccountNumber(Integer.parseInt(accountNumberString));
            account.setFirstName(firstName);
            account.setLastName(lastName);
            account.setBalance(Double.parseDouble(balanceString));
            dao.update(account);
            System.out.println("Update Account servlet is called with values "+ accountNumberString+firstName+lastName + balanceString);
            //updateAccount(Integer.parseInt(accountNumberString), firstName, lastName, Double.parseDouble(balanceString));
            RequestDispatcher dispatcher = request.getRequestDispatcher("/accountListServletAmar");
            dispatcher.forward(request,resp);
        }


    }
