package controllers;

import models.Category;
import models.CustomerManager;
import models.Order;
import models.Package;
import models.RestuarantManager;

import java.util.List;

/**
 * Created by PC301 on 25/9/2560.
 */
public interface CoreController {

    public void start();

    public boolean addOrder(Order order);

    public void selectPackage(Package pk, int amount);

    public boolean checkBill();

    public int getTable();

    public void setTable(int table);

    public void setDatabaseManager(DatabaseManager dbManager);

    public void setCustomerManager(CustomerManager customerManager);

    public void setRestuarantManager(RestuarantManager restuarantManager);

    public List<Category> getCategories();

    public List<Order> getOrders();

}
