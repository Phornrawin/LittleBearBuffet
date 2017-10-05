package controllers;

import models.Category;
import models.Item;
import models.Order;
import models.Package;

import java.util.ArrayList;
import java.util.List;

public class FirebaseManager implements DatabaseManager, FirebaseObserable {
    private String url = "";
    private List<Package> packagesBuffer;
    private List<Item> itemsBuffer;
    private List<Category> categoriesBuffer;
    private List<FirebaseObserver> observers;

    public FirebaseManager() {
        packagesBuffer = new ArrayList<>();
        itemsBuffer = new ArrayList<>();
        categoriesBuffer = new ArrayList<>();
        observers = new ArrayList<>();
    }

    public void start(){
        
    }

    @Override
    public List<Category> loadCategories() {
        return null;
    }

    @Override
    public Order addOrder(Order order) {
        return null;
    }

    @Override
    public boolean checkBill(Package pk, int amount) {
        return false;
    }

    @Override
    public void setUrl(String url) {

    }

    @Override
    public List<Package> loadPackages() {
        return null;
    }

    @Override
    public List<Item> loadItems(Package pk) {
        return null;
    }

    @Override
    public void regisObserver(FirebaseObserver observer) {
        observers.add(observer);

    }

    @Override
    public void removeObserver(FirebaseObserver observer) {
        observers.remove(observer);
    }
}
