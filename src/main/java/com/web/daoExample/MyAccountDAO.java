package com.web.daoExample;

import com.web.MyAccount;
import com.web.model.Account;
import org.springframework.security.access.method.P;

import javax.sql.DataSource;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by devendra on 3/14/2016.
 */
public class MyAccountDAO {

    private DataSource dataSource;

    public DataSource getDataSource() {
        return dataSource;
    }

    public void setDataSource(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    public void createAccount (int id, String firstName, String lastName, int balance, String bankName){
        try {
            Connection conn = dataSource.getConnection();
           // Class.forName("org.postgresql.Driver");
            //Connection conn = DriverManager.getConnection("jdbc:postgresql://localhost5432/postgis_22_sample", "postgres", "123456");
            PreparedStatement preparedStatement = conn.prepareStatement("Insert INTO myAccount (id, firstname, lastname, balance, bankname) VALUES (?,?,?,?,?)");
            preparedStatement.setInt(1,id);
            preparedStatement.setString(2, firstName);
            preparedStatement.setString(3,lastName);
            preparedStatement.setInt(4,balance);
            preparedStatement.setString(5,bankName);
            int result = preparedStatement.executeUpdate();
            preparedStatement.close();
            conn.close();
        }  catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public void createAccount(MyAccount account) {
        try {

            Connection conn = dataSource.getConnection();
           // Class.forName("org.postgresql.Driver");
            //Connection conn = DriverManager.getConnection("jdbc:postgresql://localhost5432/postgis_22_sample", "postgres", "123456");
            PreparedStatement preparedStatement = conn.prepareStatement("INSERT  INTO myAccount(id, firstname, lastname, balance, bankname)VALUES (?,?,?,?,?)");
            preparedStatement.setInt(1,account.getId());
            preparedStatement.setString(2,account.getFirstName());
            preparedStatement.setString(3, account.getLastName());
            preparedStatement.setInt(4,account.getBalance());
            preparedStatement.setString(5, account.getBankName());
            int result= preparedStatement.executeUpdate();
            preparedStatement.close();
            conn.close();
        }  catch (SQLException e) {
            e.printStackTrace();
        }

    }
    public void updateAccount(MyAccount account){
        try {
            Connection conn = dataSource.getConnection();
           // Class.forName("org.postgresql.Driver");
            //Connection conn = DriverManager.getConnection("jdbc:postgresql://localhost:5432/postgis_22_sample", "postgres", "123456");
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
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    public void deleteAccount(MyAccount account){
        try {
            Connection conn = dataSource.getConnection();
            //Class.forName("org.postgresql.Driver");
            //Connection conn = DriverManager.getConnection("jdbc:postgresql://localhost:5432/postgis_22_sample", "postgres", "123456");
            PreparedStatement preparedStatement = conn.prepareStatement("DELETE from myAccount WHERE id=?");
            preparedStatement.setInt(1, account.getId());
            int result = preparedStatement.executeUpdate();
            preparedStatement.close();
            conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public MyAccount findAccount(int id){
        try {
            Connection conn = dataSource.getConnection();
           // Class.forName("org.postgresql.Driver");
            //Connection conn = DriverManager.getConnection("jdbc:postgresql://localhost:5432/postgis_22_sample", "postgres", "123456");
            PreparedStatement preparedStatement = conn.prepareStatement("SELECT from myAccount WHERE id=?");
            preparedStatement.setInt(1,id);
            int result = preparedStatement.executeUpdate();
            preparedStatement.close();
            conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    return null;
    }

    public List<MyAccount> findAll(){
        List<MyAccount> accounts = new ArrayList<>();
        try {
            Connection conn = dataSource.getConnection();
            //Class.forName("org.postgresql.Driver");
            //Connection conn = DriverManager.getConnection("jdbc:postgresql://localhost:5432/postgis_22_sample", "postgres", "123456");
            Statement statement = conn.createStatement();
            ResultSet rs = statement.executeQuery("SELECT  * FROM myAccount");
            while ((rs.next())){
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
        } catch (SQLException e) {
            e.printStackTrace();
        }
            return accounts;
    }
}


