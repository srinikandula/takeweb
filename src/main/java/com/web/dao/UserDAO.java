package com.web.dao;

import com.web.model.User;

import javax.sql.DataSource;
import java.sql.*;
import java.util.List;

/**
 * Created by skandula on 3/14/16.
 */
public class UserDAO implements TakeWebDAO<User>{

    //dependency
    private DataSource dataSource;

    public DataSource getDataSource() {
        return dataSource;
    }

    public void setDataSource(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    public User findUser(String username, String password) {
        User user = null;
        try {
            Connection conn = dataSource.getConnection();
            PreparedStatement preparedStatement = conn.prepareStatement("select * from users where username=? and password =?");
            preparedStatement.setString(1, username);
            preparedStatement.setString(2, password);
            ResultSet rs = preparedStatement.executeQuery();
            if(rs.next()) {
                user = new User();
                user.setId(rs.getInt("id"));
                user.setUserName(rs.getString("username"));
                user.setPassword(rs.getString("password"));
            }
            preparedStatement.close();
            conn.close();
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
