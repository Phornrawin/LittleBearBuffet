package controllers;

import models.Payment;
import views.CashierView;

import java.util.List;

public interface PaymentController {
    void pay(Payment payment);
    List<Payment> getPayments();
    void setView(CashierView view);
}
