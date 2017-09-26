import controllers.ClientManager;
import controllers.CoreController;
import controllers.MainController;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import models.*;
import views.RootView;

import java.io.IOException;

public class CustomerApplication extends Application {

    private CustomerStorage customerManager;
    private RestuarantStorage restuarantManager;
    private ClientManager dbManager;
    private CoreController coreController;
    private Stage primaryStage;
    private RootView rootView;

    public static void main(String[] args) {
//        DatabaseManager db = new ClientManager();
//        List<Category> categories = db.loadCategories();
//
//        System.out.println("categories = " + categories);
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        this.customerManager = new CustomerStorage();
        this.restuarantManager = new RestuarantStorage();
        this.dbManager = new ClientManager();
        this.coreController = new MainController();

        coreController.setDatabaseManager(dbManager);
        coreController.setCustomerManager(customerManager);
        coreController.setRestuarantManager(restuarantManager);

        this.coreController.start();
        this.primaryStage = primaryStage;
        initRoot();
    }

    private void initRoot() {
        try{
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("/MainView.fxml"));
            Pane mainLayout = loader.load();
            rootView = loader.getController();
            rootView.setController(coreController);

            Scene sc = new Scene(mainLayout);
            primaryStage.setScene(sc);
            primaryStage.show();
            primaryStage.setTitle("Little Bear Buffet");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
