package views;

import controllers.CoreController;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;

public class MainStageController implements StageController {
    private Stage primaryStage;
    private CoreController controller;
    public void setStage(Stage primaryStage) {
        this.primaryStage = primaryStage;
    }

    public void showPaymentView(){
        System.out.println("showPaymentView");
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(MenuView.class.getResource("/PaymentView.fxml"));
            AnchorPane paymentLayout = loader.load();
            PaymentView paymentView = loader.getController();
            paymentView.setController(controller);
            paymentView.setStageController(this);

            // Show the scene containing the root layout.
            Scene scene = new Scene(paymentLayout);
            primaryStage.setScene(scene);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public void showMenuView() {
        System.out.println("showMenuView");
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(MainView.class.getResource("/MenuView.fxml"));
            Pane menuLayout = loader.load();
            MenuView menuView = loader.getController();
            menuView.setController(controller);
            menuView.setStageController(this);

            // Show the scene containing the root layout.
            Scene scene = new Scene(menuLayout);
            primaryStage.setScene(scene);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public void showMainView() {
        try{
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("/MainView.fxml"));
            Pane mainLayout = loader.load();
            RootView mainView = loader.getController();
            mainView.setController(controller);
            mainView.setStageController(this);

            Scene sc = new Scene(mainLayout);
            primaryStage.setScene(sc);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void setController(CoreController controller) {
        this.controller = controller;
    }
}
