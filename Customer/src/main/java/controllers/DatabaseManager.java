package controllers;

import models.Category;
import models.Item;
import models.Order;
import models.Package;

import java.util.List;

/**
 * Created by PC301 on 25/9/2560.
 */
public interface DatabaseManager {

    public List<Category> loadCategories();

    public Order addOrder(Order order);

    public boolean checkBill(Package pk, int amount);

    public void setUrl(String url);

    public List<Package> loadPackages();

    public List<Item> loadItems(Package pk);
}
