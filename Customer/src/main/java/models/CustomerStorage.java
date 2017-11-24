package models;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Created by PC301 on 27/9/2560.
 */
public class CustomerStorage implements CustomerManager{

    private int table;
    private List<Order> orders = new ArrayList<>();
    private Payment payment;

    public int getTable() {
        return table;
    }

    public List<Order> getOrders() {
        return orders;
    }

    @Override
    public Package getPackageObj() {
        return payment.getaPackage();
    }


    public int getAmount() {
        return payment.getAmt();
    }

    public void setTable(int table) {
        this.table = table;
    }



    public void addOrder(Order order) {
        orders.add(order);
    }

    @Override
    public void deleteOrder(Order order) {
        for (Order o: orders)
            if (o.getId().equals(order.getId()))
                orders.remove(o);
    }

    @Override
    public void changeOrder(Order order) {
        for (int i=0; i<orders.size(); i++)
            if (orders.get(i).getId().equals(order.getId()))
                orders.set(i, order);
    }

    public void clearOrder() {
        orders.clear();
    }

    public double getTotalPrice() {
        return payment.getAmt() * payment.getaPackage().getPrice();
    }

    @Override
    public void setPayment(Payment payment) {
        this.payment = payment;
    }

    @Override
    public Payment getPayment() {
        return payment;
    }
}
