package controllers;

import models.Category;
import models.Order;
import protocols.ProtocolParser;

import java.util.ArrayList;

/**
 * Created by PC301 on 25/9/2560.
 */
public class SocketManager implements DatabaseManager {

    private ProtocolParser parser;

    public ArrayList<Category> loadCategories() {
        return null;
    }

    public Order addOrder(Order order) {
        return null;
    }

    public boolean cancleOrder(Order order) {
        return false;
    }

    public boolean cancleOrder(int orderId) {
        return false;
    }

    public boolean checkBill() {
        return false;
    }
}
