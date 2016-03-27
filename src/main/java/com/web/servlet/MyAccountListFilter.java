package com.web.servlet;

import javax.annotation.processing.Filer;
import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import java.io.IOException;

/**
 * Created by devendra on 3/15/2016.
 */
@WebFilter(urlPatterns = {"/MyAccountListServlet"})
public class MyAccountListFilter implements javax.servlet.Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        System.out.println("Filter is called");
        String userName = servletRequest.getParameter("userName");
        if (userName != null && userName.equals("Apple")){
            filterChain.doFilter(servletRequest, servletResponse);
        } else  {
            RequestDispatcher requestDispatcher = servletRequest.getRequestDispatcher("Not Allowed");
            requestDispatcher.forward(servletRequest,servletResponse);
        }
    }

        @Override
        public void destroy () {

        }
    }
