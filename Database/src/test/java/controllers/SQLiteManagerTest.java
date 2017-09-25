package controllers;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Created by PC301 on 25/9/2560.
 */
class SQLiteManagerTest {
    private SQLiteManager sqLiteManage;

    @BeforeEach
    void setUp() {
        sqLiteManage = new SQLiteManager();
    }

    @AfterEach
    void tearDown() {

    }

    @Test
    void testGetCategoryId(){
        System.out.println("test get category id");
        List<Integer> ids = sqLiteManage.getCategoryIds();
        System.out.println("ids = " + ids);

//        assertNotNull(ids);
    }

}