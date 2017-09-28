package views;


import controllers.CoreController;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
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
    @FXML private ImageView imageViewPandaSet, imageViewKoalaSet, imageViewGrizzlySet, imageViewPolaSet;
    @FXML private Pane menuLayout;
    private CoreController controller;
    private List<Package> packages;

    @FXML
    public void initialize(){

    }

    @FXML
    public void imageViewPandaSetClicked(MouseEvent event){
        System.out.println("in panda set listener");
        // TODO get amount and sent to controller
        controller.selectPackage(packages.get(0), 1);
        buildMenuView();
    }

    @FXML
    public void imageViewKoalaSetClicked(MouseEvent event){

    }

    @FXML
    public void imageViewGrizzlySetClicked(MouseEvent event){

    }

    @FXML
    public void imageViewPolarSetClicked(MouseEvent event){

    }

    public void buildMenuView(){

        try {
            Stage secondStage = new Stage();
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(MainView.class.getResource("/MenuView.fxml"));
            menuLayout = (AnchorPane) loader.load();
            MenuView menuView = loader.getController();
            menuView.setController(controller);

            // Show the scene containing the root layout.
            Scene scene = new Scene(menuLayout);
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
        this.packages = controller.getPackages();
    }

    public void addOrder(Order order) {
        boolean isSuccess = controller.addOrder(order);

    }

    public void checkBill() {
        boolean isSuccess = controller.checkBill();
    }

}
