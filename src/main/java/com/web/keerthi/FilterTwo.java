package com.web.keerthi;

import javax.servlet.*;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * Created by CrazyNaveen on 3/15/16.
 */
public class FilterTwo implements javax.servlet.Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        System.out.println("second filter init method is called");
    }

    @Override
    public void doFilter(ServletRequest req, ServletResponse res, FilterChain filterChain) throws IOException, ServletException {
        System.out.println("second filter filter method is called");
        String userName = req.getParameter("uname");
        String passWord = req.getParameter("psw");
        System.out.println("servlet filter is called.....");
        if((passWord != null && passWord.equals("123") )){
            filterChain.doFilter(req,res);
        }
        else{
            PrintWriter pw = res.getWriter();

            RequestDispatcher dispatcher = req.getRequestDispatcher("signUp.html");
            dispatcher.forward(req,res);
            pw.println("Not a registered user.. Please sign Up");
        }

    }

    @Override
    public void destroy() {
        System.out.println("second filter destroy method is called");

    }
}
