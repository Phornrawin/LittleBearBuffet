package controllers;

import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;
import com.google.firebase.auth.FirebaseCredentials;
import com.google.firebase.database.*;
import models.*;
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
    private Map<Integer, List<String>> packageItems;
    private Map<Integer,Item> itemMap;
    private Map<String, Order> orderMap;

    private List<FirebaseObserver> observers;
    private List<OnLoadCompleteListener> loadCompleteListeners;

    private boolean isPackagesAvailable = false;
    private boolean isCategoriesAvailable = false;
    private boolean isItemsAvailable = false;

    private Payment currentPayment = null;

    public FirebaseManager() {
        packagesBuffer = new ArrayList<>();
        itemsBuffer = new ArrayList<>();
        categoriesBuffer = new ArrayList<>();
        orderBuffer = new ArrayList<>();
        observers = new ArrayList<>();
        packageItems = new HashMap<>();
        itemMap = new HashMap<>();
        orderMap = new HashMap<>();
        loadCompleteListeners = new ArrayList<>();
    }

    public void setTable(int table){
        this.table = table;
    }

    public void start(){
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
        fetchItem();
        fetchCategories();
        initOrderListener();
    }

    private void initOrderListener() {
        DatabaseReference ref = FirebaseDatabase.getInstance().getReference("order");
        ref.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                System.out.println("onOrderAdd " + getClass().getName() + ".java:" + 80);
                String id = dataSnapshot.getKey();
                int amt = Integer.parseInt(dataSnapshot.child("amount").getValue().toString());


                int iID = Integer.parseInt(dataSnapshot.child("item").child("id").getValue().toString());
                Item item = itemMap.get(iID);

//                // parse to item
//                DataSnapshot itemRef = dataSnapshot.child("item");
//                int iID = Integer.parseInt(dataSnapshot.child("item").child("id").getValue().toString());
//                int iBalance = Integer.parseInt(itemRef.child("balance").getValue().toString());
//                int iCate = Integer.parseInt(itemRef.child("categoryId").getValue().toString());
//                String iName = itemRef.child("name").getValue().toString();
//                Item item = new Item(iID, iName, iCate, iBalance);

                // parse to payment
                DataSnapshot paymentRef = dataSnapshot.child("payment");
                boolean paid = Boolean.parseBoolean(paymentRef.child("paid").getValue().toString());
                int payAmt = Integer.parseInt(paymentRef.child("amt").getValue().toString());
                int table = Integer.parseInt(paymentRef.child("table").getValue().toString());
                String payId = paymentRef.child("id").getValue().toString();
                Package aPackage = packagesBuffer.get(Integer.parseInt(paymentRef.child("aPackage").child("id").getValue().toString()));
                Payment payment = new Payment(amt, aPackage, paid, table);
                payment.setId(payId);

                // parse to package
//                DataSnapshot packageRef = paymentRef.child("aPackage");
//                double price = Double.parseDouble(packageRef.child("price").getValue().toString());
//                int pacId = Integer.parseInt(packageRef.child("id").getValue().toString());
//                String pacName = packageRef.child("name").getValue().toString();
//                Package aPackage = new Package()
                Order order = new Order(id, amt, item, payment);
                order.setStatus(dataSnapshot.child("status").getValue().toString());

                System.out.println("order.getPayment() = " + order.getPayment());
                System.out.println("currentPayment = " + currentPayment);

                if (currentPayment != null && order.getPayment() != null && currentPayment.getId().equalsIgnoreCase(order.getPayment().getId())){
                    System.out.println("in table order come");
                    orderBuffer.add(order);
                    orderMap.put(id, order);
                    notifyObservers(observer -> observer.onOrderAdd(order));
                }
            }

            @Override
            public void onChildChanged(DataSnapshot dataSnapshot, String s) {
                String id = dataSnapshot.getKey();
                String status = dataSnapshot.child("status").getValue().toString();
                orderMap.get(id).setStatus(status);
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
        System.out.println("category ref = " + ref);
        ref.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for(DataSnapshot data : dataSnapshot.getChildren()){
                    int id = Integer.parseInt(data.getKey());
                    String name = data.child("name").getValue().toString();
                    Category category = new Category(id, name);
                    categoriesBuffer.add(category);
                }
                System.out.println("FirebaseManager: client receive category");
                isCategoriesAvailable = true;
                checkLoadStatus();
//                fetchItem();
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }
    private void fetchItem(){
        DatabaseReference ref = FirebaseDatabase.getInstance().getReference("item");
        System.out.println("item ref = " + ref);
        ref.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                System.out.println("FirebaseManager: client receive item");

                for(DataSnapshot data : dataSnapshot.getChildren()){
                    int id = Integer.parseInt(data.getKey());
                    String name = data.child("name").getValue().toString();
                    int balance = Integer.parseInt(data.child("balance").getValue().toString());
                    int cateId = Integer.parseInt(data.child("cate").getValue().toString());

                    Item item = new Item(id, name, cateId, balance);
                    itemsBuffer.add(item);
                    itemMap.put(item.getId(), item);
                }
                System.out.println("FirebaseManager: client receive item");
                isItemsAvailable = true;
                checkLoadStatus();
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
                System.out.println("FirebaseManager, client receive package");
                isPackagesAvailable = true;
                checkLoadStatus();
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
    public void addOrder(Order order) {
        DatabaseReference ref = FirebaseDatabase.getInstance().getReference("order").push();
//        Map<String, String> map = new HashMap<>();
//        map.put("amt", order.getAmount() + "");
//        map.put("item", order.getItem().getId() + "");
//        map.put("table", order.getTable() + "");
//        map.put("status", order.getStatus());
        order = new Order(ref.getKey(), order.getAmount(), order.getItem(), currentPayment);
        ref.setValue(order);
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
        List<String> ids = packageItems.get(pk.getId());
        List<Item> items = new ArrayList<>();
        for(Item item : itemsBuffer)
            if (ids.contains(item.getId() + ""))
                items.add(item);
        System.out.println("itemsBuffer = " + itemsBuffer);
        System.out.println("items = " + items);
        return items;
    }

    @Override
    public void regisObserver(FirebaseObserver observer) {
        observers.add(observer);
    }

    @Override
    public void removeObserver(FirebaseObserver observer) {
        observers.remove(observer);
    }

    public void selectPackage(Package pk, int amt){
        DatabaseReference ref = FirebaseDatabase.getInstance().getReference("payment");
        DatabaseReference pushRef = ref.push();
        String key = pushRef.getKey();
        Payment payment = new Payment(amt, pk, false, table);
        payment.setId(key);
        pushRef.setValue(payment);
        this.currentPayment = payment;
        System.out.println("payment = " + payment);
    }

    private void notifyObservers(NotifyObserverCallback callback){
        for (FirebaseObserver observer : observers){
            callback.notifyObserver(observer);
        }
    }

    private interface NotifyObserverCallback {
        void notifyObserver(FirebaseObserver observer);
    }

    private void checkLoadStatus(){
        if (isPackagesAvailable && isItemsAvailable && isCategoriesAvailable) {
            System.out.println("Load Complete " + loadCompleteListeners.size());
            for (OnLoadCompleteListener listener : loadCompleteListeners)
                listener.onLoadComplete();
        }
    }

    public void addOnLoadCompleteListener(OnLoadCompleteListener listener){
        loadCompleteListeners.add(listener);
    }
    public void removeOnLoadCompleteListener(OnLoadCompleteListener listener){
        loadCompleteListeners.remove(listener);
    }



}
