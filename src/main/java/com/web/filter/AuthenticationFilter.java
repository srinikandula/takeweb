package com.web.filter;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

/**
 * Created by skandula on 3/16/16.
 */
@WebFilter(urlPatterns = {"/accountListServlet","/deleteAccountServlet"})
public class AuthenticationFilter implements Filter{
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        if(request.getSession().getAttribute("user") == null){
            System.out.println("AuthenticationFilter: No login user found, redirecting to login page");
            request.getRequestDispatcher("login.html").forward(servletRequest, servletResponse);
        }else{
            System.out.println("AuthenticationFilter: user session found");
            filterChain.doFilter(servletRequest, servletResponse);
        }
    }

    @Override
    public void destroy() {

    }
}
