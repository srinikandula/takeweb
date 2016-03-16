package com.web.AmarServlets;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import java.io.IOException;

/**
 * Created by Amar on 3/16/2016.
 */

public class LoginFilterServletAmar implements javax.servlet.Filter{

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        System.out.println("Login Filter is called");
        String username = servletRequest.getParameter("uname");
        String password = servletRequest.getParameter("pwd");
        if(username != null && username.equals("amar")) {
            filterChain.doFilter(servletRequest, servletResponse);
        }else {
            RequestDispatcher dispatcher = servletRequest.getRequestDispatcher("SignUpPageAmar.jsp");
            dispatcher.forward(servletRequest, servletResponse);
        }
    }

    @Override
    public void destroy() {

    }
}
