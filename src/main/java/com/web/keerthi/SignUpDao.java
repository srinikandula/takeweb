package com.web.keerthi;

import com.web.dao.TakeWebDAO;

import javax.sql.DataSource;
import java.sql.*;
import java.util.List;

/**
 * Created by CrazyNaveen on 3/16/16.
 */
public class SignUpDao implements TakeWebDAO<KeerthiUser> {



    private DataSource dataSource;

    public DataSource getDataSource() {
        return dataSource;
    }

    public void setDataSource(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    public KeerthiUser find(String userName, String passWord){
        KeerthiUser user = null;
        //new KeerthiUser();
        try {
            Connection conn  = dataSource.getConnection();
           // Class.forName("org.postgresql.Driver");
            //Connection conn = DriverManager.getConnection("jdbc:postgresql://localhost:5432/workshop","postgres","keerthi");
            PreparedStatement ps = conn.prepareStatement("SELECT * FROM users WHERE user_name=? and pass_word=?");
            ps.setString(1,userName);
            ps.setString(2,passWord);
            ResultSet rs = ps.executeQuery();

            if (rs.next()){
                user = new KeerthiUser();
                user.setId(rs.getInt("id"));
                user.setUserName(rs.getString("user_name"));
                user.setPassWord(rs.getString("pass_word"));
            }

            System.out.println(userName);
            System.out.println(passWord);
            //rs.close();
            ps.close();
            conn.close();
            return user;
        }  catch (SQLException e) {
            e.printStackTrace();
        }
        return user;
    }



    @Override
    public void update(KeerthiUser keerthiUser) {

    }

    @Override
    public void delete(KeerthiUser keerthiUser) {

    }

    @Override
    public KeerthiUser find(int id) {
        return null;
    }

    @Override
    public List<KeerthiUser> findAll() {
        return null;
    }


    @Override
    public void create(KeerthiUser keerthiUser) {

        PreparedStatement preparedStatement = null;
        ResultSet rs = null;
      //  Connection conn;
        try {
            Connection conn = dataSource.getConnection();
            //Class.forName("org.postgresql.Driver");
            //conn = DriverManager.getConnection("jdbc:postgresql://localhost:5432/workshop", "postgres", "keerthi");
            preparedStatement = conn.prepareStatement("INSERT INTO " +
                    "users(user_name,pass_word, id)VALUES(?,?,?) ");
            preparedStatement.setInt(3, keerthiUser.getId());
            preparedStatement.setString(1, keerthiUser.getUserName());
            preparedStatement.setString(2, keerthiUser.getPassWord());
           // preparedStatement.setString(4,keerthiUser.getCreatedBy());
            int result = preparedStatement.executeUpdate();
            preparedStatement.close();
            conn.close();
        }  catch (SQLException e) {
            e.printStackTrace();
        } /*finally {
            try {
                if (!(preparedStatement.isClosed())) {
                    preparedStatement.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }*/
        }
    }



