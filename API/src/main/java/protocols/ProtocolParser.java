package protocols;

import models.Category;
import models.Item;
import models.Order;
import sun.plugin2.message.Message;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ProtocolParser {


    private String formatMessageLine(String header, String value){
        return header + MessageProtocol.DELIMETER + value + "\n";
    }
    public String parseToString(Item item, String sender, String method){
        String msg = "";
        msg += formatMessageLine(MessageProtocol.Header.METHOD, method);
        msg += formatMessageLine(MessageProtocol.Header.SENDER, sender);
        msg += formatMessageLine(MessageProtocol.Header.TYPE, MessageProtocol.Type.ITEM);
        msg += formatMessageLine(MessageProtocol.Header.ID, item.getId() + "");
        msg += formatMessageLine(MessageProtocol.Header.NAME, item.getName());
        msg += formatMessageLine(MessageProtocol.Header.PRICE, item.getPrice() + "");
        msg += formatMessageLine(MessageProtocol.Header.CATE_ID, item.getCategoryId() + "");
        return msg;
    }
    public String parseToString(Category category, String sender, String method){
        String msg = "";
        msg += formatMessageLine(MessageProtocol.Header.METHOD, method);
        msg += formatMessageLine(MessageProtocol.Header.SENDER, sender);
        msg += formatMessageLine(MessageProtocol.Header.TYPE, MessageProtocol.Type.CATEGORY);
        msg += formatMessageLine(MessageProtocol.Header.ID, category.getName() + "");
        msg += formatMessageLine(MessageProtocol.Header.NAME, category.getName());
        return msg;
    }
    public String parseToString(Order order, String sender, String method){
        String msg = "";
        msg += formatMessageLine(MessageProtocol.Header.METHOD, method);
        msg += formatMessageLine(MessageProtocol.Header.SENDER, sender);
        msg += formatMessageLine(MessageProtocol.Header.TYPE, MessageProtocol.Type.ORDER);
        msg += formatMessageLine(MessageProtocol.Header.ID, order.getId() + "");
        msg += formatMessageLine(MessageProtocol.Header.AMOUNT, order.getAmount() + "");
        msg += formatMessageLine(MessageProtocol.Header.ITEM_ID, order.getItem().getId() + "");
        msg += formatMessageLine(MessageProtocol.Header.CATE_ID, order.getItem().getCategoryId() + "");
        msg += formatMessageLine(MessageProtocol.Header.TABLE, order.getTable() + "");
        return msg;
    }
    public String parseToString(List<Integer> ids, String sender, String method, String type){
        // TODO parse ids to string
        return null;
    }
    public String parseToString(String requestType){
        // TODO parse to request message
        return null;
    }
    public String parseToString(String requestType, int id){
        // TODO parse to request message with id
        return null;
    }
    public Item parseToItem(Map<String, String> map){
        int id = Integer.parseInt(map.get(MessageProtocol.Header.ID));
        String name = map.get(MessageProtocol.Header.NAME);
        double price = Double.parseDouble(map.get(MessageProtocol.Header.PRICE));
        int cateID = Integer.parseInt(map.get(MessageProtocol.Header.CATE_ID));

        return new Item(id, name, price, cateID);
    }
    public Category parseToCategory(Map<String, String> map){
        int id = Integer.parseInt(map.get(MessageProtocol.Header.ID));
        String name = map.get(MessageProtocol.Header.NAME);

        return new Category(id, name);
    }
    public Order parseToOrder(Map<String, String> map){
        int id = Integer.parseInt(map.get(MessageProtocol.Header.ID));
        int amt = Integer.parseInt(map.get(MessageProtocol.Header.AMOUNT));
        int table = Integer.parseInt(map.get(MessageProtocol.Header.TABLE));
        int itemId = Integer.parseInt(map.get(MessageProtocol.Header.ITEM_ID));
        int cateId = Integer.parseInt(map.get(MessageProtocol.Header.CATE_ID));
        Item tempItem = new Item(itemId, null, 0, cateId);
        return new Order(id, amt, tempItem, table);
    }
    public List<Integer> parseToIds(Map<String, String> map){
        // TODO parse to id list
        return null;
    }
    public Map<String,String> parseToMap(InputStream inputStream){
        Map<String, String> map = new HashMap<String, String>();
        BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));
        String line = null;
        try {
            while((line = reader.readLine()) != null){
                String[] e = line.split(MessageProtocol.DELIMETER);
                map.put(e[0], e[1]);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return map;
    }
}
