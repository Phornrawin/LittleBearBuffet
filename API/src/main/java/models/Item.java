package models;

public class Item {
    private int id;
    private String name;
    private double price;
    private int categoryId;

    public Item(int id, String name, double price, int categoryId) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.categoryId = categoryId;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public double getPrice() {
        return price;
    }

    public int getCategoryId() {
        return categoryId;
    }

    @Override
    public String toString() {
        return "Item : " + id + "-" + name + " (" + price + ") " + "[" + categoryId + "]";
    }
}
