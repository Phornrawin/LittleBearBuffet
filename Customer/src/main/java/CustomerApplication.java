import controllers.CoreController;
import controllers.MainController;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import views.RootView;

import java.io.IOException;

public class CustomerApplication extends Application {


    private CoreController controller;
    private Stage primaryStage;
    private RootView rootView;

    public static void main(String[] args) {
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
