package models;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ItemTest {
    @Test
    void testToString() {
        Item corn = new Item(2,"Baby-Corn",5);
        assertEquals(corn.toString(),"Item : 2-Baby-Corn[5]");
    }

}