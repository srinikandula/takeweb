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
@WebServlet(urlPatterns = {"/loadAccountServletAmar"})
public class LoadAccountServletAmar extends HttpServlet{
    AmarTakeWebDAO<AmarAccount> dao = new AmarAccountImpl();
        @Override
        protected void service(HttpServletRequest request, HttpServletResponse resp) throws ServletException, IOException {
            String accountNumberString = request.getParameter("accnum");
           /* AmarAccount account = new AmarAccount();
            account.setAccountNumber(Integer.parseInt(accountNumberString));
            //AmarAccount acc =loadAccount(Integer.parseInt(accountNumberString));*/
            AmarAccount acc = dao.find(Integer.parseInt(accountNumberString));
            request.setAttribute("accounts", acc);
            RequestDispatcher dispatcher = request.getRequestDispatcher("loadAccountsListAmar.jsp");
            dispatcher.forward(request, resp);
        }
}
