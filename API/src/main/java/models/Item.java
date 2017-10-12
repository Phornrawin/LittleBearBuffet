package models;

import java.util.HashSet;
import java.util.Set;

public class Item implements ModelObserable<Item> {
    private int id;
    private String name;
    private int categoryId;
    private int balance;
    private Set<ModelObserver<Item>> observers;


    public Item(int id, String name, int categoryId, int balance){
        this.id = id;
        this.name = name;
        this.categoryId = categoryId;
        this.balance = balance;
        observers = new HashSet<ModelObserver<Item>>();
    }
    public Item(int id, String name, int categoryId) {
        this(id, name, categoryId, 0);
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public int getCategoryId() {
        return categoryId;
    }

    public void setBalance(int balance){
        this.balance = balance;
        notifyObsrvers();
    }
    public int getBalance(){
        return balance;
    }

    public void increaseBalance(int amt){
        balance += amt;
        notifyObsrvers();
    }
    public void decreaseBalance(int amt){
        balance -= amt;
        notifyObsrvers();
    }
    @Override
    public String toString() {
        return "Item : " + id + "-" + name + "[" + categoryId + "]";
    }

    public boolean isAvailable(){
        return balance > 0;
    }

    public void regisObserver(ModelObserver<Item> observer) {
        observers.add(observer);
    }

    public void removeObserver(ModelObserver<Item> observer) {
        observers.remove(observer);
    }

    private void notifyObsrvers(){
        for (ModelObserver<Item> observer : observers)
            observer.onModelChange(this);
    }
}
