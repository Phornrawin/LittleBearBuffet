package protocols;

import models.Category;
import models.Item;
import models.Order;
import models.Package;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ProtocolParser {


    private String formatMessageLine(String header, String value){
        return header + MessageProtocol.DELIMITER + value + "\n";
    }
    private String endMessage(){
        return formatMessageLine(MessageProtocol.Header.END, MessageProtocol.Header.END) + "\n";
    }
    public String parseToString(Item item, String sender, String method){
        String msg = "";
        msg += formatMessageLine(MessageProtocol.Header.METHOD, method);
        msg += formatMessageLine(MessageProtocol.Header.SENDER, sender);
        msg += formatMessageLine(MessageProtocol.Header.TYPE, MessageProtocol.Type.ITEM);
        msg += formatMessageLine(MessageProtocol.Header.ID, item.getId() + "");
        msg += formatMessageLine(MessageProtocol.Header.NAME, item.getName());
        msg += formatMessageLine(MessageProtocol.Header.CATE_ID, item.getCategoryId() + "");
        msg += endMessage();
        return msg;
    }
    public String parseToString(Category category, String sender, String method){
        String msg = "";
        msg += formatMessageLine(MessageProtocol.Header.METHOD, method);
        msg += formatMessageLine(MessageProtocol.Header.SENDER, sender);
        msg += formatMessageLine(MessageProtocol.Header.TYPE, MessageProtocol.Type.CATEGORY);
        msg += formatMessageLine(MessageProtocol.Header.ID, category.getId() + "");
        msg += formatMessageLine(MessageProtocol.Header.NAME, category.getName());
        msg += endMessage();
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
        msg += endMessage();
        return msg;
    }
    public String parseToString(List<Integer> ids, String sender, String method, String type){
        String msg = "";
        msg += formatMessageLine(MessageProtocol.Header.METHOD, method);
        msg += formatMessageLine(MessageProtocol.Header.SENDER, sender);
        msg += formatMessageLine(MessageProtocol.Header.TYPE, type);
        msg += formatMessageLine(MessageProtocol.Header.IDS, convertIdsToString(ids));
        msg += endMessage();
        return msg;
    }
    private String convertIdsToString(List<Integer> ids){
        String s = "";
        for(int i=0; i<ids.size()-1; i++){
            s += ids.get(i) + ",";
        }
        s += ids.get(ids.size()-1);
        return s;
    }
    public String parseToString(Package packageObj, String sender, String method){
        String msg = "";
        msg += formatMessageLine(MessageProtocol.Header.METHOD, method);
        msg += formatMessageLine(MessageProtocol.Header.SENDER, sender);
        msg += formatMessageLine(MessageProtocol.Header.ID, packageObj.getId() + "");
        msg += formatMessageLine(MessageProtocol.Header.NAME, packageObj.getName());
        msg += formatMessageLine(MessageProtocol.Header.PRICE, packageObj.getPrice() + "");
        msg += formatMessageLine(MessageProtocol.Header.TYPE, MessageProtocol.Type.PACKAGE);
        msg += endMessage();
        return msg;
    }
    public String parseToString(String requestType, String sender){
        String msg = "";
        msg += formatMessageLine(MessageProtocol.Header.METHOD, MessageProtocol.Method.LOAD);
        msg += formatMessageLine(MessageProtocol.Header.SENDER, sender);
        msg += formatMessageLine(MessageProtocol.Header.TYPE, requestType);
        msg += endMessage();
        return msg;
    }
    public String parseToString(String requestType, String sender, int id){
        String msg = "";
        msg += formatMessageLine(MessageProtocol.Header.METHOD, MessageProtocol.Method.LOAD);
        msg += formatMessageLine(MessageProtocol.Header.SENDER, sender);
        msg += formatMessageLine(MessageProtocol.Header.TYPE, requestType);
        msg += formatMessageLine(MessageProtocol.Header.ID, id + "");
        msg += endMessage();
        return msg;
    }

    public Item parseToItem(Map<String, String> map){
        int id = Integer.parseInt(map.get(MessageProtocol.Header.ID));
        String name = map.get(MessageProtocol.Header.NAME);
        int cateID = Integer.parseInt(map.get(MessageProtocol.Header.CATE_ID));

        return new Item(id, name, cateID);
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
        Item tempItem = new Item(itemId, null, cateId);
        return new Order(id, amt, tempItem, table);
    }
    public List<Integer> parseToIds(Map<String, String> map){
        String[] sids = map.get(MessageProtocol.Header.IDS).split(",");
        List<Integer> ids = new ArrayList<Integer>();
        for(String sid : sids)
            ids.add(Integer.parseInt(sid));
        return ids;
    }
    public Package parseToPackage(Map<String, String> map){
        int id = Integer.parseInt(map.get(MessageProtocol.Header.ID));
        String name = map.get(MessageProtocol.Header.NAME);
        double price = Double.parseDouble(map.get(MessageProtocol.Header.PRICE));

        return new Package(id, name, price);
    }
//    public Map<String,String> parseToMap(InputStream inputStream){
//        Map<String, String> map = new HashMap<String, String>();
//        BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));
//        String line = null;
//        try {
//            while((line = reader.readLine()) != null ){
//                String[] e = line.split(MessageProtocol.DELIMITER);
//                map.put(e[0], e[1]);
//            }
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//
//        return map;
//    }

    public Map<String, String> parseToMap(BufferedReader reader){
        Map<String, String> map = new HashMap<String, String>();
        String line = null;
        try {
            while((line = reader.readLine()) != null){
                System.out.println(line);
                String[] e = line.split(MessageProtocol.DELIMITER);
                if(MessageProtocol.Header.END.equals(e[0])){
                    break;
                }
                map.put(e[0], e[1]);
            }
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("parse to map err");
        }
        return map;
    }
}
