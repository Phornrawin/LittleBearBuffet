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
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class FirebaseManager implements DatabaseManager, FirebaseObserable {
    private String url = "";
    private int table;
    private List<Package> packagesBuffer;
    private List<Item> itemsBuffer;
    private List<Category> categoriesBuffer;
    private List<FirebaseObserver> observers;

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
        try {
            FileInputStream serviceAccount = new FileInputStream("serviceAccountKey.json");

            FirebaseOptions options = new FirebaseOptions.Builder()
                    .setCredential(FirebaseCredentials.fromCertificate(serviceAccount))
                    .setDatabaseUrl(url)
                    .build();

            FirebaseApp.initializeApp(options);

            initListener();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void initListener() {
        initPackageListener();
        initCategoryListener();
    }

    private void initCategoryListener() {
        DatabaseReference ref = FirebaseDatabase.getInstance().getReference("category");
        ref.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                int id = Integer.parseInt(dataSnapshot.getKey());
                String name = dataSnapshot.child("name").getValue().toString();

                Category category = new Category(id, name);
                categoriesBuffer.add(category);

                notifyObservers(observer -> observer.onCategoryAdd(category));

            }

            @Override
            public void onChildChanged(DataSnapshot dataSnapshot, String s) {

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

    private void initPackageListener() {
        DatabaseReference ref = FirebaseDatabase.getInstance().getReference("/package");
        ref.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                int id = Integer.parseInt(dataSnapshot.getKey());
                String name = dataSnapshot.child("name").getValue().toString();
                double price = Double.parseDouble(dataSnapshot.child("price").getValue().toString());
                Package packageObj = new Package(id, name, price);

                packagesBuffer.add(packageObj);
                notifyObservers(observer -> observer.onPackageAdd(packageObj));
            }

            @Override
            public void onChildChanged(DataSnapshot dataSnapshot, String s) {
                int id = Integer.parseInt(dataSnapshot.getKey());
                String name = dataSnapshot.child("name").getValue().toString();
                double price = Double.parseDouble(dataSnapshot.child("price").getValue().toString());
                Package packageObj = new Package(id, name, price);

                int i = 0;
                while(i<packagesBuffer.size()){
                    if(id == packagesBuffer.get(i).getId()){
                        packagesBuffer.set(i, packageObj);
                        break;
                    }
                    i++;
                }

                notifyObservers(observer -> observer.onPackageChange(packageObj));

            }

            @Override
            public void onChildRemoved(DataSnapshot dataSnapshot) {
                int id = Integer.parseInt(dataSnapshot.getKey());
                String name = dataSnapshot.child("name").getValue().toString();
                double price = Double.parseDouble(dataSnapshot.child("price").getValue().toString());
                Package packageObj = new Package(id, name, price);

                int i = 0;
                while(i<packagesBuffer.size()){
                    if (id == packagesBuffer.get(i).getId()){
                        packagesBuffer.remove(i);
                        break;
                    }
                }

                notifyObservers(observer -> observer.onPackageDelete(packageObj));

            }

            @Override
            public void onChildMoved(DataSnapshot dataSnapshot, String s) {

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
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
