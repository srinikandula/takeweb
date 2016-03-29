package com.web.DAO;

import com.web.model.Account;
import org.springframework.stereotype.Service;

import javax.servlet.ServletContext;
import javax.sql.DataSource;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by skandula on 3/12/16.
 */
@Service
public class AccountDAO {
    private String url;
    private String userName;
    private String password;

    private String message;
    //dependency
    private DataSource dataSource;


    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public DataSource getDataSource() {
        return dataSource;
    }

    public void setDataSource(DataSource dataSource) {
        this.dataSource = dataSource;
    }


    public AccountDAO(){

    }

    public Connection getConnection() {
        Connection connection = null;
        try {
            long start = System.currentTimeMillis();
            Class.forName("org.postgresql.Driver");
            connection = DriverManager.getConnection(this.url,this.userName, this.password);
            long end = System.currentTimeMillis();
            System.out.println("non pooled connection took "+ (end-start));
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return connection;
    }

    public Connection getPooledConnection() {
        Connection connection = null;
        try {
            long start = System.currentTimeMillis();
            connection = dataSource.getConnection();
            long end = System.currentTimeMillis();
            System.out.println("non pooled connection took " + (end - start));
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return connection;
    }
    public void createAccount(String firstName, String lastName, double balance, String id) {
        try {
            //load the driver
            Connection conn = getPooledConnection();
            PreparedStatement preparedStatement = conn.prepareStatement("INSERT INTO Accounts( firstname, lastname, balance, id) VALUES (?, ?, ?,?)");
            preparedStatement.setString(1, firstName);
            preparedStatement.setString(2, lastName);
            preparedStatement.setDouble(3, balance);
            preparedStatement.setInt(4, Integer.parseInt(id));
            int insertedRecords = preparedStatement.executeUpdate();
            preparedStatement.close();
            conn.close();
        }  catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public void createAccount(Account account) {
        try {
            //load the driver
            Connection conn = getPooledConnection();
            PreparedStatement preparedStatement = conn.prepareStatement("INSERT INTO Accounts( firstname, lastname, balance, id) VALUES (?, ?, ?,?)");
            preparedStatement.setString(1, account.getFirstName());
            preparedStatement.setString(2, account.getLastName());
            preparedStatement.setDouble(3, account.getBalance());
            preparedStatement.setInt(4, account.getId());
            int insertedRecords = preparedStatement.executeUpdate();
            preparedStatement.close();
            conn.close();
        }  catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public void updateAccount(Account account){

    }
    public void deleteAccount(Account account){

    }
    public void deleteAccount(int accountId){
        Connection connection = getPooledConnection();
        PreparedStatement preparedStatement = null;
        try {
            preparedStatement = connection.prepareStatement("delete from accounts where id =?");
            preparedStatement.setInt(1, accountId);
            int insertedRecords = preparedStatement.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            try {
                preparedStatement.close();
                connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }

        }
    }
    public Account findAccount(int id){

        return null;
    }

    public List<Account> findAll() {
        List<Account> accounts = new ArrayList<>();
        try {
            //load the driver
            Connection conn = getPooledConnection();
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
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return accounts;
    }
}
