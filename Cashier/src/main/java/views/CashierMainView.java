package views;

import controllers.PaymentController;
import javafx.fxml.Initializable;
import models.Payment;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

public class CashierMainView implements Initializable, CashierView {
    private List<Payment> payments;
    private PaymentController controller;

    public void initialize(URL location, ResourceBundle resources) {

    }

    public void setAvailable(List<Payment> payments) {

    }

    public void onPayComplete(Payment payment) {

    }

    public void onPayFailure(Payment payment) {

    }

    public void setController(PaymentController controller) {
        this.controller = controller;
    }
}
