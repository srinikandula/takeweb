package com.web.keerthi;

import com.web.model.Account;
import com.web.model.KeerthiAccount;
import org.springframework.context.ApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

/**
 * Created by CrazyNaveen on 3/10/16.
 */
@WebServlet(urlPatterns = {"/keeAccountList"})
public class KeeAccountList extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        ServletContext servletContext = req.getSession().getServletContext();
       // DaoInterface<KeerthiAccount> dao = new AccountDaoImpl();
        AccountDao accountDao = new AccountDao();

       // ServletContext ctxt = req.getSession().getServletContext();
        //get bean factory
        ApplicationContext appContext= WebApplicationContextUtils.getRequiredWebApplicationContext(servletContext);
        AccountDao dao  = (AccountDao) appContext.getBean("keerthidAccDao");
         List<KeerthiAccount>  accounts = dao.findAll();

       // List<KeerthiAccount> accounts = accountDao.findAll();
        req.setAttribute("accounts", accounts);
        RequestDispatcher rd = req.getRequestDispatcher("kaccountList.jsp");
        rd.forward(req, res);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        doGet(req,res);

    }

    /*private List<KeerthiAccount> accountList() {
        List<KeerthiAccount> list = new ArrayList<>();
        Statement statement= null ;
        ResultSet rs = null;
        Connection conn = null;
        try {
            Class.forName("org.postgresql.Driver");
            conn = DriverManager.getConnection("jdbc:postgresql://localhost:5432/workshop", "postgres", "keerthi");
            statement = conn.createStatement();
             rs = statement.executeQuery("SELECT * FROM accounts");
            while (rs.next()){
                KeerthiAccount account = new KeerthiAccount();
                account.setId(rs.getInt("id"));
                account.setUserName(rs.getString("name"));
                account.setAccNumber(rs.getLong("acc_num"));
                account.setBalance(rs.getDouble("balance"));
                list.add(account);
            }
            rs.close();
            statement.close();
            conn.close();

        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        finally {
            try {
                if(!(rs.isClosed())){
                    rs.close();
                }
                if(!(statement.isClosed())){
                    statement.close();
                }
                if(!(conn.isClosed())){
                    conn.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        return list;
    }*/

    }






