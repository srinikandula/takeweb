package com.web.daoExample;

import com.web.MyAccount;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by devendra on 3/14/2016.
 */
public class LoadServletDAO {



        protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

           // MyAccountDAO myAccountDAO = new MyAccountDAO();

            String id = req.getParameter("id");
            MyAccount account=new MyAccount();
            account.setId(Integer.parseInt(id));
            TakeWebDAO MyAccountDAO = new MyAccountDAOImpl();
            MyAccount acc = (MyAccount)MyAccountDAO.find(Integer.parseInt(id));
            //myAccount.setId(Integer.parseInt("id"));
            //myAccount acc = dao.find(Integer.parseInt("id"));
            req.setAttribute("account", acc);
            RequestDispatcher requestDispatcher = req.getRequestDispatcher("loadMyAccount.jsp");
            requestDispatcher.forward(req,resp);


        }
    }


