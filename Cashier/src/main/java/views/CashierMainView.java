package views;

import controllers.PaymentController;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import models.Payment;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

public class CashierMainView implements Initializable, CashierView {
    @FXML private ComboBox cb_table;
    @FXML private Label lb_peopleAmt, lb_totalPrice, lb_eachPrice;
    @FXML private Button btn_delete, btn_dot, btn_0, btn_1, btn_2, btn_3, btn_4, btn_5, btn_6, btn_7, btn_8, btn_9;
    private List<Payment> payments; 
    private PaymentController controller;

    public void initialize(URL location, ResourceBundle resources) {

    }

    public void setAvailable(List<Payment> payments) {
        this.payments = payments;
    }

    public void onPayComplete(Payment payment) {

    }

    public void onPayFailure(Payment payment) {

    }

    public void setController(PaymentController controller) {
        this.controller = controller;
    }
}
