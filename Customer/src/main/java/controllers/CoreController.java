package controllers;

import models.*;
import models.Package;
//import models.CustomerManager;
//import models.RestuarantManager;

import java.util.List;

/**
 * Created by PC301 on 25/9/2560.
 */
public interface CoreController {

    void start();
    boolean addOrder(Order order);
    void selectPackage(Package pk, int amount);
    boolean checkBill();
    int getTable();
    List<Package> getPackages();
    List<Order> getOrders();
    double getTotalPrice();
    Package getCurrentPackage();
    int getAmount();
    void setTable(int table);
    void setDatabaseManager(DatabaseManager dbManager);
    void setCustomerManager(CustomerManager customerManager);
    void setRestaurantManager(RestaurantManager restaurantManager);
    List<Category> getCategories();
    void addRootView();

}
