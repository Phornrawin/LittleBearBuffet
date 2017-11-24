package controllers;

import callbacks.OnResult;
import listeners.PaymentListener;
import models.Payment;
import views.CashierView;

import java.util.ArrayList;
import java.util.List;

public class MainController implements PaymentController, PaymentListener{
    private List<Payment> payments = new ArrayList<Payment>();
    private RealTimeDatabaseManager dbManager;
    private CashierView cashierView;
    public void start(){
        dbManager.addPaymentListener(this);
    }

    public void pay(final Payment payment) {
        dbManager.checkBill(payment, new OnResult<Payment>() {
            public void onComplete(Payment obj) {
                cashierView.onPayComplete(payment);
            }

            public void onFailure(Payment obj) {
                cashierView.onPayFailure(payment);
            }
        });
    }

    public List<Payment> getPayments() {
        return null;
    }

    public void setView(CashierView view) {

    }

    public void setDbManager(RealTimeDatabaseManager dbManager){
        this.dbManager = dbManager;
    }

    public void onPaymentAdd(Payment payment) {

    }

    public void onPaymentChange(Payment payment) {

    }

    public void onPaymentRemove(Payment payment) {

    }
}
