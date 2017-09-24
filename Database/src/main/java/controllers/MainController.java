package controllers;

import models.Order;
import protocols.MessageProtocol;
import protocols.ProtocolParser;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Map;

public class MainController implements  CoreController {
    DatabaseManager dbManager;
    ProtocolParser parser = new ProtocolParser();
    public final String SENDER = "server";

    public void start() {
        try {
            String clientMessage;
            String capitalizedSentence;
            ServerSocket serverSocket = new ServerSocket(6789);
            System.out.println("Start server...");
            while (true) {
                Socket connectionSocket = serverSocket.accept();
//                BufferedReader inFromClient = new BufferedReader(new InputStreamReader(connectionSocket.getInputStream()));
                InputStream inFromClient = connectionSocket.getInputStream();
                DataOutputStream outToClient = new DataOutputStream(connectionSocket.getOutputStream());

                Map<String, String> map = parser.parseToMap(inFromClient);
                if(MessageProtocol.Method.ADD.equals(map.get(MessageProtocol.Header.METHOD))){
                    if (MessageProtocol.Type.ORDER.equals(map.get(MessageProtocol.Header.TYPE))){
                        Order order = parser.parseToOrder(map);
                        Order newOrder = dbManager.addOrder(order);
                        if(newOrder != null){
                            String replyMsg = parser.parseToString(newOrder, SENDER, MessageProtocol.Method.REPLY);
                            outToClient.writeBytes(replyMsg);
                        }else{
                            // TODO reply error can't add order
                        }
                    }else{
                        // TODO reply bad msg
                    }
                }else if(MessageProtocol.Method.LOAD.equals(map.get(MessageProtocol.Header.METHOD))){

                } else {
                    // TODO reply bad msg
                }

                outToClient.close();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

//    private

    public void setDatabaseManager(DatabaseManager dbManager) {
        this.dbManager = dbManager;
    }
}
