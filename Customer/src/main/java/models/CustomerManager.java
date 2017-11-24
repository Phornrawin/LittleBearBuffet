package models;

import java.util.List;

/**
 * Created by PC301 on 27/9/2560.
 */
public interface CustomerManager {
    int getTable();
    List<Order> getOrders();
    Package getPackageObj();
    int getAmount();
    void setTable(int table);
    void addOrder(Order order);
    void deleteOrder(Order order);
    void changeOrder(Order order);
    void clearOrder();
    double getTotalPrice();
    void setPayment(Payment payment);
    Payment getPayment();
}
