package com.web.keerthi;

import com.web.model.KeerthiAccount;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by CrazyNaveen on 3/12/16.
 */
public class AccountDaoImpl implements DaoInterface<KeerthiAccount> {

    @Override
    public void update(KeerthiAccount account) {
        PreparedStatement preparedStatement = null;
        ResultSet rs;
        Connection conn;
        try {
            Class.forName("org.postgresql.Driver");
            conn = DriverManager.getConnection("jdbc:postgresql://localhost:5432/workshop", "postgres", "keerthi");

            preparedStatement = conn.prepareStatement("UPDATE accounts set name=?, acc_num=?, balance =?  WHERE id = ? ");
            preparedStatement.setInt(4, account.getId());
            preparedStatement.setString(1, account.getUserName());
            preparedStatement.setLong(2, account.getAccNumber());
            preparedStatement.setDouble(3, account.getBalance());

            int result = preparedStatement.executeUpdate();
            System.out.println("records status "+result);
            preparedStatement.close();
            conn.close();

        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (!(preparedStatement.isClosed())) {
                    preparedStatement.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public void delete(KeerthiAccount account) {
        try {
            Class.forName("org.postgresql.Driver");
            Connection conn = DriverManager.getConnection("jdbc:postgresql://localhost:5432/workshop", "postgres", "keerthi");
            PreparedStatement preparedStatement = conn.prepareStatement("delete from accounts where id =?");
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
    public KeerthiAccount find(int id) {
        KeerthiAccount account = new KeerthiAccount();
        try {
            Class.forName("org.postgresql.Driver");
            Connection conn = DriverManager.getConnection("jdbc:postgresql://localhost:5432/workshop", "postgres", "keerthi");
            PreparedStatement preparedStatement = conn.prepareStatement("SELECT * from accounts where id =?");
            //preparedStatement.setInt(1, account.getId());
            preparedStatement.setInt(1,id);
            ResultSet rs  = preparedStatement.executeQuery();
            while (rs.next()){
                account.setId(rs.getInt("id"));
                account.setUserName(rs.getString("name"));
                account.setAccNumber(rs.getLong("acc_num"));
                account.setBalance(rs.getDouble("balance"));
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
    public List<KeerthiAccount> findAll() {
        List<KeerthiAccount> accounts = new ArrayList<>();
        try {
            Class.forName("org.postgresql.Driver");
            Connection conn = DriverManager.getConnection("jdbc:postgresql://localhost:5432/workshop", "postgres", "keerthi");
            Statement statement = conn.createStatement();
            ResultSet rs = statement.executeQuery("select * from accounts");
            while(rs.next()){
                KeerthiAccount account = new KeerthiAccount();
                account.setId(rs.getInt("id"));
                account.setUserName(rs.getString("name"));
                account.setAccNumber(rs.getLong("acc_num"));
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

    @Override
    public void create(KeerthiAccount account) {
        try {
            Class.forName("org.postgresql.Driver");
            Connection conn = DriverManager.getConnection("jdbc:postgresql://localhost:5432/workshop", "postgres", "keerthi");
            PreparedStatement preparedStatement = conn.prepareStatement("INSERT INTO Accounts( id, name, acc_num , balance) VALUES (?, ?, ?,?)");
            preparedStatement.setInt(1, account.getId());
            preparedStatement.setString(2, account.getUserName());
            preparedStatement.setLong(3,account.getAccNumber() );
            preparedStatement.setDouble(4, account.getBalance());
            int insertedRecords = preparedStatement.executeUpdate();
            preparedStatement.close();
            conn.close();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
