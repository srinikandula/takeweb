package com.web.dao;

import com.web.model.Account;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by skandula on 3/12/16.
 */
public class AccountDAO {

    public void createAccount(String firstName, String lastName, double balance, String id) {
        try {
            //load the driver
            Class.forName("org.postgresql.Driver");
            Connection conn = DriverManager.getConnection("jdbc:postgresql://localhost:5432/postgres", "postgres", "postgres");
            PreparedStatement preparedStatement = conn.prepareStatement("INSERT INTO Accounts( firstname, lastname, balance, id) VALUES (?, ?, ?,?)");
            preparedStatement.setString(1, firstName);
            preparedStatement.setString(2, lastName);
            preparedStatement.setDouble(3, balance);
            preparedStatement.setInt(4, Integer.parseInt(id));
            int insertedRecords = preparedStatement.executeUpdate();
            preparedStatement.close();
            conn.close();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public void createAccount(Account account) {
        try {
            //load the driver
            Class.forName("org.postgresql.Driver");
            Connection conn = DriverManager.getConnection("jdbc:postgresql://localhost:5432/postgres", "postgres", "postgres");
            PreparedStatement preparedStatement = conn.prepareStatement("INSERT INTO Accounts( firstname, lastname, balance, id) VALUES (?, ?, ?,?)");
            preparedStatement.setString(1, account.getFirstName());
            preparedStatement.setString(2, account.getLastName());
            preparedStatement.setDouble(3, account.getBalance());
            preparedStatement.setInt(4, account.getId());
            int insertedRecords = preparedStatement.executeUpdate();
            preparedStatement.close();
            conn.close();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public void updateAccount(Account account){

    }
    public void deleteAccount(Account account){

    }
    public Account findAccount(int id){
        return null;
    }

    public List<Account> findAll() {
        List<Account> accounts = new ArrayList<>();
        try {
            //load the driver
            Class.forName("org.postgresql.Driver");
            Connection conn = DriverManager.getConnection("jdbc:postgresql://localhost:5432/postgres", "postgres", "postgres");
            Statement statement = conn.createStatement();
            ResultSet rs = statement.executeQuery("select * from accounts");
            while(rs.next()){
                Account account = new Account();
                account.setFirstName(rs.getString("firstName"));
                account.setLastName(rs.getString("lastName"));
                account.setId(rs.getInt("id"));
                account.setBalance(rs.getDouble("balance"));
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
}
