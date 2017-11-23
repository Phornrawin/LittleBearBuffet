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
    private CoreController coreController;
    private Stage primaryStage;
    private RootView rootView;
    private StageController stageController;

    public static void main(String[] args) {
//        List<Category> categories = db.loadCategories();
//
//        System.out.println("categories = " + categories);
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        this.customerManager = new CustomerStorage();
        this.restaurantManager = new RestaurantStorage();
        this.coreController = new MainController();
        if (getParameters().getRaw().size() > 0)
            customerManager.setTable(Integer.parseInt(getParameters().getRaw().get(0)));
        else
            customerManager.setTable(1);

        FirebaseManagerAPI firebaseManagerAPI = new FirebaseManagerAPI();
        firebaseManagerAPI.addLoadCompleteListener(() -> Platform.runLater(() -> CustomerApplication.this.primaryStage.show()));
        firebaseManagerAPI.start();

        coreController.setDatabaseManager(firebaseManagerAPI);
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
