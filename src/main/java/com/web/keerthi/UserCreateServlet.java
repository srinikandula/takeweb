package com.web.keerthi;

import com.web.dao.TakeWebDAO;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by CrazyNaveen on 3/18/16.
 */
@WebServlet(urlPatterns = {"/userCreate"})
public class UserCreateServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        String userName = req.getParameter("uname");
        String passWord = req.getParameter("pass");
        String id = req.getParameter("id");
        KeerthiUser user = (KeerthiUser) req.getSession().getAttribute("loggedinUser");
        String uName = user.getUserName();
        System.out.println(uName);
        // if (user != null) {
        user.setId(Integer.parseInt(id));
        user.setUserName(userName);
        user.setPassWord(passWord);
        TakeWebDAO dao = new SignUpDao();
        dao.create(user);
        RequestDispatcher dispatcher = req.getRequestDispatcher("keeAccountList");
        //RequestDispatcher dispatcher = req.getRequestDispatcher("keeCreate");
        dispatcher.forward(req, res);
    } /*else {
            RequestDispatcher dispatcher = req.getRequestDispatcher("UserCreate.html");
            dispatcher.forward(req, res);
        }*/
    // }


    protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        doGet(req,res);

    }


}
