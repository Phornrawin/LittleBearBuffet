package models;

import java.util.List;

/**
 * Created by PC301 on 27/9/2560.
 */
public interface RestaurantManager {
    List<Package> getPackages();
    List<Category> categories();
    void setPackages(List<Package> packages);
    void setCategories(List<Category> categories);
    void addItemsToCategory(List<Item> items);
    void clearCategories();
}
