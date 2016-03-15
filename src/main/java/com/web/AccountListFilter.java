package com.web;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import java.io.IOException;

/**
 * Created by skandula on 3/14/16.
 */
//@WebFilter(urlPatterns = {"/accountListServlet","/deleteAccountServlet"})
public class AccountListFilter implements javax.servlet.Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        System.out.println("Filter is called");
        String username = servletRequest.getParameter("username");
        if(username != null && username.equals("joe")) {
            filterChain.doFilter(servletRequest, servletResponse);
        }else {
            RequestDispatcher dispatcher = servletRequest.getRequestDispatcher("login.html");
            dispatcher.forward(servletRequest, servletResponse);
        }
    }

    @Override
    public void destroy() {

    }
}
