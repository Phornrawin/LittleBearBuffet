package models;

public class Item {
    private int id;
    private String name;
    private int categoryId;

    public Item(int id, String name, int categoryId) {
        this.id = id;
        this.name = name;
        this.categoryId = categoryId;
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

    @Override
    public String toString() {
        return "Item : " + id + "-" + name + "[" + categoryId + "]";
    }
}
