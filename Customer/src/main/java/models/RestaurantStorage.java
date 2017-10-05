package models;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by PC301 on 27/9/2560.
 */
public class RestaurantStorage implements RestaurantManager {
    private List<Package> packages;
    private List<Category> categories = new ArrayList<Category>();

    public List<Package> getPackages() {
        return packages;
    }

    public List<Category> categories() {
        return categories;
    }

    public void setPackages(List<Package> packages) {
        this.packages = packages;
    }

    public void setCategories(List<Category> categories) {
        this.categories = categories;
    }

    public void addItemsToCategory(List<Item> items) {
        for(Item item : items){
            int cateId = item.getCategoryId();
            for(Category category : categories)
                if (category.getId() == cateId)
                    category.addItem(item);
        }
    }

    public void clearCategories() {
        for (Category category : categories){
            category.getItems().clear();
        }
    }

    @Override
    public void addCategory(Category category) {

    }

    @Override
    public void changeCategory(Category category) {

    }

    @Override
    public void removeCategory(Category category) {

    }

    @Override
    public void addPackage(Package packageObj) {

    }

    @Override
    public void changePacakage(Package packageObj) {

    }

    @Override
    public void removePackage(Package packageObj) {

    }

    @Override
    public void addItem(Item item) {
        
    }

    @Override
    public void changeItem(Item item) {

    }

    @Override
    public void removeItem(Item item) {

    }
}
