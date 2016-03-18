package com.web.keerthi;

import com.web.dao.TakeWebDAO;

import java.sql.*;
import java.util.List;

/**
 * Created by CrazyNaveen on 3/16/16.
 */
public class SignUpDao implements TakeWebDAO<KeerthiUser> {


    public KeerthiUser find(String userName, String passWord){
        KeerthiUser user = null;
        //new KeerthiUser();
        try {
            Class.forName("org.postgresql.Driver");
            Connection conn = DriverManager.getConnection("jdbc:postgresql://localhost:5432/workshop","postgres","keerthi");
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
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
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

    }
}
