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
import java.util.List;

/**
 * Created by Amar on 3/14/2016.
 */

    //@WebServlet(urlPatterns = {"/accountListServletAmar"})
    public class AccountListServletAmar extends HttpServlet {
        @Override
        protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
            AmarTakeWebDAO<AmarAccount> accountDAO = new AmarAccountImpl();
            List<AmarAccount> acc = accountDAO.findAll();
            req.setAttribute("accounts", acc);
            RequestDispatcher dispatcher = req.getRequestDispatcher("accountsListAmar.jsp");
            dispatcher.forward(req, resp);
        }
}
