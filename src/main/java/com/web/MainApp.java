package com.web;

import org.apache.commons.dbcp.BasicDataSource;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Amar on 3/13/2016.
 */
public class MainApp {
    public static void main(String[] args) {
        //load configuration and initialize a bean factory
        ApplicationContext context =
                new ClassPathXmlApplicationContext("applicationContext.xml");

        BasicDataSource dataSource = (BasicDataSource)context.getBean("dsource");
        try {
            System.out.println("before getConnection() MaxActive:"+dataSource.getMaxActive());
            System.out.println("before getConnection() MaxIdel:"+dataSource.getMaxIdle());
            System.out.println("before getConnection() initialsize:"+dataSource.getInitialSize());
            System.out.println("before getConnection() getNumActive:"+dataSource.getNumActive());
            Connection connection = null;
            List<Connection> connections = new ArrayList<>();
            for(int i=0;i<20;i++){
                connection = dataSource.getConnection();
                System.out.println(i+") after getConnection() getNumActive:"+dataSource.getNumActive() +" IdleConnections " + dataSource.getNumIdle());
                connections.add(connection);
            }
            for(int i=0;i<20;i++){
                connections.get(i).close();
                System.out.println(i + ") after getConnection().close getNumActive:" + dataSource.getNumActive() + " IdleConnections " + dataSource.getNumIdle());
            }
            System.out.println("after getConnection() getNumActive:"+dataSource.getNumActive());
            connection = dataSource.getConnection();
            Statement statement = connection.createStatement();
            ResultSet rs = statement.executeQuery("select * from accounts");
            while(rs.next()){
                System.out.println(rs.getString(1));
            }
            rs.close();
            statement.close();
            connection.close();
            System.out.println("after conneciton.close() MaxActive:" + dataSource.getMaxActive());
            System.out.println("after conneciton.close()  MaxIdel:" + dataSource.getMaxIdle());
            System.out.println("after connection.close() getNumActive:"+dataSource.getNumActive());

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
