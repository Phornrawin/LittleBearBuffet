package models;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by PC301 on 27/9/2560.
 */
public class CustomerStorage implements CustomerManager{

    private int table;
    private int amount;
    private List<Order> orders = new ArrayList<Order>();
    private Package packageObj;

    public int getTable() {
        return table;
    }

    public List<Order> getOrders() {
        return orders;
    }

    public Package getPackageObj() {
        return packageObj;
    }

    public int getAmount() {
        return amount;
    }

    public void setTable(int table) {
        this.table = table;
    }

    public void setAmount(int amt) {
        this.amount = amt;
    }

    public void setPackageObj(Package packageObj) {
        this.packageObj = packageObj;
    }

    public void addOrder(Order order) {
        orders.add(order);
    }

    public void clearOrder() {
        orders.clear();
    }

    public double getTotalPrice() {
        return amount * packageObj.getPrice();
    }
}
