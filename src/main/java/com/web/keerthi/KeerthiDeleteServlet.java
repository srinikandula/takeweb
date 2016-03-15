package com.web.keerthi;

import com.web.model.KeerthiAccount;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by CrazyNaveen on 3/11/16.
 */
@WebServlet(urlPatterns = {"/keerthiDelete"})
public class KeerthiDeleteServlet extends HttpServlet {
    @Override
    protected void service(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        //AccountDao accountDao = new AccountDao();
        DaoInterface<KeerthiAccount> dao = new AccountDaoImpl();
        KeerthiAccount keerthiAccount = new KeerthiAccount();
        String id = req.getParameter("id");
        keerthiAccount.setId(Integer.parseInt(id));
        dao.delete(keerthiAccount);
        //dao.deleteAccount(Integer.parseInt(id));
        RequestDispatcher rd = req.getRequestDispatcher("/keeAccountList");
        rd.forward(req,res);
    }

    /*private void deleteAccount(String id){
        try {
            Class.forName("org.postgresql.Driver");
            Connection conn = DriverManager.getConnection("jdbc:postgresql://localhost:5432/workshop", "postgres", "keerthi");
            PreparedStatement preparedStatement = conn.prepareStatement("delete from accounts where id =?");
            preparedStatement.setInt(1, Integer.parseInt(id));
            int result = preparedStatement.executeUpdate();
            preparedStatement.close();
            conn.close();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }*/
}
