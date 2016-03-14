package com.web.AmarServlets;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * Created by Amar on 3/14/2016.
 */
public class FindBalanceServletAmar extends HttpServlet {
    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //super.doGet(req, resp);
        String accountNumber = req.getParameter("accnum");
       /* System.out.println("Create Account servlet is called with values "+ accountNumber);
        double bal = findAccountBalance(Integer.parseInt(accountNumber));
        if (bal == -1){
            PrintWriter pw = resp.getWriter();
            pw.write("Account number is invalid,error in finding account balance.\n please choose existing account number");
        }else{
            req.setAttribute("bal", bal);
            RequestDispatcher dispatcher = req.getRequestDispatcher("accountBalAmar.jsp");
            dispatcher.forward(req, resp);
        }*/
    }
}
