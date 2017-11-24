import controllers.FirebaseManagerAPI;
import controllers.MainController;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import views.CashierMainView;

import java.io.IOException;

public class CashierApplication extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        FirebaseManagerAPI firebaseManager = new FirebaseManagerAPI();
        MainController controller = new MainController();
        controller.setDbManager(firebaseManager);
        firebaseManager.start();

        System.out.println("show CashierView");
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("/CashierMainView.fxml"));
            Pane mainLayout = loader.load();
            CashierMainView cashierMainView = loader.getController();
            cashierMainView.setController(controller);
            cashierMainView.setAvailable(controller.getPayments());
            controller.setView(cashierMainView);

            // Show the scene containing the root layout.
            Scene scene = new Scene(mainLayout);
            Stage stage = new Stage();
            stage.setScene(scene);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
