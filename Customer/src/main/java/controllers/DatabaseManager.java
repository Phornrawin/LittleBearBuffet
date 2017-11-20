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

    List<Category> loadCategories();

    Order addOrder(Order order);

    boolean checkBill(Package pk, int amount);

    void setUrl(String url);

    List<Package> loadPackages();

    List<Item> loadItems(Package pk);

    void selectPackage(Package pk, int amt);
}
