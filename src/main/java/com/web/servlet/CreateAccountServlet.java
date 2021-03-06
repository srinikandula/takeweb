/**
 * Created by skandula on 3/9/16.
 */
package com.web.servlet;

import com.web.DAO.AccountDAO;
import com.web.DAO.AccountDAOImpl;
import com.web.DAO.TakeWebDAO;
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
<<<<<<< HEAD:src/main/java/com/web/CreateAccountServlet.java

=======
@WebServlet(urlPatterns = {"/createAccount"})
>>>>>>> e9afa4a5644ac6e192f3bfc3a4a809aad5b24c05:src/main/java/com/web/servlet/CreateAccountServlet.java
public class CreateAccountServlet extends HttpServlet{
    @Override
    public void init(ServletConfig servletConfig) throws ServletException {
        System.out.println("CreateAccountServlet:init() is called ");
    }

    TakeWebDAO<Account> dao = new AccountDAOImpl();
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        System.out.println("CreateAccountServlet:doPost() is called ");
        //super.doGet(req, resp);
        String id = req.getParameter("id");
        String firstName = req.getParameter("fname");
        String lname = req.getParameter("lname");
        String balanceString = req.getParameter("balance");
        System.out.println("Create Account servlet is called with values " + firstName + lname + balanceString);
        Account account = new Account();
        account.setFirstName(firstName);
        account.setLastName(lname);
        account.setBalance(Double.parseDouble(balanceString));
        ServletContext context = req.getServletContext();
        ApplicationContext appContext= WebApplicationContextUtils.getRequiredWebApplicationContext(context);
        AccountDAO accountDAO = (AccountDAO)appContext.getBean("accountDAO");
        accountDAO.createAccount(firstName, lname, Double.parseDouble(balanceString), id);
        dao.create(account);

        RequestDispatcher dispatcher = req.getRequestDispatcher("/accountListServlet");
        dispatcher.forward(req,resp);
    }

    @Override
    public void destroy() {
        System.out.println("CreateAccountServlet:doPost() is called ");
    }
}
