package models;

import com.google.firebase.database.Exclude;
import controllers.RealTimeDatabaseManager;

import java.text.SimpleDateFormat;
import java.util.Date;

public class Payment {
    private String id;
    private int table;
    private int amt;
    private Package aPackage;
    private boolean isPaid;
    private Date startTime;
    private Date payTime;

    public Payment() {
    }


    public Payment(int amt, Package aPackage, boolean isPaid, int table) {
        this.amt = amt;
        this.aPackage = aPackage;
        this.isPaid = isPaid;
        this.table = table;
    }



    public Payment(String id, int table, int amt, Package aPackage, boolean isPaid) {
        this.id = id;
        this.table = table;
        this.amt = amt;
        this.aPackage = aPackage;
        this.isPaid = isPaid;
    }

    public int getTable() {
        return table;
    }

    public String getId() {
        return id;
    }

    public int getAmt() {
        return amt;
    }

    public Package getaPackage() {
        return aPackage;
    }

    public boolean isPaid() {
        return isPaid;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setPaid(boolean paid) {
        isPaid = paid;
    }


    public Date getStartTime() {
        return startTime;
    }

    public void setStartTime(Date startTime) {
        this.startTime = startTime;
    }

    public Date getPayTime() {
        return payTime;
    }

    public void setPayTime(Date payTime) {
        this.payTime = payTime;
    }

    public void setTable(int table) {
        this.table = table;
    }

    public void setAmt(int amt) {
        this.amt = amt;
    }

    public void setaPackage(Package aPackage) {
        this.aPackage = aPackage;
    }


    @Override
    public String toString() {
        return "Payment{" +
                "id='" + id + '\'' +
                ", table=" + table +
                ", amt=" + amt +
                ", aPackage=" + aPackage +
                ", isPaid=" + isPaid +
                ", startTime=" + startTime +
                ", payTime=" + payTime +
                '}';
    }
}
