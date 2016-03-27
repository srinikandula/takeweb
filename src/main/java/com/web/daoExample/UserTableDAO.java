package com.web.daoExample;

import com.web.model.MyUser;

import javax.sql.DataSource;
import java.sql.*;
import java.util.List;


/**
 * Created by devendra on 3/17/2016.
 */
public class UserTableDAO implements TakeWebDAO<MyUser> {


    private DataSource dataSource;


    public DataSource getDataSource() {
        return dataSource;
    }

    public void setDataSource(DataSource dataSource) {
        this.dataSource = dataSource;
    }


    public MyUser findUser(String username, String password) {
        MyUser myUser = null;
        try {
              Connection conn = dataSource.getConnection();
           // Class.forName("org.postgresql.Driver");
            //Connection conn = DriverManager.getConnection("jdbc:postgresql://localhost:5432/postgis_22_sample", "postgres", "123456");
            PreparedStatement preparedStatement = conn.prepareStatement("select * from userTable where username=? and password =?");
            preparedStatement.setString(1, username);
            preparedStatement.setString(2, password);
            ResultSet rs = preparedStatement.executeQuery();
            if(rs.next()){
                myUser = new MyUser();
                myUser.setId(rs.getInt("id"));
                myUser.setUserName(rs.getString("username"));
                myUser.setPassword(rs.getString("password"));
            }
            preparedStatement.close();
            conn.close();
       // } catch (ClassNotFoundException e) {
         //   e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return myUser;
    }

    @Override
    public void update(MyUser myUser) {

    }

    @Override
    public void delete(MyUser myUser) {

    }

    @Override
    public MyUser find(int id) {
        return null;
    }

    @Override
    public List<MyUser> finaAll() {
        return null;
    }

    @Override
    public void create(MyUser myUser) {

    }
}
