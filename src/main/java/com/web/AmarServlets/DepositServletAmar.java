package com.web.AmarServlets;

import com.web.dao.AmarAccountImpl;
import com.web.dao.AmarTakeWebDAO;
import com.web.model.AmarAccount;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * Created by Amar on 3/14/2016.
 */
//@WebServlet(urlPatterns = {"/updateAccountServletAmar"})
public class DepositServletAmar extends HttpServlet {
    AmarTakeWebDAO<AmarAccount> dao = new AmarAccountImpl();

        @Override
        protected void service(HttpServletRequest request, HttpServletResponse resp) throws ServletException, IOException {
            //super.doGet(req, resp);
            String accountNumberString = request.getParameter("accnum");
            String depositString = request.getParameter("deposit");
            AmarAccount account = new AmarAccount();
            account.setAccountNumber(Integer.parseInt(accountNumberString));
            account.setBalance(Double.parseDouble(depositString));
            dao.update(account);
            System.out.println("Create Account servlet is called with values "+ accountNumberString+depositString);
            //makeDeposit(Integer.parseInt(accountNumberString), Double.parseDouble(depositString));
            PrintWriter pw = resp.getWriter();
            pw.write("Account has updated Successfully");
        }

    }
