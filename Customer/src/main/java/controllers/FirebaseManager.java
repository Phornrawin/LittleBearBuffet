package controllers;

import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;
import com.google.firebase.auth.FirebaseCredentials;
import com.google.firebase.database.*;
import models.Category;
import models.Item;
import models.Order;
import models.Package;

import java.io.FileInputStream;
import java.io.IOException;
import java.lang.reflect.Array;
import java.util.*;

public class FirebaseManager implements DatabaseManager, FirebaseObserable {
    private String url = "https://littlebearbuffet.firebaseio.com/";
    private int table;
    private List<Package> packagesBuffer;
    private List<Item> itemsBuffer;
    private List<Category> categoriesBuffer;
    private List<Order> orderBuffer;
    private List<FirebaseObserver> observers;
    private Map<Integer, List<String>> packageItems;
    private Map<Integer,Item> itemMap;

    public void setTable(int table){
        this.table = table;
    }

    public void start(){
        packagesBuffer = new ArrayList<>();
        itemsBuffer = new ArrayList<>();
        categoriesBuffer = new ArrayList<>();
        orderBuffer = new ArrayList<>();
        observers = new ArrayList<>();
        packageItems = new HashMap<>();
        itemMap = new HashMap<>();
        try {
            FileInputStream serviceAccount = new FileInputStream("serviceAccountKey.json");

            FirebaseOptions options = new FirebaseOptions.Builder()
                    .setCredential(FirebaseCredentials.fromCertificate(serviceAccount))
                    .setDatabaseUrl(url)
                    .build();

            FirebaseApp.initializeApp(options);

            fetchData();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void fetchData(){
        fetchPackages();
        fetchCategories();
        initOrderListener();
    }

    private void initOrderListener() {
        DatabaseReference ref = FirebaseDatabase.getInstance().getReference("order");
        ref.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                String id = dataSnapshot.getKey();
                int amt = Integer.parseInt(dataSnapshot.child("amt").getValue().toString());
                int table = Integer.parseInt(dataSnapshot.child("table").getValue().toString());
                Item item = itemMap.get(Integer.parseInt(dataSnapshot.child("item").getValue().toString()));
                Order order = new Order(id, amt, item, table);
                orderBuffer.add(order);
            }

            @Override
            public void onChildChanged(DataSnapshot dataSnapshot, String s) {
                String id = dataSnapshot.getKey();

            }

            @Override
            public void onChildRemoved(DataSnapshot dataSnapshot) {

            }

            @Override
            public void onChildMoved(DataSnapshot dataSnapshot, String s) {

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }

    private void fetchCategories(){
        DatabaseReference ref = FirebaseDatabase.getInstance().getReference("category");
        ref.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for(DataSnapshot data : dataSnapshot.getChildren()){
                    int id = Integer.parseInt(data.getKey());
                    String name = data.child("name").getValue().toString();
                    Category category = new Category(id, name);
                    categoriesBuffer.add(category);
                }
                fetchItem();
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }
    private void fetchItem(){
        DatabaseReference ref = FirebaseDatabase.getInstance().getReference("item");
        ref.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for(DataSnapshot data : dataSnapshot.getChildren()){
                    int id = Integer.parseInt(data.getKey());
                    String name = data.child("name").getValue().toString();
                    int balance = Integer.parseInt(data.child("balance").getValue().toString());
                    int cateId = Integer.parseInt(data.child("cate_id").getValue().toString());

                    Item item = new Item(id, name, cateId, balance);
                    itemsBuffer.add(item);
                    itemMap.put(id, item);
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }
    private void fetchPackages(){
        DatabaseReference ref = FirebaseDatabase.getInstance().getReference("package");
        ref.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for (DataSnapshot data : dataSnapshot.getChildren()){
                    int id = Integer.parseInt(data.getKey());
                    String name = data.child("name").getValue().toString();
                    double price = Double.parseDouble(data.child("price").getValue().toString());
                    Package packageObj = new Package(id, name, price);
                    packagesBuffer.add(packageObj);

                    String[] items = data.child("items").getValue().toString().split(",");
                    packageItems.put(id, Arrays.asList(items));
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }

    @Override
    public List<Category> loadCategories() {
        return categoriesBuffer;
    }

    @Override
    public Order addOrder(Order order) {
        DatabaseReference ref = FirebaseDatabase.getInstance().getReference("order");
        Map<String, Integer> map = new HashMap<>();
        map.put("amt", order.getAmount());
        map.put("item", order.getItem().getId());
        map.put("table", order.getTable());
        ref.push().setValue(map);
        return null;
    }

    @Override
    public boolean checkBill(Package pk, int amount) {
        return false;
    }

    @Override
    public void setUrl(String url) {
        this.url = url;
    }

    @Override
    public List<Package> loadPackages() {
        return packagesBuffer;
    }

    @Override
    public List<Item> loadItems(Package pk) {
        return itemsBuffer;
    }

    @Override
    public void regisObserver(FirebaseObserver observer) {
        observers.add(observer);

    }

    @Override
    public void removeObserver(FirebaseObserver observer) {
        observers.remove(observer);
    }

    private void notifyObservers(NotifyObserverCallback callback){
        for (FirebaseObserver observer : observers){
            callback.notifyObserver(observer);
        }
    }

    private interface NotifyObserverCallback {
        void notifyObserver(FirebaseObserver observer);
    }
}
