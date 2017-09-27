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
    void setAmount(int amt);
    void setPackageObj(Package packageObj);
    void addOrder(Order order);
    void clearOrder();
    double getTotalPrice();
}
