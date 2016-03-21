package com.web.keerthi;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import com.web.keerthi.KeerthiUser;

/**
 * Created by CrazyNaveen on 3/16/16.
 */
@WebServlet(urlPatterns = {"/loginServlet"})

public class KeerthiLoginServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        System.out.println("Login servlet is called.....");

        String userName = req.getParameter("uname");
        String passWord = req.getParameter("pswd");
       // String confPassword = req.getParameter("pwd");
        SignUpDao dao = new SignUpDao();
        KeerthiUser user = dao.find(userName,passWord);
        if(user != null) {
            req.getSession().setAttribute("loggedinUser", user);
            RequestDispatcher dispatcher = req.getRequestDispatcher("keeAccountList");
            dispatcher.forward(req, res);
        }
        else{
            System.out.println("error....");
        }

    }
}
