package controllers;

import models.Category;
import models.Order;

import java.util.ArrayList;

/**
 * Created by PC301 on 25/9/2560.
 */
public class MainController implements CoreController {

    private ArrayList<Category> categories;
    private ArrayList<Order> orders;

    private DatabaseManager dbManager;
    public void start() {

    }

    public boolean addOrder(Order order) {
        Order orderWithId = dbManager.addOrder(order);

        if (orderWithId != null) {
            orders.add(orderWithId);
            return true;
        }
        return false;
    }

    public boolean checkBill() {
        return false;
    }

    public int getTable() {
        return 0;
    }

    public void setDatabaseManager(DatabaseManager dbManager) {
        this.dbManager = dbManager;
    }
}
