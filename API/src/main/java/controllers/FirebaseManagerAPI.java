package controllers;

import callbacks.OnResult;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;
import com.google.firebase.auth.FirebaseCredentials;
import com.google.firebase.database.*;
import com.google.firebase.tasks.OnCompleteListener;
import com.google.firebase.tasks.OnFailureListener;
import com.google.firebase.tasks.Task;
import listeners.LoadCompleteListener;
import listeners.OrderListener;
import listeners.PaymentListener;
import models.*;
import models.Package;

import java.io.FileInputStream;
import java.io.IOException;
import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.*;

public class FirebaseManagerAPI implements RealTimeDatabaseManager{

    private String url = "https://littlebearbuffet.firebaseio.com/";

    private Set<LoadCompleteListener> loadCompleteListeners = new HashSet<LoadCompleteListener>();
    private Set<OrderListener> orderListeners = new HashSet<OrderListener>();
    private Set<PaymentListener> paymentListeners = new HashSet<PaymentListener>();

    private List<Item> itemsBuffer = new ArrayList<Item>();
    private Map<Integer, Item> itemMap = new HashMap<Integer, Item>();
    private List<Order> ordersBuffer = new ArrayList<Order>();
    private Map<String, Order> orderMap = new HashMap<String, Order>();
    private List<Package> packages = new ArrayList<Package>();
    private List<List<String>> packageItem = new ArrayList<List<String>>();
    private List<Category> categories = new ArrayList<Category>();
    private List<Payment> payments = new ArrayList<>();

    private boolean isStarted = false;
    private boolean isPackageLoaded = false;
    private boolean isItemLoaded = false;
    private boolean isCategoryLoaded = false;

