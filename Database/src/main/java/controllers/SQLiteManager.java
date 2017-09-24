package controllers;

import models.Category;
import models.Order;

import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.List;

public class SQLiteManager implements DatabaseManager{

    public List<Category> loadCategories(){

        return null;
    }

    public Order addOrder(Order order) {
        return null;
    }

    public boolean checkBill() {
        return false;
    }
}
