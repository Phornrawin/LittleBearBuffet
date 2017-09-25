package controllers;

import models.Category;
import models.Order;

import java.util.ArrayList;

/**
 * Created by PC301 on 25/9/2560.
 */
public interface DatabaseManager {

    public ArrayList<Category> loadCategories();

    public Order addOrder(Order order);

    public boolean cancleOrder(Order order);

    public boolean cancleOrder(int orderId);

    public boolean checkBill();
}
