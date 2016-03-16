package com.web;

import com.web.dao.UserDAO;
import com.web.model.User;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by skandula on 3/14/16.
 */
public class LoginServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) throws ServletException, IOException {
        //super.doPost(httpServletRequest, httpServletResponse);
        String userName = httpServletRequest.getParameter("username");
        String password = httpServletRequest.getParameter("password");
        UserDAO userDAO = new UserDAO();
        User user = userDAO.findUser(userName, password);
        if(user != null) {
            RequestDispatcher dispatcher = httpServletRequest.getRequestDispatcher("accountListServlet");

        }

    }
}
