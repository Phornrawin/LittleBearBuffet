package views;

import controllers.CoreController;
import javafx.stage.Stage;

public interface StageController {
    void setStage(Stage primaryStage);
    void setController(CoreController controller);
    void showPaymentView();
    void showMenuView();
    void showMainView();
}
