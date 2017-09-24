package models;

import java.util.ArrayList;
import java.util.List;

public class Category {
    private int id;
    private String name;
    private List<Item> items;

    public Category(int id, String name) {
        this.id = id;
        this.name = name;
        items = new ArrayList<Item>();
    }

    public void addItem(Item item){
        items.add(item);
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public List<Item> getItems() {
        return items;
    }
    public Item getItem(int itemId){
        for(Item item : items)
            if(item.getId() == itemId)
                return item;
        return null;
    }

}