    public void start() {
        if (!isStarted){
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
        isStarted = true;
    }

    private void fetchData(){
        fetchPackages();
        fetchItem();
        fetchCategories();
        initOrderListener();
        initPaymentListener();
        initItemListener();
    }

    private void initItemListener() {
        System.out.println("initItemListener");
        DatabaseReference ref = FirebaseDatabase.getInstance().getReference("item");
        ref.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {

            }

            @Override
            public void onChildChanged(DataSnapshot dataSnapshot, String s) {
                System.out.println("onItemChange");
                String name = dataSnapshot.child("name").getValue().toString();
                int id = Integer.parseInt(dataSnapshot.getKey());
                int amt = Integer.parseInt(dataSnapshot.child("balance").getValue().toString());
                System.out.println(name + " " + amt);
                Item item = itemMap.get(id);
                item.setBalance(amt);
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

    private void initPaymentListener() {
        System.out.println("initPaymentListener");
        DatabaseReference ref = FirebaseDatabase.getInstance().getReference("payment");
        ref.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                Package pk = dataSnapshot.child("aPackage").getValue(Package.class);
                Date startTime = dataSnapshot.child("startTime").getValue(Date.class);
                Date payTime = dataSnapshot.child("payTime").getValue(Date.class);
                String id = dataSnapshot.child("id").getValue().toString();
                int amt = Integer.parseInt(dataSnapshot.child("amt").getValue().toString());
                int table = Integer.parseInt(dataSnapshot.child("table").getValue().toString());
                boolean paid = Boolean.parseBoolean(dataSnapshot.child("paid").getValue().toString());
//                Payment payment = dataSnapshot.getValue(Payment.class);
                Payment payment = new Payment(amt, pk, paid, table);
                payment.setStartTime(startTime);
                payment.setPayTime(payTime);
                payment.setId(id);
                System.out.println("new payment = " + payment);
                notifyPaymentListener((listener -> {
                    listener.onPaymentAdd(payment);
                }));
            }

            @Override
            public void onChildChanged(DataSnapshot dataSnapshot, String s) {
                Package pk = dataSnapshot.child("aPackage").getValue(Package.class);
                Date startTime = dataSnapshot.child("startTime").getValue(Date.class);
                Date payTime = dataSnapshot.child("payTime").getValue(Date.class);
                String id = dataSnapshot.child("id").getValue().toString();
                int amt = Integer.parseInt(dataSnapshot.child("amt").getValue().toString());
                int table = Integer.parseInt(dataSnapshot.child("table").getValue().toString());
                boolean paid = Boolean.parseBoolean(dataSnapshot.child("paid").getValue().toString());
//                Payment payment = dataSnapshot.getValue(Payment.class);
                Payment payment = new Payment(amt, pk, paid, table);
                payment.setStartTime(startTime);
                payment.setPayTime(payTime);
                payment.setId(id);
                System.out.println("change payment = " + payment);
                notifyPaymentListener((listener -> {
                    listener.onPaymentChange(payment);
                }));
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

    private void initOrderListener() {
        DatabaseReference ref = FirebaseDatabase.getInstance().getReference("order");
        ref.addChildEventListener(new ChildEventListener() {
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
                Package aPackage = packages.get(Integer.parseInt(paymentRef.child("aPackage").child("id").getValue().toString()));
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


                ordersBuffer.add(order);
                orderMap.put(id, order);
                // TODO notify order add
                notifyOrderListener();
            }

            public void onChildChanged(DataSnapshot dataSnapshot, String s) {
                // TODO notify order change
            }

            public void onChildRemoved(DataSnapshot dataSnapshot) {
                // TODO notify order remove
            }

            public void onChildMoved(DataSnapshot dataSnapshot, String s) {

            }

            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }

    private void fetchCategories() {
        DatabaseReference ref = FirebaseDatabase.getInstance().getReference("category");
        System.out.println("category ref = " + ref);
        ref.addListenerForSingleValueEvent(new ValueEventListener() {
            public void onDataChange(DataSnapshot dataSnapshot) {
                for(DataSnapshot data : dataSnapshot.getChildren()){
                    int id = Integer.parseInt(data.getKey());
                    String name = data.child("name").getValue().toString();
                    Category category = new Category(id, name);
                    categories.add(category);
                }
                System.out.println("FirebaseManager: client receive category");
                isCategoryLoaded = true;
                checkLoadStatus();
//                fetchItem();
            }
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }

    private void fetchItem() {
        DatabaseReference ref = FirebaseDatabase.getInstance().getReference("item");
        System.out.println("item ref = " + ref);
        ref.addListenerForSingleValueEvent(new ValueEventListener() {
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
                isItemLoaded = true;
                checkLoadStatus();
            }

            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }

    private void fetchPackages() {
        DatabaseReference ref = FirebaseDatabase.getInstance().getReference("package");
        ref.addListenerForSingleValueEvent(new ValueEventListener() {
            public void onDataChange(DataSnapshot dataSnapshot) {
                for (DataSnapshot data : dataSnapshot.getChildren()){
                    int id = Integer.parseInt(data.getKey());
                    String name = data.child("name").getValue().toString();
                    double price = Double.parseDouble(data.child("price").getValue().toString());
                    Package packageObj = new Package(id, name, price);
                    packages.add(packageObj);

                    String[] items = data.child("items").getValue().toString().split(",");
                    packageItem.add(Arrays.asList(items));
                }
                System.out.println("FirebaseManager, client receive package");
                isPackageLoaded = true;
                checkLoadStatus();
            }

            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }

    private void checkLoadStatus() {
        if (isCategoryLoaded && isItemLoaded && isPackageLoaded)
            notifyLoadComplete();
    }

    public void addLoadCompleteListener(LoadCompleteListener listener) {
        loadCompleteListeners.add(listener);
    }

    public void removeLoadCompleteListener(LoadCompleteListener listener) {
        loadCompleteListeners.remove(listener);
    }

    public void addOrderListener(OrderListener listener) {
        orderListeners.add(listener);
    }

    public void removeOrderListener(OrderListener listener) {
        orderListeners.remove(listener);
    }

    public List<Payment> addPaymentListener(PaymentListener listener) {
        paymentListeners.add(listener);
        return payments;
    }

    public void removePaymentListener(PaymentListener listener) {
        paymentListeners.remove(listener);
    }

    public List<Item> getItems(Package pk) {
        return itemsBuffer;
    }

    public List<Package> getPackages() {
        return packages;
    }

    public List<Category> getCategories() {
        return categories;
    }

    public List<Order> getOrders(Payment payment) {
        List<Order> orders = new ArrayList<Order>();
        for (Order order : this.ordersBuffer)
            if (order.getPayment().getId().equals(payment.getId()))
                orders.add(order);
        return orders;
    }

    public void addOrder(Order order, OnResult<Order> callback) {
        System.out.println("addOrder in db " + order);
        DatabaseReference ref = FirebaseDatabase.getInstance().getReference("order").push();
        DatabaseReference iref = FirebaseDatabase.getInstance().getReference("item").child(order.getItem().getId() + "");
        ref.setValue(order)
                .addOnCompleteListener((task ->callback.onComplete(order)))
                .addOnFailureListener(e -> {
                    e.printStackTrace();
                    callback.onFailure(order);
                });
        iref.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                int balance = Integer.parseInt(dataSnapshot.child("balance").getValue().toString());
                iref.child("balance").setValue(balance-order.getAmount());
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }

    @Override
    public Payment selectPackage(Package pk, int table, int amt, final OnResult<Payment> callback) {
        DatabaseReference ref = FirebaseDatabase.getInstance().getReference("payment").push();
        String id = ref.getKey();
        final Payment payment = new Payment(id, table, amt, pk, false);
        Instant instant = Instant.from(LocalDateTime.now().atZone(ZoneId.systemDefault()));
        payment.setStartTime(Date.from(instant));
//        payment.setPayTime(Date.from(instant));
        ref.setValue(payment)
                .addOnCompleteListener((task)->callback.onComplete(payment))
                .addOnFailureListener((e)->{
                    e.printStackTrace();
                    callback.onFailure(payment);
                });
        return payment;
    }

    public void checkBill(Payment payment, OnResult<Payment> callback) {
        DatabaseReference ref = FirebaseDatabase.getInstance().getReference("payment").child(payment.getId());
        ref.child("paid").setValue("true")
                        .addOnFailureListener(e->{
                            e.printStackTrace();
                            callback.onFailure(payment);
                        })
                        .addOnCompleteListener(task->{
                            Date now = Date.from(Instant.from(LocalDateTime.now().atZone(ZoneId.systemDefault())));
                            ref.child("payTime").setValue(now)
                                                .addOnCompleteListener(task2->{
                                                    callback.onComplete(payment);
                                                })
                                                .addOnFailureListener(e->{
                                                    e.printStackTrace();
                                                    callback.onFailure(payment);
                                                });
                        });
    }



    private void notifyLoadComplete(){
        for (LoadCompleteListener listener : loadCompleteListeners)
            listener.onComplete();
    }
    private void notifyOrderListener(){

    }
    private void notifyPaymentListener(NotifierPaymentListener notifier){
        for (PaymentListener listener:paymentListeners){
            notifier.perform(listener);
        }
    }

    private interface NotifierPaymentListener{
        void perform(PaymentListener listener);
    }
}

