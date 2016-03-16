package com.web.AmarServlets;

import com.web.dao.AmarAccountDAO;
import com.web.dao.AmarAccountImpl;
import com.web.dao.AmarTakeWebDAO;
import com.web.model.AmarAccount;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

/**
 * Created by Amar on 3/14/2016.
 */

    public class CreateAccountServletAmar extends HttpServlet {
    AmarTakeWebDAO<AmarAccount> dao = new AmarAccountImpl();
        @Override
        protected void service(HttpServletRequest request, HttpServletResponse resp) throws ServletException, IOException {
            //super.doGet(req, resp);
            //AmarAccountDAO account = new AmarAccountDAO();
            String accountNumberString = request.getParameter("accnum");
            String firstName = request.getParameter("fname");
            String lastName = request.getParameter("lname");
            String balanceString = request.getParameter("balance");
            //AmarTakeWebDAO<AmarAccount> accountDAO = new AmarAccountImpl();
            //AmarAccount account = new AmarAccount();
            //List<AmarAccount> acc = accountDAO.create(AmarAccount account);
            //request.setAttribute("accounts", acc);
            AmarAccount account = new AmarAccount();
            account.setAccountNumber(Integer.parseInt(accountNumberString));
            account.setFirstName(firstName);
            account.setLastName(lastName);
            account.setBalance(Double.parseDouble(balanceString));
            System.out.println("Create Account servlet is called in AmarServlets with values "+ accountNumberString+firstName+lastName + balanceString);
            dao.create(account);
            RequestDispatcher dispatcher = request.getRequestDispatcher("/accountListServletAmar");
            dispatcher.forward(request,resp);
        }
}
