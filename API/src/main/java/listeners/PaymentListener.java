package listeners;

import models.Payment;

public interface PaymentListener {
    void onPaymentAdd(Payment payment);
    void onPaymentChange(Payment payment);
    void onPaymentRemove(Payment payment);
}
