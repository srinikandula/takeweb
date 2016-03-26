package com.web.keerthi;

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

/**
 * Created by CrazyNaveen on 3/11/16.
 */
@WebServlet(urlPatterns = {"/keeLoadList"})
public class LoadServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        //AccountDao dao = new AccountDao();
        //dao.findAll(Integer.parseInt(id));
        KeerthiAccount keerthiAccount = new KeerthiAccount();
        DaoInterface<KeerthiAccount> dao = new AccountDaoImpl();
        String id = req.getParameter("id");
        keerthiAccount.setId(Integer.parseInt(id));

        ServletContext ctxt = req.getSession().getServletContext();
        //get bean factory
        ApplicationContext appContext= WebApplicationContextUtils.getRequiredWebApplicationContext(ctxt);
        AccountDao accountDao  = (AccountDao) appContext.getBean("keerthidAccDao");
       KeerthiAccount acc = accountDao.find(Integer.parseInt(id));

      //  KeerthiAccount acc = dao.find(Integer.parseInt(id));
        req.setAttribute("account", acc);
        RequestDispatcher rd = req.getRequestDispatcher("loadAccount.jsp");
        rd.forward(req, res);
    }


    protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        doGet(req,res);

    }




    /*private List loadAccounts(int id) {
        PreparedStatement preparedStatement = null;
        ResultSet rs;
        Connection conn;
        List<KeerthiAccount> list = new ArrayList<>();
        try {
            Class.forName("org.postgresql.Driver");
            conn = DriverManager.getConnection("jdbc:postgresql://localhost:5432/workshop", "postgres", "keerthi");
            preparedStatement = conn.prepareStatement("SELECT * FROM accounts WHERE id = ?");
            preparedStatement.setInt(1,id);
            rs = preparedStatement.executeQuery();

            KeerthiAccount account = new KeerthiAccount();
            while (rs.next()) {
                account.setId(rs.getInt("id"));
                account.setUserName(rs.getString("name"));
                account.setAccNumber(rs.getLong("acc_num"));
                account.setBalance(rs.getDouble("balance"));
                list.add(account);
            }

            rs.close();
            preparedStatement.close();
            conn.close();
            return list;
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (!(preparedStatement.isClosed())) {
                    preparedStatement.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return list;
    }*/

}


