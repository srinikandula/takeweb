package com.web.servlet;


import com.web.bean.PrototypeBean;
import com.web.bean.SingletonBean;
import com.web.dao.AccountDAO;
import com.web.model.Account;
import org.springframework.context.ApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

/**
 * Created by skandula on 3/10/16.
 */
@WebServlet(urlPatterns = {"/accountListServlet",})
public class AccountListServlet extends HttpServlet {

    @Override
    public void init(ServletConfig servletConfig) throws ServletException {
        String param = servletConfig.getInitParameter("param");
        System.out.println("AccountListServlet: init() is called");
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        System.out.println("AccountListServlet: goGet() is called");
        ServletContext ctxt = req.getSession().getServletContext();
        //get bean factory
        ApplicationContext appContext= WebApplicationContextUtils.getRequiredWebApplicationContext(ctxt);
        //look up the bean
        AccountDAO accountDAO = (AccountDAO)appContext.getBean("accountDAO");
        PrototypeBean prototypeBean = (PrototypeBean)appContext.getBean("prototypeBean");
        SingletonBean singletonBean = (SingletonBean)appContext.getBean("singletonBean");
        System.out.println("Prototype bean counter "+ prototypeBean.getCounter());
        System.out.println("Singleton bean counter "+ singletonBean.getCounter());
        List<Account> accounts = accountDAO.findAll();
        req.setAttribute("accounts", accounts);
        RequestDispatcher dispatcher = req.getRequestDispatcher("accountsList.jsp");
        dispatcher.forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) throws ServletException, IOException {
        doGet(httpServletRequest,httpServletResponse);
    }

    @Override
    public void destroy() {
        System.out.println("AccountListServlet: destroy() is called");
    }
}
