package com.web.keerthi;

import com.web.model.KeerthiAccount;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by CrazyNaveen on 3/10/16.
 */
//@WebServlet(urlPatterns = {"/keerthiCreate"})
public class KeerthiCreate extends HttpServlet {
    protected void doPost(HttpServletRequest req, HttpServletResponse res) throws IOException, ServletException {
        AccountDao account = new AccountDao();
        String id = req.getParameter("id");
        String userName = req.getParameter("uname");
        String accNumber = req.getParameter("accNum");
        String balance =req.getParameter("balance");
        KeerthiAccount keerthiAccount =new KeerthiAccount();
        keerthiAccount.setId(Integer.parseInt(id));
        keerthiAccount.setUserName(userName);
       // keerthiAccount.setAccNumber(Long.parseLong(accNumber));
        keerthiAccount.setBalance(Double.parseDouble(balance));
        //account.createAccount(Integer.parseInt(id), userName, Long.parseLong(accNumber), Double.parseDouble( balance));
        KeerthiUser user = (KeerthiUser) (req.getSession().getAttribute("loggedinUser"));
        String userName1 = user.getUserName();

        keerthiAccount.setCreatedBy(userName1);
        DaoInterface<KeerthiAccount> accountDao = new AccountDaoImpl();
        accountDao.create(keerthiAccount);

        RequestDispatcher rd = req.getRequestDispatcher("keeAccountList");
        rd.forward(req,res);
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        doPost(req,res);
    }

    /*private void createAccount(int id,String name, long accNumber,double balance){

        PreparedStatement preparedStatement = null;
        ResultSet rs = null;
        Connection conn;
        try {
            Class.forName("org.postgresql.Driver");
            conn = DriverManager.getConnection("jdbc:postgresql://localhost:5432/workshop", "postgres", "keerthi");
            preparedStatement = conn.prepareStatement("INSERT INTO " +
                    "accounts(id,name,acc_num,balance)VALUES(?,?,?,?) ");
            preparedStatement.setInt(1, id);
            preparedStatement.setString(2, name);
            preparedStatement.setLong(3, accNumber);
            preparedStatement.setDouble(4, balance);

            int result = preparedStatement.executeUpdate();
            preparedStatement.close();
            conn.close();
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
    }*/
}
