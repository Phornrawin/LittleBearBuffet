package controllers;

import callbacks.OnResult;
import listeners.LoadCompleteListener;
import listeners.OrderListener;
import listeners.PaymentListener;
import models.*;
import models.Package;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class FirebaseManagerAPI implements RealTimeDatabaseManager{

    private Set<LoadCompleteListener> loadCompleteListeners = new HashSet<LoadCompleteListener>();
    private Set<OrderListener> orderListeners = new HashSet<OrderListener>();
    public void start() {

    }

    public void addLoadCompleteListener(LoadCompleteListener listener) {

    }

    public void removeLoadCompleteListener(LoadCompleteListener listener) {

    }

    public void addOrderListener(OrderListener listener) {

    }

    public void removeOrderListener(OrderListener listener) {

    }

    public void addPaymentListener(PaymentListener listener) {

    }

    public void removePaymentListener(PaymentListener listener) {

    }

    public List<Item> getItems(Package pk) {
        return null;
    }

    public List<Package> getPackages() {
        return null;
    }

    public List<Category> getCategories() {
        return null;
    }

    public List<Order> getOrders(Payment payment) {
        return null;
    }

    public void addOrder(Order order, OnResult<Order> callback) {

    }

    public Payment selectPackage(Package pk, int table, OnResult<Payment> callback) {
        return null;
    }

    public void checkBill(Payment payment, OnResult<Payment> callback) {

    }
}
