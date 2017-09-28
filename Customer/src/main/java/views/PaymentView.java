package views;

import controllers.CoreController;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import models.Order;

import java.time.LocalDate;


public class PaymentView implements RootView {
    @FXML private javafx.scene.control.Label labelDate, labelTable, labelPackage, labelAmount, labelPayment;
    private CoreController controller;
    private StageController stageController;

    public void setPaymentData(){
        labelDate.setText(LocalDate.now().toString());
        labelTable.setText(controller.getTable() + "");
        labelPackage.setText(controller.getCurrentPackage().toString());
        labelAmount.setText(controller.getAmount() + "");
        labelPayment.setText(controller.getTotalPrice() + " Baht");
    }

    @FXML
    private void onClickBtnBack(){
        this.stageController.showMenuView();
    }

    @FXML
    private void onClickBtnBackToMain(){
        this.controller.checkBill();
        this.stageController.showMainView();
    }

    public void setController(CoreController controller) {
        this.controller = controller;
        setPaymentData();
    }

    public void addOrder(Order order) {

    }

    public void checkBill() {

    }

    public void setStageController(StageController stageController) {
        this.stageController = stageController;
    }
}
