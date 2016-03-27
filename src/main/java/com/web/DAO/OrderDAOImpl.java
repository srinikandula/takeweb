package com.web.DAO;

import com.web.model.Order;
import com.web.model.User;

import javax.sql.DataSource;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;


public class OrderDAOImpl implements OrderDAO<Order>{

    private String message;
    private String url;
    private String userName;
    private String password;
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


    public OrderDAOImpl(){

    }



    @Override
    public void createOrder(Order order, String username) {
        try {
            username = "marry";
            Connection conn = dataSource.getConnection();
            PreparedStatement preparedStatement = conn.prepareStatement("INSERT INTO public.\"ORDER\"(\"orderID\", \"Amount\", quantity, orderprice,\"userName\")VALUES (?, ?, ?, null,?)");
            preparedStatement.setInt(1, order.getOrderId());
            preparedStatement.setInt(2, order.getAmount());
            preparedStatement.setLong(3,order.getQuantity());
           // preparedStatement.setDouble(4,order.getOrderprice());
            preparedStatement.setString(4,username);
            int insertedRecords = preparedStatement.executeUpdate();
            if(insertedRecords == 1){
                System.out.println("created successfully");
            }
            preparedStatement.close();
            conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    @Override
    public int checkID(int orderId) {
        int count=0;
        try {
            Connection conn = dataSource.getConnection();
            PreparedStatement preparedStatement = conn.prepareStatement("SELECT count(*) FROM public.\"ORDER\" WHERE \"orderID\" = ?");
            preparedStatement.setInt(1, orderId);
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                count = resultSet.getInt("count");
            }
                resultSet.close();
            preparedStatement.close();
            conn.close();
            return count;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return count;
    }

    public void updateOrder(Order order){
        try {
            Connection conn = dataSource.getConnection();
            PreparedStatement preparedStatement = conn.prepareStatement("UPDATE public.\"ORDER\" SET  quantity=?, orderprice=? WHERE \"orderID\"=?");
            preparedStatement.setInt(1, order.getQuantity());
            preparedStatement.setInt(2, order.getOrderprice());
            preparedStatement.setInt(3, order.getOrderId());
            int insertedRecords = preparedStatement.executeUpdate();
            preparedStatement.close();
            conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void deleteOrder(Order order){
        try {
            Connection conn = dataSource.getConnection();
            PreparedStatement preparedStatement = conn.prepareStatement("DELETE FROM public.\"ORDER\" WHERE \"orderID\"=?");
            preparedStatement.setInt(1, order.getOrderId());
            int insertedRecords = preparedStatement.executeUpdate();
            preparedStatement.close();
            conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }
    public int findOrderAmount(Order order){
        try {
            Connection conn = dataSource.getConnection();
            PreparedStatement preparedStatement = conn.prepareStatement("SELECT \"Amount\" FROM public.\"ORDER\" WHERE \"orderID\"=?");
            preparedStatement.setInt(1, order.getOrderId());
            ResultSet resultSet = preparedStatement.executeQuery();
            int amount = -1;
            if(resultSet.next()){
                amount = resultSet.getInt("Amount");
            }
            resultSet.close();
            preparedStatement.close();
            conn.close();
            return amount;
        }catch (SQLException e) {
            e.printStackTrace();
        }
        return -1;
    }

    public List<Order> findOrders(){
        List<Order> orders = new ArrayList<>();
        try {
            //load the driver
            Connection conn = dataSource.getConnection();
            Statement statement = conn.createStatement();
            ResultSet rs = statement.executeQuery("select * from public.\"ORDER\"");
            while(rs.next()){
                Order order = new Order();
                order.setOrderId(rs.getInt("orderId"));
                order.setAmount(rs.getInt("Amount"));
                order.setQuantity(rs.getInt("quantity"));
                order.setOrderprice(rs.getInt("orderprice"));
                order.setUserName(rs.getString("userName"));
                orders.add(order);
            }
            rs.close();
            statement.close();
            conn.close();
        }catch (SQLException e) {
            e.printStackTrace();
        }
        return orders;
    }

    }


