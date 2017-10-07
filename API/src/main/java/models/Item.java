package models;

public class Item implements ModelObserable<Item> {
    private int id;
    private String name;
    private int categoryId;
    private int balance;


    public Item(int id, String name, int categoryId, int balance){
        this.id = id;
        this.name = name;
        this.categoryId = categoryId;
        this.balance = balance;
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
    }
    public int getBalance(){
        return balance;
    }

    @Override
    public String toString() {
        return "Item : " + id + "-" + name + "[" + categoryId + "]";
    }

    public boolean isAvailable(){
        return balance > 0;
    }

    public void regisObserver(ModelObserver<Item> observer) {

    }

    public void removeObserver(ModelObserver<Item> observer) {

    }
}
