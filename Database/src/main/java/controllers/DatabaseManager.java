package controllers;

import models.Category;
import models.Item;
import models.Order;

import java.util.List;

public interface DatabaseManager {
    List<Category> loadCategories();
    Order addOrder(Order order);
    List<Integer> getCategoryIds();
    List<Integer> getItemIds();
    Category getCategory(int id);
    Item getItem(int id);
    boolean checkBill();
    void setUrl(String url);
}
