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
import org.springframework.context.ApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

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
        ServletContext ctxt = req.getSession().getServletContext();
        ApplicationContext appContext= WebApplicationContextUtils.getRequiredWebApplicationContext(ctxt);
        SignUpDao signUpDao = (SignUpDao) appContext.getBean("keerthiUserDao");
         KeerthiUser user = signUpDao.find(userName,passWord);
       // SignUpDao dao = new SignUpDao();
       // KeerthiUser user = dao.find(userName,passWord);
       // System.out.println(user);
        if(user != null) {
            req.getSession().setAttribute("loggedinUser", user);
            RequestDispatcher dispatcher = req.getRequestDispatcher("KeeCreate.html");
            dispatcher.forward(req, res);
        }
        else{
            //System.out.println("error....");
            RequestDispatcher dispatcher = req.getRequestDispatcher("UserCreate.html");
            dispatcher.forward(req, res);

        }

    }
}
