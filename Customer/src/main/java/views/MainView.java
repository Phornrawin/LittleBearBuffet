package views;


import controllers.CoreController;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import models.Order;

import java.awt.*;
import java.awt.event.MouseEvent;
import java.io.IOException;

public class MainView implements RootView {
    @FXML private TextField textViewAmountCustomer;
    @FXML private ImageView imageViewPandaSet, imageViewKoalaSet, imageViewGrizzlySet, imageViewPolaSet;
    private CoreController controller;

    @FXML
    public void initialize(){

    }

    @FXML
    public void imageViewPandaSetClicked(javafx.scene.input.MouseEvent event){
        System.out.println("in panda set listener");
        buildMenuView();
    }

    @FXML
    public void imageViewKoalaSetClicked(javafx.scene.input.MouseEvent event){

    }

    @FXML
    public void imageViewGrizzlySetClicked(javafx.scene.input.MouseEvent event){

    }

    @FXML
    public void imageViewPolarSetClicked(javafx.scene.input.MouseEvent event){

    }

    public void buildMenuView(){

        try {
            Stage secondStage = new Stage();
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(MainView.class.getResource("/MenuView.fxml"));
            Pane mainLayout = null;
            mainLayout = (AnchorPane) loader.load();
            MenuView menuView = loader.getController();
            menuView.setController(controller);

            // Show the scene containing the root layout.
            Scene scene = new Scene(mainLayout);
            secondStage.setScene(scene);
            secondStage.setResizable(false);
            secondStage.setTitle("Select Menu");
            secondStage.initModality(Modality.APPLICATION_MODAL);
            secondStage.showAndWait();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
    public void setController(CoreController controller) {
        this.controller = controller;
    }

    public void addOrder(Order order) {
        boolean isSuccess = controller.addOrder(order);

    }

    public void checkBill() {
        boolean isSuccess = controller.checkBill();
    }

}
