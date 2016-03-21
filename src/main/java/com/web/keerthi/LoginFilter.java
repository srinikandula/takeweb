package com.web.keerthi;

import javax.servlet.*;
import javax.servlet.annotation.WebServlet;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * Created by CrazyNaveen on 3/15/16.
 */
//@WebServlet(urlPatterns = {"/signupFilter"})
public class LoginFilter implements javax.servlet.Filter{
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        System.out.println("init() is called");
    }

    @Override
    public void doFilter(ServletRequest req, ServletResponse res, FilterChain filterChain) throws IOException, ServletException {

        /*String userName = req.getParameter("uname");
        String passWord = req.getParameter("pswd");
        System.out.println("servlet filter is called.....");
        if((userName != null && userName.equals("java") && (passWord != null && passWord.equals("123")) )){
            filterChain.doFilter(req,res);
        }
        else{
           // PrintWriter pw = res.getWriter();
            //pw.println("Not a registered user.. Please sign Up");
            //RequestDispatcher dispatcher = req.getRequestDispatcher("signUp.html");
            //dispatcher.forward(req,res);
        }*/


    }

    @Override
    public void destroy() {
        System.out.println(" Destroy() is called");
    }
}
