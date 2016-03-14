package com.web.dao;

import com.web.model.AmarAccount;
import org.springframework.stereotype.Service;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Amar on 3/14/2016.
 */
@Service
public class AmarAccountImpl implements AmarTakeWebDAO<AmarAccount> {

    @Override
    public void update(AmarAccount account) {
        try {
            Class.forName("org.postgresql.Driver");
            String URL = "jdbc:postgresql://localhost:5432/postgres"; //HOST/DATABASE
            String USER = "postgres";
            String PASS = "Pass1234";
            Connection conn = DriverManager.getConnection(URL, USER, PASS);
            PreparedStatement stmt = conn.prepareStatement("SELECT * from account where accnum =?");
            stmt.setInt(1, account.getAccountNumber());
            ResultSet rs = stmt.executeQuery();
            if ((!rs.next())) {
                System.err.println("Account number is invalid, doesn't exist");
                return;
            } else {
                do{
                    PreparedStatement preparedStatement = conn.prepareStatement("update account set firstname =?, lastname =?, balance =? where accnum =?");
                    preparedStatement.setString(1, account.getFirstName());
                    preparedStatement.setString(2, account.getLastName());
                    preparedStatement.setDouble(3, account.getBalance());
                    preparedStatement.setInt(4, account.getAccountNumber());
                    int insertedRecords = preparedStatement.executeUpdate();
                    System.out.println("Records inserted :" + insertedRecords);
                    System.out.println("Account is updated successfully");
                    preparedStatement.close();
                }while (rs.next());
            }
            rs.close();
            stmt.close();
            conn.close();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void delete(AmarAccount account) {
        try {
            Class.forName("org.postgresql.Driver");
            String URL = "jdbc:postgresql://localhost:5432/postgres"; //HOST/DATABASE
            String USER = "postgres";
            String PASS = "Pass1234";
            Connection conn = DriverManager.getConnection(URL, USER, PASS);
            PreparedStatement stmt = conn.prepareStatement("SELECT * from account where accnum =?");
            stmt.setInt(1, account.getAccountNumber());
            ResultSet rs = stmt.executeQuery();
            if ((!rs.next())) {
                System.err.println("Account number is invalid, please choose the option again");
                return;
            } else {
                PreparedStatement smt = conn.prepareStatement("DELETE from account where accnum = ?");
                smt.setInt(1, account.getAccountNumber());
                smt.executeUpdate();
                System.out.println("Account is deleted successfully");
                smt.close();
            }
            rs.close();
            stmt.close();
            conn.close();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public AmarAccount find(int accnum) {
        AmarAccount account = new AmarAccount();
        try {
            //load the driver
            Class.forName("org.postgresql.Driver");
            Connection conn = DriverManager.getConnection("jdbc:postgresql://localhost:5432/postgres", "postgres", "Pass1234");
            PreparedStatement preparedStatement = conn.prepareStatement("select * from account where accnum = ?");
            preparedStatement.setDouble(1, accnum);
            ResultSet rs = preparedStatement.executeQuery();
            while(rs.next()){
                account.setAccountNumber(rs.getInt("accnum"));
                account.setFirstName(rs.getString("firstName"));
                account.setLastName(rs.getString("lastName"));
                account.setBalance(rs.getDouble("balance"));
            }
            rs.close();
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
    public List<AmarAccount> findAll() {
        List<AmarAccount> accounts = new ArrayList<>();
        try {
            //load the driver
            Class.forName("org.postgresql.Driver");
            Connection conn = DriverManager.getConnection("jdbc:postgresql://localhost:5432/postgres", "postgres", "Pass1234");
            Statement statement = conn.createStatement();
            ResultSet rs = statement.executeQuery("select * from account");
            while(rs.next()){
                AmarAccount account = new AmarAccount();
                account.setAccountNumber(rs.getInt("accnum"));
                account.setFirstName(rs.getString("firstName"));
                account.setLastName(rs.getString("lastName"));
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
    public void create(AmarAccount account) {
        try {
            //load the driver
            Class.forName("org.postgresql.Driver");
            Connection conn = DriverManager.getConnection("jdbc:postgresql://localhost:5432/postgres", "postgres", "postgres");
            PreparedStatement preparedStatement = conn.prepareStatement("INSERT INTO Account( firstname, lastname, balance, accnum,  ) VALUES (?, ?, ?,?)");
            preparedStatement.setString(1, account.getFirstName());
            preparedStatement.setString(2, account.getLastName());
            preparedStatement.setDouble(3, account.getBalance());
            preparedStatement.setInt(4, account.getAccountNumber());
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
