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
        return payments;
    }

    public void setView(CashierView view) {
        this.cashierView = view;
        cashierView.setAvailable(payments);
    }

    public void setDbManager(RealTimeDatabaseManager dbManager){
        this.dbManager = dbManager;
        List<Payment> payments = dbManager.addPaymentListener(this);
        this.payments.addAll(payments);
    }

    public void onPaymentAdd(Payment payment) {
        System.out.println("payment add " + payment);
        payments.add(payment);
        if (cashierView != null)
            cashierView.setAvailable(payments);
        System.out.println(cashierView + " add complete");
    }

    public void onPaymentChange(Payment payment) {

    }

    public void onPaymentRemove(Payment payment) {

    }
}
