package controllers;

import models.*;
import models.Package;

import java.util.List;

/**
 * Created by PC301 on 25/9/2560.
 */
public class MainController implements CoreController, FirebaseObserver{

    private DatabaseManager dbManager;
    private RestaurantManager restaurantManager;
    private CustomerManager customerManager;


    public void start() {
        List<Package> packages = null;
        while(packages == null){
            System.out.println("loading packages ...");
            packages = dbManager.loadPackages();
        }
        System.out.println("start");
        System.out.println("packages = " + packages);
        restaurantManager.setPackages(packages);

        List<Category> categories = null;
        while (categories == null){
            System.out.println("loading categories ...");
            categories = dbManager.loadCategories();
        }
        restaurantManager.setCategories(categories);
    }

    public boolean addOrder(Order order) {
        if(order.getTable() != customerManager.getTable()) {
            order = new Order(order.getId(), order.getAmount(), order.getItem(), customerManager.getTable());
        }
        Order newOrder = dbManager.addOrder(order);

        if (newOrder != null) {
            System.out.println("add order complete");
            customerManager.addOrder(order);
            return true;
        }
        System.out.println("add order fail");
        return false;
    }

    public void selectPackage(Package pk, int amount) {
        customerManager.setPackageObj(pk);
        customerManager.setAmount(amount);

        List<Item> items = null;
        while (items == null) {
            System.out.println("loading item list ...");
            items = dbManager.loadItems(pk);
        }
        System.out.println("load item complete");

        restaurantManager.addItemsToCategory(items);
    }

    public boolean checkBill() {
        Package pk = customerManager.getPackageObj();
        int amount = customerManager.getAmount();
        boolean isSuccess = dbManager.checkBill(pk, amount);
        if (isSuccess){
            customerManager.clearOrder();
            restaurantManager.clearCategories();
        }
        return isSuccess;
    }

    public int getTable() {
        return customerManager.getTable();
    }

    public List<Package> getPackages() {
        return restaurantManager.getPackages();
    }

    public int getAmount() {
        return customerManager.getAmount();
    }

    public void setTable(int table) {
        customerManager.setTable(table);
    }

    public void setDatabaseManager(DatabaseManager dbManager) {
        this.dbManager = dbManager;
    }

    public void setCustomerManager(CustomerManager customerManager) {
        this.customerManager = customerManager;
    }

    public void setRestaurantManager(RestaurantManager restaurantManager) {
        this.restaurantManager = restaurantManager;
    }

    public List<Category> getCategories() {
        return restaurantManager.categories();
    }

    public List<Order> getOrders() {
        return customerManager.getOrders();
    }

    public double getTotalPrice() {
        return customerManager.getTotalPrice();
    }

    public Package getCurrentPackage() {
        return customerManager.getPackageObj();
    }

    @Override
    public int getCurrentPackageId() {
        return customerManager.getPackageObj().getId();
    }

    @Override
    public void onItemAdd(Item item) {

    }

    @Override
    public void onItemChange(Item item) {

    }

    @Override
    public void onItemDelete(Item item) {

    }

    @Override
    public void onPackageAdd(Package packageObj) {

    }

    @Override
    public void onPackageChange(Package packageObj) {

    }

    @Override
    public void onPackageDelete(Package packageObj) {

    }

    @Override
    public void onCategoryAdd(Category category) {

    }

    @Override
    public void onCategoryChange(Category category) {

    }

    @Override
    public void onCategoryDelete(Category category) {

    }

    @Override
    public void onOrderAdd(Order order) {

    }

    @Override
    public void onOrderChange(Order order) {

    }

    @Override
    public void onOrderDelete(Order order) {

    }
}
