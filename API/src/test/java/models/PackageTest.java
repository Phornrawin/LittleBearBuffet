package models;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class PackageTest {
    @Test
    void testToString() {
        Package pandaSet = new Package(1,"Panda Set",299.0);
        assertEquals(pandaSet.toString(), "Package : 1-Panda Set (299.0)");
    }

}