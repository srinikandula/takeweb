package com.web;
import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;
import java.util.*;
import javax.servlet.annotation.WebServlet;
import javax.servlet.annotation.WebFilter;
import com.web.DAO.*;
import org.springframework.context.ApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;
import javax.servlet.http.HttpServletRequest;

public class CheckFilter implements Filter {
    public void init(FilterConfig config) throws ServletException {

    }

    public void doFilter(ServletRequest servletrequest, ServletResponse response, FilterChain chain) throws IOException, ServletException {


        System.out.println("check filter is called");
        // Pass request back down the filter chain


        int orderId = Integer.parseInt(servletrequest.getParameter("orderId"));
        HttpServletRequest request = (HttpServletRequest) servletrequest;
        ServletContext ctxt = request.getSession().getServletContext();
        ApplicationContext appContext = WebApplicationContextUtils.getRequiredWebApplicationContext(ctxt);
        OrderDAO dao = (OrderDAO)appContext.getBean("OrderDAO");
        int check = dao.checkID(orderId);
        if (check == 1)
            chain.doFilter(request, response);
        else {
            //request.setAttribute("check" , "0");
            //request.setAttribute("message","Invalid");
           // request.setAttribute("selectedTab" ,"#tabs-2");
           // request.setAttribute("message" , "Invalid");
            //RequestDispatcher dispatch = request.getRequestDispatcher("/createOrder.jsp");
            //dispatch.forward(request,response);
            PrintWriter pw = response.getWriter();
            pw.write("OrderID is invalid");

        }
    }
    public void destroy() {
      /* Called before the Filter instance is removed
      from service by the web container*/
    }
}