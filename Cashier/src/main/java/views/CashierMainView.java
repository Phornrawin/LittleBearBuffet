package views;

import controllers.PaymentController;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.util.StringConverter;
import models.Payment;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class CashierMainView implements Initializable, CashierView {
    @FXML private ComboBox cb_table;
    @FXML private Label lb_peopleAmt, lb_totalPrice, lb_eachPrice, lb_package;
    @FXML private Button btn_delete, btn_dot, btn_0, btn_1, btn_2, btn_3, btn_4, btn_5, btn_6, btn_7, btn_8, btn_9;
    private List<Payment> payments = new ArrayList<>();
    private PaymentController controller;

    public void initialize(URL location, ResourceBundle resources) {
    }

    public void setAvailable(List<Payment> payments) {
        this.payments = payments;
        System.out.println("payments.size = " + payments.size());
        initComboBoxTable();
    }

    public void onPayComplete(Payment payment) {

    }

    public void onPayFailure(Payment payment) {

    }

    public void setController(PaymentController controller) {
        this.controller = controller;
    }

    public void initComboBoxTable() {
        cb_table.getItems().addAll(payments);
        cb_table.getSelectionModel().selectFirst();
        cb_table.setConverter(new StringConverter() {
            @Override
            public String toString(Object object) {
                Payment payment = (Payment) object;
                return payment.getTable() + "";
            }

            @Override
            public Object fromString(String string) {
                return null;
            }
        });

        cb_table.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                Payment payment = (Payment) cb_table.getValue();
                lb_package.setText(payment.getaPackage().getName());
                lb_peopleAmt.setText(payment.getAmt() + "");
                lb_totalPrice.setText((payment.getAmt() * payment.getaPackage().getPrice()) + " Baht");
                lb_eachPrice.setText(payment.getaPackage().getPrice() + " Baht");

            }
        });
    }
}
