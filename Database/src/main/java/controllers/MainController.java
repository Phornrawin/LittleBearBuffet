package controllers;

import models.Category;
import models.Order;
import protocols.MessageProtocol;
import protocols.ProtocolParser;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MainController implements  CoreController {
    DatabaseManager dbManager;
    ProtocolParser parser = new ProtocolParser();
    public final String SENDER = "server";

    public void start() {
        dbManager = new SQLiteManager();
        try {
            String clientMessage;
            String capitalizedSentence;
            ServerSocket serverSocket = new ServerSocket(6789);
            System.out.println("Start server...");
            while (true) {
                Socket connectionSocket = serverSocket.accept();
                InputStream inFromClient = connectionSocket.getInputStream();
                DataOutputStream outToClient = new DataOutputStream(connectionSocket.getOutputStream());

                BufferedReader reader = new BufferedReader(new InputStreamReader(inFromClient));
//                Map<String, String> map = parser.parseToMap(inFromClient);
                System.out.println("calling parse to map");
                Map<String, String> map = parser.parseToMap(reader);
//                Map<String, String> map = new HashMap<String, String>();
                System.out.println("map = " + map);
                if(MessageProtocol.Method.ADD.equals(map.get(MessageProtocol.Header.METHOD))){
                    if (MessageProtocol.Type.ORDER.equals(map.get(MessageProtocol.Header.TYPE))){
                        addOrder(map, outToClient);
                    }else{
                        // TODO reply bad msg
                    }
                }else if(MessageProtocol.Method.LOAD.equals(map.get(MessageProtocol.Header.METHOD))){
                    if (MessageProtocol.Type.CATEGORY_ID.equals(map.get(MessageProtocol.Header.TYPE))){
                        System.out.println("load category id");
                        replyCategoryID(map, outToClient);
                    }else if (MessageProtocol.Type.CATEGORY.equals(map.get(MessageProtocol.Header.TYPE))){
                        // TODO reply category
                    }else if (MessageProtocol.Type.ITEM_ID.equals(map.get(MessageProtocol.Header.TYPE))){
                        // TODO reply item id
                    }else if (MessageProtocol.Type.ITEM.equals(map.get(MessageProtocol.Header.TYPE))){
                        // TODO reply item
                    } else {
                        // TODO reply bad msg
                    }

                } else {
                    replyBadMessage(outToClient);
                }
                outToClient.close();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void addOrder(Map<String, String> map, DataOutputStream out) throws IOException {
        Order order = parser.parseToOrder(map);
        Order newOrder = dbManager.addOrder(order);
        if(newOrder != null){
            String replyMsg = parser.parseToString(newOrder, SENDER, MessageProtocol.Method.REPLY);
            out.writeBytes(replyMsg);
        }else{
            // TODO reply error can't add order
        }
    }

    private void replyCategoryID(Map<String, String> map, DataOutputStream out) throws IOException {
        List<Integer> ids = dbManager.getCategoryIds();
        System.out.println("category id " + ids);
        if(ids != null){
            String replyMsg = parser.parseToString(ids, SENDER, MessageProtocol.Method.REPLY, MessageProtocol.Type.CATEGORY_ID);
            out.writeBytes(replyMsg);
        }else {
            // TODO reply error can't load category ids
        }
    }

    private void replyCategory(Map<String, String> map, DataOutputStream out){
        try{
            Category category = dbManager.getCategory(Integer.parseInt(map.get(MessageProtocol.Header.ID)));
            if(category != null){
                String replyMsg = parser.parseToString(category, SENDER, MessageProtocol.Method.REPLY);
                out.writeBytes(replyMsg);
            }else {
                // TODO reply error can't load category
            }
        }catch (Exception e){
            // parse error
            replyBadMessage(out);
        }
    }

    private void replyBadMessage(DataOutputStream out){
        // TODO reply bag msg
    }
    public void setDatabaseManager(DatabaseManager dbManager) {
        this.dbManager = dbManager;
    }


}
