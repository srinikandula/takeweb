package com.web.keerthi;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * Created by CrazyNaveen on 3/16/16.
 */
@WebServlet(urlPatterns = {"/signupServlet"})
public class SignupServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {

        String userName =  req.getParameter("uname");
       // String phn = req.getParameter("phn");
       // String email = req.getParameter("email");
        String passWord = req.getParameter("pswd");



        if((userName != null)  && (passWord != null)){
            //if(passWord.equals(confirmPassword)) {
                System.out.println("Signup servlet is called...");
                RequestDispatcher dispatcher = req.getRequestDispatcher("home.html");
                dispatcher.forward(req, res);
            //}

        }
    }
}
