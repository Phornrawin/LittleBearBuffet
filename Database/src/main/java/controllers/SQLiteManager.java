package controllers;

import models.Category;
import models.Item;
import models.Order;

import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class SQLiteManager implements DatabaseManager{

    private String url = "buffetDB.db";

    public List<Category> loadCategories(){

        return null;
    }

    public Order addOrder(Order order) {
        return null;
    }

    public List<Integer> getCategoryIds() {
        Connection connection = null;
        try {
            connection = prepareConnection();
            if (connection != null){
                String sql = "select id from category";
                Statement statement = connection.createStatement();
                ResultSet resultSet = statement.executeQuery(sql);

                List<Integer> ids = new ArrayList<Integer>();

                while (resultSet.next()){
                    int id = resultSet.getInt(1);

                    ids.add(id);
                }
                return ids;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            if (connection != null){
                try {
                    connection.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
        return null;
    }

    public List<Integer> getItemIds() {
        Connection connection = null;
        try {
            connection = prepareConnection();
            if (connection != null){
                String sql = "select id from item";
                Statement statement = connection.createStatement();
                ResultSet resultSet = statement.executeQuery(sql);

                List<Integer> ids = new ArrayList<Integer>();

                while(resultSet.next()){
                    int id = resultSet.getInt(1);
                    ids.add(id);
                }

                return ids;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            if (connection != null){
                try {
                    connection.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
        return null;
    }

    public Category getCategory(int id) {
        Connection connection = null;
        try {
            connection = prepareConnection();

            if (connection != null){
                String sql = "select * " +
                            "from category " +
                            "where id=" + id;
                Statement statement = connection.createStatement();
                ResultSet resultSet = statement.executeQuery(sql);

                if (resultSet.next()){
                    int categoryId = resultSet.getInt(1);
                    String name = resultSet.getString("name");

                    return new Category(categoryId, name);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            if(connection != null)
                try {
                    connection.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
        }

        return null;
    }

    public Item getItem(int id) {
        Connection connection;
        try{
            connection = prepareConnection();
            if(connection != null){
                String sql = "select * from item where id="+id;
                Statement statement = connection.createStatement();
                ResultSet resultSet = statement.executeQuery(sql);

                if (resultSet.next()){
                    int idItem = resultSet.getInt("id");
                    String name = resultSet.getString("name");
                    Double price = resultSet.getDouble("price");
                    int cateId = resultSet.getInt("cate_id");

                    return new Item(idItem, name, cateId);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public boolean checkBill() {
        return false;
    }

    private Connection prepareConnection(){
        try {
            Class.forName("org.sqlite.JDBC");
            String dbURL = "jdbc:sqlite:" + url;
            Connection conn = DriverManager.getConnection(dbURL);

            return conn;
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            System.err.println("connection Fail cannot find database");
        }

        return null;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}
