import controllers.CoreController;
import controllers.DatabaseManager;
import controllers.MainController;
import controllers.ClientManager;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import models.Category;
import views.RootView;

import java.io.IOException;
import java.util.List;

public class CustomerApplication extends Application {


    private CoreController controller;
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
        this.controller = new MainController();
        this.controller.start();
        this.primaryStage = primaryStage;
        initRoot();
    }

    private void initRoot() {
        try{
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("/MainView.fxml"));
            Pane mainLayout = loader.load();
            rootView = loader.getController();
            rootView.setController(controller);

            Scene sc = new Scene(mainLayout);
            primaryStage.setScene(sc);
            primaryStage.show();
            primaryStage.setTitle("Little Bear Buffet");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
