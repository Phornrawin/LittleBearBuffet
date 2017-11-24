package views;

import controllers.PaymentController;
import models.Payment;

import java.util.List;

public interface CashierView {
    void setAvailable(List<Payment> payments);
    void onPayComplete(Payment payment);
    void onPayFailure(Payment payment);
    void setController(PaymentController controller);
}
