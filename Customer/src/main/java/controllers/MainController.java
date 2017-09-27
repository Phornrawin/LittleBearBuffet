package controllers;

import models.*;
import models.Package;

import java.util.List;

/**
 * Created by PC301 on 25/9/2560.
 */
public class MainController implements CoreController {

    private DatabaseManager dbManager;
//    private RestuarantManager restuarantManager;
//    private CustomerManager customerManager;


    public void start() {
        List<Package> packages = null;
        while (packages == null) {
            packages = dbManager.loadPackages();
        }

//        restuarantManager.setPackages(packages);
    }

    public boolean addOrder(Order order) {
        Order newOrder = dbManager.addOrder(order);

        if (newOrder != null) {
//            customerManager.addOrder(order);
            return true;
        }
        return false;
    }

    public void selectPackage(Package pk, int amount) {
//        customerManager.setPackage(pk);
//        customerManager.setAmount(amount);

        List<Item> items = null;
        while (items == null)
            items = dbManager.loadItems(pk);

//        customerManager.addItemsToCategory(items);
    }

    public boolean checkBill() {
//        Package pk = customerManager.getPackage();
//        int amount = customerManager.getAmount();
        return true;
//        return dbManager.checkBill(pk, amount);
    }

    public int getTable() {
        return 0;
    }

    public void setTable(int table) {

    }

    public void setDatabaseManager(DatabaseManager dbManager) {
        this.dbManager = dbManager;
    }

    public List<Category> getCategories() {
        return null;
    }

//    public void setCustomerManager(CustomerManager customerManager) {
//        this.customerManager = customerManager;
//    }

//    public void setRestuarantManager(RestuarantManager restuarantManager) {
//        this.restuarantManager = restuarantManager;
//    }

//    public List<Category> getCategories() {
//        return restuarantManager.getCategories();
//    }

    public List<Order> getOrders() {
        return null;
    }
}
