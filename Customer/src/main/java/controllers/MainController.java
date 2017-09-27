package controllers;

import models.*;
import models.Package;

import java.util.List;

/**
 * Created by PC301 on 25/9/2560.
 */
public class MainController implements CoreController {

    private DatabaseManager dbManager;
    private RestaurantManager restaurantManager;
    private CustomerManager customerManager;


    public void start() {
        List<Package> packages = null;
        while(packages == null){
            System.out.println("loading packages ...");
            packages = dbManager.loadPackages();
        }
        restaurantManager.setPackages(packages);
    }

    public boolean addOrder(Order order) {
        Order newOrder = dbManager.addOrder(order);

        if (newOrder != null) {
            System.out.println("add order complete");
            customerManager.addOrder(order);
            return true;
        }
        System.out.println("add order fail");
        return false;
    }

    public void selectPackage(Package pk, int amount) {
        customerManager.setPackageObj(pk);
        customerManager.setAmount(amount);

        List<Item> items = null;
        while (items == null) {
            System.out.println("loading item list ...");
            items = dbManager.loadItems(pk);
        }
        System.out.println("load item complete");

        restaurantManager.addItemsToCategory(items);
    }

    public boolean checkBill() {
        Package pk = customerManager.getPackageObj();
        int amount = customerManager.getAmount();
        return dbManager.checkBill(pk, amount);
    }

    public int getTable() {
        return customerManager.getTable();
    }

    public void setTable(int table) {
        customerManager.setTable(table);
    }

    public void setDatabaseManager(DatabaseManager dbManager) {
        this.dbManager = dbManager;
    }

    public void setCustomerManager(CustomerManager customerManager) {
        this.customerManager = customerManager;
    }

    public void setRestaurantManager(RestaurantManager restaurantManager) {
        this.restaurantManager = restaurantManager;
    }

    public List<Category> getCategories() {
        return restaurantManager.categories();
    }


    public List<Order> getOrders() {
        return customerManager.getOrders();
    }
}
