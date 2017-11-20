import controllers.*;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.concurrent.ScheduledService;
import javafx.concurrent.Task;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import models.*;
import views.MainStageController;
import views.RootView;
import views.StageController;

import java.io.IOException;

public class CustomerApplication extends Application{

    private CustomerStorage customerManager;
    private RestaurantStorage restaurantManager;
    private ClientManager dbManager;
    private CoreController coreController;
    private Stage primaryStage;
    private RootView rootView;
    private StageController stageController;

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
        this.restaurantManager = new RestaurantStorage();
        this.dbManager = new ClientManager();
        customerManager.setTable(1);
        this.coreController = new MainController();

        FirebaseManager firebaseManager = new FirebaseManager();
        firebaseManager.setTable(1);
        firebaseManager.addOnLoadCompleteListener(() -> Platform.runLater(() -> CustomerApplication.this.primaryStage.show()));
        firebaseManager.start();

        coreController.setDatabaseManager(firebaseManager);
        coreController.setCustomerManager(customerManager);
        coreController.setRestaurantManager(restaurantManager);

        this.coreController.start();
        this.primaryStage = primaryStage;

        stageController = new MainStageController();
        stageController.setStage(primaryStage);
        stageController.setController(coreController);
        stageController.showMainView();
        primaryStage.setTitle("Little Bear Buffet");
    }


}
