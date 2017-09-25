import controllers.CoreController;
import controllers.MainController;
import javafx.application.Application;
import javafx.stage.Stage;

public class DatabaseApplication extends Application {

    public static void main(String[] args) {
        CoreController controller = new MainController();
        controller.start();

//        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {

    }
}
