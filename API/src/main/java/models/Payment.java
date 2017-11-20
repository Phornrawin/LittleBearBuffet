package models;

public class Payment {
    private String id;
    private int amt;
    private Package aPackage;
    private boolean isPaid;

    public Payment(int amt, Package aPackage, boolean isPaid) {
        this.amt = amt;
        this.aPackage = aPackage;
        this.isPaid = isPaid;
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
}
