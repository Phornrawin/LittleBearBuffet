package controllers;

import models.Category;
import models.Item;
import models.Order;
import models.Package;

import java.util.List;

public interface FirebaseObserver {
    void onItemAdd(Item item, List<Integer> packageAvailable);
    void onItemChange(Item item);
    void onItemDelete(Item item);
    void onPackageAdd(Package packageObj);
    void onPackageChange(Package packageObj);
    void onPackageDelete(Package packageObj);
    void onCategoryAdd(Category category);
    void onCategoryChange(Category category);
    void onCategoryDelete(Category category);
    void onOrderAdd(Order order);
    void onOrderChange(Order order);
    void onOrderDelete(Order order);
}
