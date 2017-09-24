package controllers;

import models.Category;
import models.Order;

import java.util.List;

public interface DatabaseManager {
    List<Category> loadCategories();
    Order addOrder(Order order);
    boolean checkBill();
}
