package com.web.dao;

import com.web.model.Account;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

/**
 * Created by skandula on 3/12/16.
 */
public class AccountOracleDAOImpl implements TakeWebDAO<Account> {

    public void create(Account account) {
        try {
            //load the driver
            Class.forName("com.oracle.jdbc.Driver");
            Connection conn = DriverManager.getConnection("jdbc:oracle://localhost:5432/postgres", "postgres", "postgres");
            PreparedStatement preparedStatement = conn.prepareStatement("INSERT INTO Accounts( firstname, lastname, balance, id) VALUES (?, ?, ?,?)");
            /*preparedStatement.setString(1, firstName);
            preparedStatement.setString(2, lastName);
            preparedStatement.setDouble(3, balance);
            preparedStatement.setInt(4, Integer.parseInt(id));
            int insertedRecords = preparedStatement.executeUpdate();
            preparedStatement.close();
            conn.close();*/
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void update(Account account){

    }
    public void delete(Account account){

    }

    @Override
    public Account find(int id) {
        return null;
    }

    public Account findAccount(int id){
        return null;
    }
    public List<Account> findAll(){
        return null;
    }
}
