package models;

public class Order {
    private int id;
    private int amount;
    private Item item;
    private int table;

    public Order(int id, int amount, Item item, int table) {
        this.id = id;
        this.amount = amount;
        this.item = item;
        this.table = table;
    }

    public int getId() {
        return id;
    }

    public int getAmount() {
        return amount;
    }

    public Item getItem() {
        return item;
    }

    public int getTable() {
        return table;
    }

    public void increaseAmount(int amt){ this.amount += amt; }


}
