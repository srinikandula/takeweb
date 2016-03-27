package com.web.DAO;

import com.web.model.User;

import javax.sql.DataSource;
import java.sql.*;

/**
 * Created by njonnala on 3/16/2016.
 */
public class LoginDAO  {

    public DataSource getDataSource() {
        return dataSource;
    }

    public void setDataSource(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    private DataSource dataSource;



    private User user;

    public User getUser() {
        return user;
    }
    public void setUser(User user){this.user = user;}

    public User checklogin(String userName, String password) {
        //User user = null;
        try {
            Connection conn = dataSource.getConnection();
            //System.out.println(userName);
            //System.out.println(password);
            PreparedStatement preparedStatement = conn.prepareStatement("SELECT \"userName\", password  FROM public.\"LOGIN\" where \"userName\"=? and password=?");
            preparedStatement.setString(1, userName);
            preparedStatement.setString(2,password);
            ResultSet rs = preparedStatement.executeQuery();
           // System.out.println("from dao outside"+rs.next());
            if(rs.next()) {
                //user = new User();
                System.out.println("from dao"+rs.getString("userName"));
                user.setUserName(rs.getString("userName"));
                user.setPassword(rs.getString("password"));
            }
            rs.close();
            preparedStatement.close();
            conn.close();
            return user;
        }  catch (SQLException e) {
            e.printStackTrace();
        }
        return user;

    }

    public void createUser(User user){
        try {
            Connection conn = dataSource.getConnection();
            PreparedStatement preparedStatement = conn.prepareStatement("INSERT INTO public.\"LOGIN\"(\"userName\", password, \"firstname\", \"lastname\" )VALUES (?, ?, ?, ?)");
            preparedStatement.setString(1, user.getUserName());
            preparedStatement.setString(2,user.getPassword());
            preparedStatement.setString(3,user.getFirstName());
            preparedStatement.setString(4,user.getLastName());
            int insertedRecords = preparedStatement.executeUpdate();
            preparedStatement.close();
            conn.close();
            }
                catch (SQLException e) {
                e.printStackTrace();
            }

    }
}
