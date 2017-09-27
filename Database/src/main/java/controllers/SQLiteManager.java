package controllers;

import models.Category;
import models.Item;
import models.Order;
import models.Package;

import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.sql.*;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class SQLiteManager implements DatabaseManager{

    private String url = "buffetDB.db";


    public Order addOrder(Order order) {
        Connection connection = null;
        try {
            connection = prepareConnection();
            if (connection != null){
                Calendar calendar = Calendar.getInstance();
                SimpleDateFormat format = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");
                int amt = order.getAmount();
                int table = order.getTable();
                int itemId = order.getItem().getId();
                String timeStamp = format.format(calendar.getTime());
                String sql = "insert into 'order' " +
                             "(amount, 'table', item_id, time_stamp) " +
                             "values ('" + amt + "', '" + table + "', '" + itemId + "', '" + timeStamp + "')";
                Statement statement = connection.createStatement();
                int result = statement.executeUpdate(sql);
                System.out.println("insert result " + result);

                sql = "select * " +
                        "from 'order' " +
                        "where amount='" + amt + "' and 'order'.'table'='" + table + "' and item_id='" + itemId + "' and time_stamp='" + timeStamp + "'";
                System.out.println("sql = " + sql);
                ResultSet resultSet = statement.executeQuery(sql);

//                System.out.println("resultSet. = " + resultSet.);
                if(resultSet.next()){
                    int id = resultSet.getInt("id");
                    Order newOrder = new Order(id, amt, order.getItem(), table);

                    return newOrder;
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            if (connection != null)
                try {
                    connection.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
        }
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

    public List<Integer> getItemIds(int packageID) {
        Connection connection = null;
        try {
            connection = prepareConnection();
            if (connection != null){
                String sql = "select id from item join package_item on package_item.item_id=item.id where package_id=" + packageID;
                System.out.println("sql = " + sql);
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

    public List<Integer> getPackageIds() {
        Connection connection = null;
        try {
            connection = prepareConnection();
            if (connection != null){
                String sql = "select id from package";
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
                    System.out.println("close connection");
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
        Connection connection = null;
        try{
            connection = prepareConnection();
            if(connection != null){
                String sql = "select * from item where id="+id;
                Statement statement = connection.createStatement();
                ResultSet resultSet = statement.executeQuery(sql);

                if (resultSet.next()){
                    int idItem = resultSet.getInt("id");
                    String name = resultSet.getString("name");
                    int cateId = resultSet.getInt("cate_id");

                    return new Item(idItem, name, cateId);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            if (connection != null)
                try {
                    connection.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
        }
        return null;
    }

    public Package getPackage(int id) {
        Connection connection = null;
        try{
            connection = prepareConnection();
            if(connection != null){
                String sql = "select * from package where id="+id;
                Statement statement = connection.createStatement();
                ResultSet resultSet = statement.executeQuery(sql);

                if (resultSet.next()){
                    int idItem = resultSet.getInt("id");
                    String name = resultSet.getString("name");
                    double price = resultSet.getDouble("price");

                    return new Package(id, name, price);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            if (connection != null)
                try {
                    System.out.println("close connect");
                    connection.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
        }
        return null;
    }

    public boolean checkBill(Package packageObj, int amount) {
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
