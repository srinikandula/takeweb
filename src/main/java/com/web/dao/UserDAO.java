package com.web.DAO;

import com.web.model.User;

import java.sql.*;
import java.util.List;

/**
 * Created by skandula on 3/14/16.
 */
public class UserDAO implements TakeWebDAO<User>{

    public User findUser(String username, String password) {
        User user = null;
        try {
            //load the driver
            Class.forName("org.postgresql.Driver");
            Connection conn = DriverManager.getConnection("jdbc:postgresql://localhost:5432/postgres", "postgres", "postgres");
            PreparedStatement preparedStatement = conn.prepareStatement("select * from users where username=? and password =?");
            preparedStatement.setString(1, username);
            preparedStatement.setString(2, password);
            ResultSet rs = preparedStatement.executeQuery();
            if(rs.next()) {
                user = new User();
                user.setUserName(rs.getString("username"));
                user.setPassword(rs.getString("password"));
            }
            preparedStatement.close();
            conn.close();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return user;
    }

    @Override
    public void update(User user) {

    }

    @Override
    public void delete(User user) {

    }

    @Override
    public User find(int id) {
        return null;
    }

    @Override
    public List<User> findAll() {
        return null;
    }

    @Override
    public void create(User user) {

    }
}
