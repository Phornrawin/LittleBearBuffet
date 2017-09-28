package views;

import controllers.CoreController;
import javafx.stage.Stage;

public interface StageController {
    void setStage(Stage primaryStage);
    void showMenuView();
    void showMainView();
    void setController(CoreController controller);
}
