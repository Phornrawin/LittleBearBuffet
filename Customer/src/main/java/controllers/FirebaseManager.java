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
    private String url = "";
    private int table;
    private List<Package> packagesBuffer;
    private List<Item> itemsBuffer;
    private List<Category> categoriesBuffer;
    private List<FirebaseObserver> observers;
    private Map<Integer, List<String>> packageItems;

    public FirebaseManager() {
        url = "https://littlebearbuffet.firebaseio.com/";
    }

    public void setTable(int table){
        this.table = table;
    }

    public void start(){
        packagesBuffer = new ArrayList<>();
        itemsBuffer = new ArrayList<>();
        categoriesBuffer = new ArrayList<>();
        observers = new ArrayList<>();
        packageItems = new HashMap<>();
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
    }
    private void fetchCategories(){
        DatabaseReference ref = FirebaseDatabase.getInstance().getReference("category");
        ref.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

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
    private void initCategoryListener() {
        DatabaseReference ref = FirebaseDatabase.getInstance().getReference("category");
        ref.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                System.out.println(dataSnapshot);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

    }

    private void initPackageListener() {
        DatabaseReference ref = FirebaseDatabase.getInstance().getReference("/package");
        ref.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
//                System.out.println(dataSnapshot);
                for(DataSnapshot data : dataSnapshot.getChildren())
                    System.out.println(data);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
//        ref.addChildEventListener(new ChildEventListener() {
//            @Override
//            public void onChildAdded(DataSnapshot dataSnapshot, String s) {
//                int id = Integer.parseInt(dataSnapshot.getKey());
//                String name = dataSnapshot.child("name").getValue().toString();
//                double price = Double.parseDouble(dataSnapshot.child("price").getValue().toString());
//                Package packageObj = new Package(id, name, price);
//                String[] items = dataSnapshot.child("items").toString().split(",");
//
//                packagesBuffer.add(packageObj);
//                notifyObservers(observer -> observer.onPackageAdd(packageObj));
//            }
//
//            @Override
//            public void onChildChanged(DataSnapshot dataSnapshot, String s) {
//                int id = Integer.parseInt(dataSnapshot.getKey());
//                String name = dataSnapshot.child("name").getValue().toString();
//                double price = Double.parseDouble(dataSnapshot.child("price").getValue().toString());
//                Package packageObj = new Package(id, name, price);
//
//                int i = 0;
//                while(i<packagesBuffer.size()){
//                    if(id == packagesBuffer.get(i).getId()){
//                        packagesBuffer.set(i, packageObj);
//                        break;
//                    }
//                    i++;
//                }
//
//                notifyObservers(observer -> observer.onPackageChange(packageObj));
//
//            }
//
//            @Override
//            public void onChildRemoved(DataSnapshot dataSnapshot) {
//                int id = Integer.parseInt(dataSnapshot.getKey());
//                String name = dataSnapshot.child("name").getValue().toString();
//                double price = Double.parseDouble(dataSnapshot.child("price").getValue().toString());
//                Package packageObj = new Package(id, name, price);
//
//                int i = 0;
//                while(i<packagesBuffer.size()){
//                    if (id == packagesBuffer.get(i).getId()){
//                        packagesBuffer.remove(i);
//                        break;
//                    }
//                }
//
//                notifyObservers(observer -> observer.onPackageDelete(packageObj));
//
//            }
//
//            @Override
//            public void onChildMoved(DataSnapshot dataSnapshot, String s) {
//
//            }
//
//            @Override
//            public void onCancelled(DatabaseError databaseError) {
//
//            }
//        });
    }

    @Override
    public List<Category> loadCategories() {
        return null;
    }

    @Override
    public Order addOrder(Order order) {
        return null;
    }

    @Override
    public boolean checkBill(Package pk, int amount) {
        return false;
    }

    @Override
    public void setUrl(String url) {

    }

    @Override
    public List<Package> loadPackages() {
        return null;
    }

    @Override
    public List<Item> loadItems(Package pk) {
        return null;
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
