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
    private String url = "localhost";
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
        return categories;
    }

    private List<Integer> loadCategoryIds(){
        Socket clientSocket = null;
        try {
            clientSocket = new Socket(url, PORT_NUMBER);
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
            clientSocket = new Socket(url, PORT_NUMBER);
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
            clientSocket = new Socket(url, PORT_NUMBER);
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

    public Order addOrder(Order order) {
        Socket clientSocket = null;
        try {
            clientSocket = new Socket(url, PORT_NUMBER);
            DataOutputStream outToServer = new DataOutputStream(clientSocket.getOutputStream());
            InputStream inFormServer = clientSocket.getInputStream();

            String msg = parser.parseToString(order, SENDER, MessageProtocol.Method.ADD);
            outToServer.writeBytes(msg);
            BufferedReader reader = new BufferedReader(new InputStreamReader(inFormServer));
            Map<String, String> map = parser.parseToMap(reader);

            if(MessageProtocol.Type.ORDER.equals(map.get(MessageProtocol.Header.TYPE))){
                return parser.parseToOrder(map);
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
        this.url = url;
    }

    public List<Package> loadPackages() {
        List<Integer> ids = loadPackageIds();
        List<Package> packages = new ArrayList<Package>();
        System.out.println("ids = " + ids);
        if(ids != null){
            for(int id : ids){
                Package packageObj = loadPackage(id);
                if(packageObj != null)
                    packages.add(packageObj);
            }
        }
        return packages;
    }

    private Package loadPackage(int id) {
        Socket clientSocket = null;
        try {
            clientSocket = new Socket(url, PORT_NUMBER);
            DataOutputStream outToServer = new DataOutputStream(clientSocket.getOutputStream());
            InputStream inFormServer = clientSocket.getInputStream();

            String msg = parser.parseToString(MessageProtocol.Type.PACKAGE, SENDER, id);
            outToServer.writeBytes(msg);
            BufferedReader reader = new BufferedReader(new InputStreamReader(inFormServer));
            Map<String, String> map = parser.parseToMap(reader);

            if(MessageProtocol.Type.PACKAGE.equals(map.get(MessageProtocol.Header.TYPE))){
                return parser.parseToPackage(map);
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

    private List<Integer> loadPackageIds(){
        Socket clientSocket = null;
        try {
            clientSocket = new Socket(url, PORT_NUMBER);
            DataOutputStream outToServer = new DataOutputStream(clientSocket.getOutputStream());
            InputStream inFormServer = clientSocket.getInputStream();

            String msg = parser.parseToString(MessageProtocol.Type.PACKAGE_ID, SENDER);
            outToServer.writeBytes(msg);
            BufferedReader reader = new BufferedReader(new InputStreamReader(inFormServer));
            Map<String, String> map = parser.parseToMap(reader);

            if(MessageProtocol.Type.PACKAGE_ID.equals(map.get(MessageProtocol.Header.TYPE))){
                return parser.parseToIds(map);

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

    public List<Item> loadItems(Package pk) {
        int packageId = pk.getId();
        List<Integer> ids = loadItemIds(packageId);
        if (ids != null){
            List<Item> items = new ArrayList<Item>();
            for(int id : ids){
                Item item = loadItem(id);
                if (item != null)
                    items.add(item);
            }

            return items;
        }
        return null;
    }

    private List<Integer> loadItemIds(int packageId){
        Socket clientSocket = null;
        try {
            clientSocket = new Socket(url, PORT_NUMBER);
            DataOutputStream outToServer = new DataOutputStream(clientSocket.getOutputStream());
            InputStream inFormServer = clientSocket.getInputStream();

            String msg = parser.parseToString(MessageProtocol.Type.ITEM_ID, SENDER, packageId);
            outToServer.writeBytes(msg);
            BufferedReader reader = new BufferedReader(new InputStreamReader(inFormServer));
            Map<String, String> map = parser.parseToMap(reader);

            if(MessageProtocol.Type.ITEM_ID.equals(map.get(MessageProtocol.Header.TYPE))){
                return parser.parseToIds(map);
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

    private Item loadItem(int id){
        Socket clientSocket = null;
        try {
            clientSocket = new Socket(url, PORT_NUMBER);
            DataOutputStream outToServer = new DataOutputStream(clientSocket.getOutputStream());
            InputStream inFormServer = clientSocket.getInputStream();

            String msg = parser.parseToString(MessageProtocol.Type.ITEM, SENDER, id);
            outToServer.writeBytes(msg);
            BufferedReader reader = new BufferedReader(new InputStreamReader(inFormServer));
            Map<String, String> map = parser.parseToMap(reader);

            if(MessageProtocol.Type.ITEM.equals(map.get(MessageProtocol.Header.TYPE))){
                return parser.parseToItem(map);
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

    

}
