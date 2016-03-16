package com.web;

import javax.servlet.*;
import java.io.IOException;

/**
 * Created by skandula on 3/14/16.
 */
//@WebFilter(urlPatterns = {"/accountListServlet","/deleteAccountServlet"})
public class TestFilter implements Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        System.out.println("TestFilter:doFilter() is called");
        filterChain.doFilter(servletRequest, servletResponse);
    }

    @Override
    public void destroy() {

    }
}
