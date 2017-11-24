package controllers;

import callbacks.OnResult;
import com.sun.org.apache.xpath.internal.operations.Or;
import listeners.OrderListener;
import models.*;
import models.Package;
import views.RootView;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Created by PC301 on 25/9/2560.
 */
public class MainController implements CoreController, OrderListener{

    private RealTimeDatabaseManager dbManager;
    private RestaurantManager restaurantManager;
    private CustomerManager customerManager;
    private Set<RootView> rootViews = new HashSet<>();



    public void start() {
        List<Package> packages = dbManager.getPackages();
        System.out.println("start");
        System.out.println("packages = " + packages);
        restaurantManager.setPackages(packages);

        List<Category> categories = dbManager.getCategories();
        restaurantManager.setCategories(categories);
    }

    public boolean addOrder(Order order) {
        dbManager.addOrder(order, new OnResult<Order>() {
            @Override
            public void onComplete(Order obj) {

            }

            @Override
            public void onFailure(Order obj) {
                // TODO back to home scene when add payment error
            }
        });
        return false;
    }

    @Override
    public void addOrder(Order order, OnResult<Order> callback) {
        dbManager.addOrder(order, callback);
    }

    @Override
    public void addOrders(List<Order> orders, OnResult<List<Order>> callback) {
        OrderAppender appender = new OrderAppender(orders, callback);
        appender.perform();
    }



    public void selectPackage(Package pk, int amount) {
        Payment payment = dbManager.selectPackage(pk, getTable(), amount, new OnResult<Payment>() {
            @Override
            public void onComplete(Payment obj) {

            }

            @Override
            public void onFailure(Payment obj) {

            }
        });
        customerManager.setPayment(payment);
        List<Item> items = dbManager.getItems(pk);
        System.out.println("load item complete");

        restaurantManager.addItemsToCategory(items);
    }

    public boolean checkBill() {
        dbManager.checkBill(customerManager.getPayment(), new OnResult<Payment>() {
            @Override
            public void onComplete(Payment obj) {

            }

            @Override
            public void onFailure(Payment obj) {

            }
        });
        return true;
    }

    public int getTable() {
        return customerManager.getTable();
    }

    public List<Package> getPackages() {
        return restaurantManager.getPackages();
    }

    public int getAmount() {
        return customerManager.getAmount();
    }

    public void setTable(int table) {
        customerManager.setTable(table);
    }

    public void setDatabaseManager(RealTimeDatabaseManager dbManager) {
        this.dbManager = dbManager;
        dbManager.addOrderListener(this);
    }

    public void setCustomerManager(CustomerManager customerManager) {
        this.customerManager = customerManager;
    }

    public void setRestaurantManager(RestaurantManager restaurantManager) {
        this.restaurantManager = restaurantManager;
    }

    public List<Category> getCategories() {
        return restaurantManager.getCategories();
    }

    @Override
    public void addRootView() {

    }

    public List<Order> getOrders() {
        return customerManager.getOrders();
    }

    public double getTotalPrice() {
        return customerManager.getTotalPrice();
    }

    public Package getCurrentPackage() {
        return customerManager.getPackageObj();
    }
    public void onCategoryDelete(Category category) {
        restaurantManager.removeCategory(category);
    }

    @Override
    public void onOrderAdd(Order order) {
        customerManager.addOrder(order);
        System.err.println("onOrder Add");
    }

    @Override
    public void onOrderChange(Order order) {
        customerManager.changeOrder(order);
    }

    @Override
    public void onOrderRemove(Order order) {

    }


    private class OrderAppender{
        private List<Order> orders;
        private OnResult<List<Order>> callback;
        private List<Order> complete = new ArrayList<>();
        private List<Order> failure = new ArrayList<>();
        private int count;
        public OrderAppender(List<Order> orders, OnResult<List<Order>> callback) {
            this.orders = orders;
            this.callback = callback;
            count = orders.size();
        }

        public void perform(){
            for (Order order: orders){
                order.setPayment(customerManager.getPayment());
                dbManager.addOrder(order, new OnResult<Order>() {
                    @Override
                    public void onComplete(Order obj) {
                        complete.add(obj);
                        customerManager.addOrder(obj);
                        checkSum();
                    }

                    @Override
                    public void onFailure(Order obj) {
                        failure.add(obj);
                        checkSum();
                    }
                });
            }
        }

        private void checkSum(){
            if (failure.size() + complete.size() == count){
                if (complete.size() > 0)
                    callback.onComplete(complete);
                if (failure.size() > 0)
                    callback.onFailure(failure);
            }
        }
    }
}
