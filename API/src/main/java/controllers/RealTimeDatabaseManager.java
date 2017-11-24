package controllers;

import callbacks.OnResult;
import listeners.LoadCompleteListener;
import listeners.OrderListener;
import listeners.PaymentListener;
import models.*;
import models.Package;

import java.util.List;

public interface RealTimeDatabaseManager {
    void start();

    // Listener method
    void addLoadCompleteListener(LoadCompleteListener listener);
    void removeLoadCompleteListener(LoadCompleteListener listener);
    void addOrderListener(OrderListener listener);
    void removeOrderListener(OrderListener listener);
    void addPaymentListener(PaymentListener listener);
    void removePaymentListener(PaymentListener listener);

    // get data method
    List<Item> getItems(Package pk);
    List<Package> getPackages();
    List<Category> getCategories();
    List<Order> getOrders(Payment payment);

    void addOrder(Order order, OnResult<Order> callback);
    Payment selectPackage(Package pk, int table, int amt, OnResult<Payment> callback);
    void checkBill(Payment payment, OnResult<Payment> callback);
}
