package models;

import java.util.HashSet;
import java.util.Set;

public class Order implements ModelObserable<Order> {
    private final String COOKING = "cooking";
    private String id;
    private int amount;
    private Item item;
    private Payment payment;
    private String status;
    private Set<ModelObserver<Order>> observers;

    public Order() {
    }

    public Order(String id, int amount, Item item, Payment payment) {
        this.id = id;
        this.amount = amount;
        this.item = item;
        this.payment = payment;
        status = COOKING;
        observers = new HashSet<ModelObserver<Order>>();
    }

    public String getId() {
        return id;
    }

    public int getAmount() {
        return amount;
    }

    public Item getItem() {
        return item;
    }

    public Payment getPayment() {
        return payment;
    }

    public String getStatus(){
        return status;
    }

    public void increaseAmount(int amt){
        this.amount += amt;
        notifyObservers();
    }

    public void decreaseAmount(int amt){
        this.amount -= amt;
        notifyObservers();
    }

    public void setStatus(String status){
        this.status = status;
        notifyObservers();
    }

    public void regisObserver(ModelObserver<Order> observer) {
        observers.add(observer);
    }

    public void removeObserver(ModelObserver<Order> observer) {
        observers.remove(observer);
    }

    private void notifyObservers(){
        for(ModelObserver<Order> observer : observers)
            observer.onModelChange(this);
    }

    public void setPayment(Payment payment) {
        this.payment = payment;
    }


    @Override
    public String toString() {
        return "Order{" +
                "COOKING='" + COOKING + '\'' +
                ", id='" + id + '\'' +
                ", amount=" + amount +
                ", item=" + item +
                ", payment=" + payment +
                ", status='" + status + '\'' +
                ", observers=" + observers +
                '}';
    }
}
