package controllers;

import models.Category;
import models.Item;
import models.Order;
import models.Package;

import java.util.List;

public interface DatabaseManager {
    Order addOrder(Order order);
    List<Integer> getCategoryIds();
    List<Integer> getItemIds();
    List<Integer> getItemIds(int packageID);
    List<Integer> getPackageIds();
    Category getCategory(int id);
    Item getItem(int id);
    Package getPackage(int id);
    boolean checkBill(Package packageObj, int amount);
    void setUrl(String url);
}
