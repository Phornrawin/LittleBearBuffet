package views;


import controllers.CoreController;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import models.Order;
import models.Package;

import java.io.IOException;
import java.util.List;

public class MainView implements RootView {
    @FXML private TextField textViewAmountCustomer;
    private CoreController controller;
    private List<Package> packages;
    private StageController stageController;

    @FXML
    public void initialize(){

    }

    @FXML
    public void imageViewPandaSetClicked(MouseEvent event){
        try{
            System.out.println("in panda set listener");
            int amount = Integer.parseInt(textViewAmountCustomer.getText());
            controller.selectPackage(packages.get(0), amount);
            stageController.showMenuView();

        }catch (NumberFormatException e){
            showTopicWarning();
        }
    }

    @FXML
    public void imageViewKoalaSetClicked(MouseEvent event){
        try {
            System.out.println("in Koala set listener");
            int amount = Integer.parseInt(textViewAmountCustomer.getText());
            controller.selectPackage(packages.get(1), amount);
            stageController.showMenuView();
        } catch (NumberFormatException e) {
            showTopicWarning();
        }
    }

    @FXML
    public void imageViewGrizzlySetClicked(MouseEvent event){
        System.out.println("in Grizzly set listener");
        try {
            int amount = Integer.parseInt(textViewAmountCustomer.getText());
            controller.selectPackage(packages.get(2), amount);
            stageController.showMenuView();
        } catch (NumberFormatException e) {
            showTopicWarning();
        }
    }

    @FXML
    public void imageViewPolarSetClicked(MouseEvent event){
        System.out.println("in Polar set listener");
        try {
            int amount = Integer.parseInt(textViewAmountCustomer.getText());
            controller.selectPackage(packages.get(3), amount);
            stageController.showMenuView();
        } catch (NumberFormatException e) {
            showTopicWarning();
        }

    }

    public void setController(CoreController controller) {
        this.controller = controller;
        this.packages = controller.getPackages();
    }

    public void showTopicWarning(){
        Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.setTitle("Warning");
        alert.setHeaderText("Look, You have not entered the amount text field yet.");
        alert.setContentText("Please enter a number in the amount field.");
        alert.showAndWait();
    }


    public void addOrder(Order order) {
        boolean isSuccess = controller.addOrder(order);

    }

    public void checkBill() {
        boolean isSuccess = controller.checkBill();
    }

    public void setStageController(StageController stageController) {
        this.stageController = stageController;
    }

    @Override
    public void onOrderAdd(Order order) {

    }


}
