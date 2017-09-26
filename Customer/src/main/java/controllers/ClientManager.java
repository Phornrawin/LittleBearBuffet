package controllers;

import models.Category;
import models.Item;
import models.Order;
import models.Package;
import protocols.MessageProtocol;
import protocols.ProtocolParser;

import java.io.*;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Created by PC301 on 25/9/2560.
 */
public class ClientManager implements DatabaseManager {

    private ProtocolParser parser;
    private final int PORT_NUMBER = 6789;
    private final String SERVER = "localhost";
    private final String SENDER = "customer";

    public ClientManager() {
        parser = new ProtocolParser();
    }

    public List<Category> loadCategories() {
        System.out.println("loadCategories");
        ArrayList<Category> categories = new ArrayList<Category>();
        List<Integer> categoryIds = loadCategoryIds();
        if(categoryIds != null){
            for(int id : categoryIds){
                Category category = loadCategoryInfo(id);
                if(category != null)
                    categories.add(category);
            }
        }

//        List<Integer> itemIds = loadItemIds();
//        if (itemIds != null){
//            for(int id : itemIds){
//                Item item = loadItemInfo(id);
//                if (item != null)
//                    for(Category category: categories){
//                        if(category.getId() == item.getCategoryId())
//                            category.addItem(item);
//                    }
//            }
//        }


        return categories;
    }

    private List<Integer> loadCategoryIds(){
        Socket clientSocket = null;
        try {
            clientSocket = new Socket(SERVER, PORT_NUMBER);
            DataOutputStream outToServer = new DataOutputStream(clientSocket.getOutputStream());
            InputStream inFormServer = clientSocket.getInputStream();

            String msg = parser.parseToString(MessageProtocol.Type.CATEGORY_ID, SENDER);
            System.out.println("msg to server " + msg);
            outToServer.writeBytes(msg);
            BufferedReader reader = new BufferedReader(new InputStreamReader(inFormServer));
            Map<String, String> map = parser.parseToMap(reader);
            if(MessageProtocol.Type.CATEGORY_ID.equals(map.get(MessageProtocol.Header.TYPE))){
                List<Integer> ids = parser.parseToIds(map);
                if (ids != null) {
                    return ids;
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if(clientSocket != null)
                try {
                    clientSocket.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
        }

        return null;
    }
    private Category loadCategoryInfo(int id){
        Socket clientSocket = null;
        try {
            clientSocket = new Socket(SERVER, PORT_NUMBER);
            DataOutputStream outToServer = new DataOutputStream(clientSocket.getOutputStream());
            InputStream inFormServer = clientSocket.getInputStream();

            String msg = parser.parseToString(MessageProtocol.Type.CATEGORY, SENDER, id);
            outToServer.writeBytes(msg);
            BufferedReader reader = new BufferedReader(new InputStreamReader(inFormServer));
            Map<String, String> map = parser.parseToMap(reader);
            if(MessageProtocol.Type.CATEGORY.equals(map.get(MessageProtocol.Header.TYPE))){
                Category category = parser.parseToCategory(map);
                if (category != null)
                    return category;
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if(clientSocket != null)
                try {
                    clientSocket.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
        }
        return null;
    }
    private List<Integer> loadItemIds(){
        Socket clientSocket = null;
        try {
            clientSocket = new Socket(SERVER, PORT_NUMBER);
            DataOutputStream outToServer = new DataOutputStream(clientSocket.getOutputStream());
            InputStream inFormServer = clientSocket.getInputStream();

            String msg = parser.parseToString(MessageProtocol.Type.ITEM_ID, SENDER);
            outToServer.writeBytes(msg);
            BufferedReader reader = new BufferedReader(new InputStreamReader(inFormServer));
            Map<String, String> map = parser.parseToMap(reader);
            if(MessageProtocol.Type.ITEM_ID.equals(map.get(MessageProtocol.Header.TYPE))){
                List<Integer> ids = parser.parseToIds(map);
                if (ids != null)
                    return ids;
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if(clientSocket != null)
                try {
                    clientSocket.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
        }

        return null;
    }
    private Item loadItemInfo(int id){
        Socket clientSocket = null;
        try {
            clientSocket = new Socket(SERVER, PORT_NUMBER);
            DataOutputStream outToServer = new DataOutputStream(clientSocket.getOutputStream());
            InputStream inFormServer = clientSocket.getInputStream();

            String msg = parser.parseToString(MessageProtocol.Type.ITEM, SENDER, id);
            outToServer.writeBytes(msg);
            BufferedReader reader = new BufferedReader(new InputStreamReader(inFormServer));
            Map<String, String> map = parser.parseToMap(reader);
            if(MessageProtocol.Type.ITEM.equals(map.get(MessageProtocol.Header.TYPE))){
                Item item = parser.parseToItem(map);
                if (item != null)
                    return item;
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if(clientSocket != null)
                try {
                    clientSocket.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
        }
        return null;
    }

    public Order addOrder(Order order) {
        return null;
    }

    public boolean checkBill(Package pk, int amount) {
        return false;
    }

    public boolean cancleOrder(Order order) {
        return false;
    }

    public boolean cancleOrder(int orderId) {
        return false;
    }

    public void setUrl(String url) {

    }

    public List<Package> loadPackages() {
        return null;
    }

    public List<Item> loadItems(Package pk) {
        return null;
    }

}
