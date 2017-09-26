import controllers.CoreController;
import controllers.MainController;
import controllers.SQLiteManager;
import javafx.application.Application;
import javafx.stage.Stage;

import java.util.List;

public class DatabaseApplication extends Application {

    public static void main(String[] args) {
        CoreController controller = new MainController();
        controller.start();
//        System.out.println("test db jar");
//        SQLiteManager db = new SQLiteManager();
//        List<Integer> ids = db.getCategoryIds();
//        System.out.println(ids);
//        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {

    }
}
