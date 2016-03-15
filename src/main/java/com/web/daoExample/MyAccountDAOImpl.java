package com.web.daoExample;

import com.web.MyAccount;
import org.apache.poi.ss.formula.functions.T;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by devendra on 3/14/2016.
 */
public class MyAccountDAOImpl implements TakeWebDAO<MyAccount> {
    @Override
    public void update(MyAccount account) {
        try {
            Class.forName("org.postgresql.Driver");
            Connection conn = DriverManager.getConnection("jdbc:postgresql://localhost:5432/postgis_22_sample", "postgres", "123456");
            PreparedStatement preparedStatement = conn.prepareStatement("UPDATE myAccount SET firstname=?, lastname=?, balance=?, bankname=? WHERE id=?");
            preparedStatement.setInt(5,account.getId());
            preparedStatement.setString(1,account.getFirstName());
            preparedStatement.setString(2,account.getLastName());
            preparedStatement.setInt(3,account.getBalance());
            preparedStatement.setString(4, account.getBankName());
            int result = preparedStatement.executeUpdate();
            System.out.println(result);
            preparedStatement.close();
            conn.close();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    @Override
    public void delete(MyAccount account) {
        try {
            Class.forName("org.postgresql.Driver");
            Connection conn = DriverManager.getConnection("jdbc:postgresql://localhost:5432/postgis_22_sample", "postgres", "123456");
            PreparedStatement preparedStatement = conn.prepareStatement("DELETE from myAccount WHERE id=?");
            preparedStatement.setInt(1, account.getId());
            int result = preparedStatement.executeUpdate();
            preparedStatement.close();
            conn.close();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }



    @Override
    public MyAccount find(int id) {
        MyAccount account = new MyAccount();
        try {
            Class.forName("org.postgresql.Driver");
            Connection conn = DriverManager.getConnection("jdbc:postgresql://localhost:5432/postgis_22_sample", "postgres", "123456");
            PreparedStatement preparedStatement = conn.prepareStatement("SELECT  * from myAccount WHERE id=?");
            preparedStatement.setInt(1,id);
            ResultSet rs = preparedStatement.executeQuery();
            while ((rs.next())){
                account.setId(rs.getInt("id"));
                account.setFirstName(rs.getString("firstName"));
                account.setLastName(rs.getString("lastName"));
                account.setBalance(rs.getInt("balance"));
                account.setBankName(rs.getString("bankName"));
            }
            preparedStatement.close();
            conn.close();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return account;
    }




    @Override
    public List<MyAccount> finaAll() {
        List<MyAccount> accounts = new ArrayList<>();
        try {
            Class.forName("org.postgresql.Driver");
            Connection conn = DriverManager.getConnection("jdbc:postgresql://localhost5432/postgis_22_sample", "postgres", "123456");
            Statement statement = conn.createStatement();
            ResultSet rs = statement.executeQuery("SELECT  * FROM myAccount");
            while (rs.next()){
                MyAccount account = new MyAccount();
                account.setId(rs.getInt("id"));
                account.setFirstName(rs.getString("firstName"));
                account.setLastName(rs.getString("lastName"));
                account.setBalance(rs.getInt("balance"));
                account.setBankName(rs.getString("bankName"));
                accounts.add(account);
            }
            statement.close();
            conn.close();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }


        return accounts;
    }



    @Override
    public void create(MyAccount account) {
        try {
            Class.forName("org.postgresql.Driver");
            Connection conn = DriverManager.getConnection("jdbc:postgresql://localhost5432/postgis_22_sample", "postgres", "123456");
            PreparedStatement preparedStatement = conn.prepareStatement("INSERT  INTO myAccount (id, firstname, lastname, balance, bankname) VALUES (?,?,?,?,?)");
            preparedStatement.setInt(1,account.getId());
            preparedStatement.setString(2,account.getFirstName());
            preparedStatement.setString(3,account.getLastName());
            preparedStatement.setInt(4,account.getBalance());
            preparedStatement.setString(5,account.getBankName());
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    }

