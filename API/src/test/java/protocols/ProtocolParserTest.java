package protocols;

import models.Category;
import models.Item;
import models.Order;
import models.Package;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.*;
import java.net.Socket;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;
import static protocols.MessageProtocol.Header.SENDER;

class ProtocolParserTest {
    ProtocolParser protocol;

    ProtocolParserTest() throws IOException {
    }

    @BeforeEach
    void setUp() {
        protocol = new ProtocolParser();
    }

    /** Item request
     * parseToString(Item item, String sender, String method)*/
    @Test
    void parseToString() {
        Item corn = new Item(2,"Baby-Corn",5);
        String cornString = protocol.parseToString(corn,"customer-1","load");
        assertEquals(cornString,"method:load\n" +
                "sender:customer-1\n" +
                "type:item\n" +
                "id:2\n" +
                "name:Baby-Corn\n" +
                "category-id:5\n" +
                "end:end\n\n");
    }

    /** Category request
     * parseToString(Category category, String sender, String method)*/
    @Test
    void parseToString1() {
        Category veget = new Category(5, "Vegetable");
        String vegetString = protocol.parseToString(veget, "customer-1","load");
        assertEquals(vegetString,"method:load\n" +
                "sender:customer-1\n" +
                "type:category\n" +
                "id:5\n" +
                "name:Vegetable\n" +
                "end:end\n\n");
    }

    /** Order request
     * parseToString(Order order, String sender, String method)*/
    @Test
    void parseToString2() {
        Item corn = new Item(2,"Baby-Corn",5);
        Order order = new Order("1",3,corn,5);
        String orderString = protocol.parseToString(order,"customer-1","add");
        assertEquals(orderString,"method:add\n" +
                "sender:customer-1\n" +
                "type:order\n" +
                "id:1\n" +
                "amount:3\n" +
                "item-id:2\n" +
                "category-id:5\n" +
                "table:5\n" +
                "end:end\n\n");
    }
    /** ArrayList ids request
     * parseToString(List<Integer> ids, String sender, String method, String type)*/
    @Test
    void parseToString3() {
        List<Integer> ids = new ArrayList<Integer>();
        ids.add(1);
        ids.add(2);
        String idsString = protocol.parseToString(ids,"server-1","load","5");
        assertEquals(idsString,"method:load\n" +
                "sender:server-1\n" +
                "type:5\n" +
                "ids:1,2\n" +
                "end:end\n\n");
    }

    /** Package request
     * parseToString(Package packageObj, String sender, String method)*/
    @Test
    void parseToString4() {
        Package pandaSet = new Package(1,"Panda Set",299.0);
        String pandaSetString = protocol.parseToString(pandaSet,"customer-1", "load");
        assertEquals(pandaSetString, "method:load\n" +
                "sender:customer-1\n" +
                "id:1\n" +
                "name:Panda Set\n" +
                "price:299.0\n" +
                "type:package\n" +
                "end:end\n\n");
    }

    /** Request Type
     * parseToString(String requestType, String sender)*/
    @Test
    void parseToString5() {
        assertEquals(protocol.parseToString("1", "customer-1"), "method:load\n" +
                "sender:customer-1\n" +
                "type:1\n" +
                "end:end\n\n");
    }
    /** Request Type
     * parseToString(String requestType, String sender, int id)*/
    @Test
    void parseToString6() {
        assertEquals(protocol.parseToString("5","customer-1",2),"method:load\n" +
                "sender:customer-1\n" +
                "type:5\n" +
                "id:2\n" +
                "end:end\n\n");
    }
    /**
     * public Item parseToItem(Map<String, String> map)*/
    @Test
    void parseToItem() {
        Map<String,String> cornMap = new HashMap<String, String>();
        cornMap.put("id","2");
        cornMap.put("name","Baby-Corn");
        cornMap.put("category-id","5");
        Item corn = protocol.parseToItem(cornMap);
        assertEquals(corn.getId(),2);
        assertEquals(corn.getName(),"Baby-Corn");
        assertEquals(corn.getCategoryId(),5);
    }
    /**
     * Category parseToCategory(Map<String, String> map)*/
    @Test
    void parseToCategory() {
        Map<String,String> vetgetMap = new HashMap<String, String>();
        vetgetMap.put("id","5");
        vetgetMap.put("name", "Vetgetable");
        Category vetget = protocol.parseToCategory(vetgetMap);
        assertEquals(vetget.getId(),5);
        assertEquals(vetget.getName(), "Vetgetable");
    }
    /**
     * Order parseToOrder(Map<String, String> map)*/
    @Test
    void parseToOrder() {
        Map<String,String> cornMap = new HashMap<String, String>();
        cornMap.put("id","2");
        cornMap.put("amount","2");
        cornMap.put("table", "9");
        cornMap.put("item-id","2");
        cornMap.put("category-id","5");
        Order corn = protocol.parseToOrder(cornMap);
        assertEquals(corn.getId(),"2");
        assertEquals(corn.getAmount(),2);
        assertEquals(corn.getTable(),9);

        assertEquals(corn.getItem().getId(),2);
        assertEquals(corn.getItem().getName(),null);
        assertEquals(corn.getItem().getCategoryId(),5);
    }

    /**
     * List<Integer> parseToIds(Map<String, String> map)*/
    @Test
    void parseToIds() {
        Map<String,String> vetgetMap = new HashMap<String, String>();
        vetgetMap.put("ids","2,4");
        Integer[] vetgetID  = new Integer[2];
        vetgetID[0] = 2;
        vetgetID[1] = 4;
        Integer[] vetgetParse = new Integer[protocol.parseToIds(vetgetMap).size()];
        vetgetParse = protocol.parseToIds(vetgetMap).toArray(vetgetParse);
        assertArrayEquals(vetgetParse, vetgetID);
    }

    /**
     * parseToPackage(Map<String, String> map)*/
    @Test
    void parseToPackage() {
        Map<String,String> packageMap = new HashMap<String, String>();
        packageMap.put("id","1");
        packageMap.put("name","Panda Set");
        packageMap.put("price","299.0");
        Package panda = protocol.parseToPackage(packageMap);
        assertEquals(panda.getId(),1);
        assertEquals(panda.getName(),"Panda Set");
        assertEquals(panda.getPrice(),299.0);
    }

}