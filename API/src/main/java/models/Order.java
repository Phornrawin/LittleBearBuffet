package models;

public class Order {
    private final String COOKING = "cooking";
    private String id;
    private int amount;
    private Item item;
    private int table;
    private String status;

    public Order(String id, int amount, Item item, int table) {
        this.id = id;
        this.amount = amount;
        this.item = item;
        this.table = table;
        status = COOKING;
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

    public int getTable() {
        return table;
    }

    public void increaseAmount(int amt){ this.amount += amt; }

    public void setStatus(String status){
        this.status = status;
    }

}
