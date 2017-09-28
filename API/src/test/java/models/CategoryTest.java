package models;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class CategoryTest {
    Category category;
    Item asparagus;
    Item corn;
    List<Item> items;

    @BeforeEach
    void setUp() {
        category = new Category(5, "Vegetable");
        asparagus = new Item(1,"Asparagus",5);
        corn = new Item(2,"Baby-Corn",5);
        items = category.getItems();
        items.add(asparagus);
        items.add(corn);
    }

    @Test
    void addItem() {
        Item bmbooPulp = new Item(4,"Bamboo-Pulp",5);
        category.addItem(bmbooPulp);
        assertEquals(items.get(items.size()-1), bmbooPulp);
    }

    @Test
    void getItem() {
        assertEquals(category.getItem(1), asparagus);
        assertEquals(category.getItem(2), corn);
    }

    @Test
    void testToString() {
        assertEquals(category.toString(),"Category : 5-Vegetable");
    }

}