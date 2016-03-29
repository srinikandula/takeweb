package com.web.dao;

import com.web.model.Account;
import com.web.model.AmarAccount;
import org.springframework.stereotype.Service;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Amar on 3/14/2016.
 */
@Service
public class AmarAccountDAO {
    public void createAccount(int accountNumber, String firstName, String lastName, double balance) {
        try {
            Class.forName("org.postgresql.Driver");
            //System.out.println("print1");
            String URL = "jdbc:postgresql://localhost:5432/postgres"; //HOST/DATABASE
            String USER = "postgres";
            String PASS = "Pass1234";
            Connection conn = DriverManager.getConnection(URL, USER, PASS);
            //System.out.println("print2");
            PreparedStatement stmt = conn.prepareStatement("SELECT * from account where accnum =?");
            stmt.setInt(1, accountNumber);
            ResultSet rs = stmt.executeQuery();
            if ((!rs.next())) {
                PreparedStatement preparedStatement = conn.prepareStatement("INSERT INTO Account(accnum, firstname, lastname, balance) VALUES (?, ?, ?, ?)");
                preparedStatement.setInt(1, accountNumber);
                preparedStatement.setString(2, firstName);
                preparedStatement.setString(3, lastName);
                preparedStatement.setDouble(4, balance);
                int insertedRecords = preparedStatement.executeUpdate();
                System.out.println("Records inserted :" + insertedRecords);
                preparedStatement.close();
            } else {
                System.err.println("Account number already exists, please choose the option again");
                return;
            }
            rs.close();
            stmt.close();
            conn.close();
        }catch(ClassNotFoundException e) {
            e.printStackTrace();
            System.exit(1);
        }catch(SQLException e) {
            e.printStackTrace();
            System.exit(2);
        }

    }
    public void createAccount(AmarAccount account) {
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
    public void updateAccount(int accountNumber, String firstName, String lastName, double balance){
        try {
            Class.forName("org.postgresql.Driver");
            String URL = "jdbc:postgresql://localhost:5432/postgres"; //HOST/DATABASE
            String USER = "postgres";
            String PASS = "Pass1234";
            Connection conn = DriverManager.getConnection(URL, USER, PASS);
            PreparedStatement stmt = conn.prepareStatement("SELECT * from account where accnum =?");
            stmt.setInt(1, accountNumber);
            ResultSet rs = stmt.executeQuery();
            if ((!rs.next())) {
                System.err.println("Account number is invalid, doesn't exist");
                return;
            } else {
                do{
                    PreparedStatement preparedStatement = conn.prepareStatement("update account set firstname =?, lastname =?, balance =? where accnum =?");
                    preparedStatement.setString(1, firstName);
                    preparedStatement.setString(2, lastName);
                    preparedStatement.setDouble(3, balance);
                    preparedStatement.setInt(4, accountNumber);
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
    public void deleteAccount(int accountNumber){
        try {
            Class.forName("org.postgresql.Driver");
            String URL = "jdbc:postgresql://localhost:5432/postgres"; //HOST/DATABASE
            String USER = "postgres";
            String PASS = "Pass1234";
            Connection conn = DriverManager.getConnection(URL, USER, PASS);
            PreparedStatement stmt = conn.prepareStatement("SELECT * from account where accnum =?");
            stmt.setInt(1, accountNumber);
            ResultSet rs = stmt.executeQuery();
            if ((!rs.next())) {
                System.err.println("Account number is invalid, please choose the option again");
                return;
            } else {
                PreparedStatement smt = conn.prepareStatement("DELETE from account where accnum = ?");
                smt.setInt(1, accountNumber);
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
    public List<AmarAccount> findAllAccounts() {
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
    public AmarAccount findAccount(int accnum){
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
}
