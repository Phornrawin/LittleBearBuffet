package views;

import controllers.CoreController;
import models.Order;

/**
 * Created by PC301 on 25/9/2560.
 */
public interface RootView {

    void setController(CoreController controller);
    void addOrder(Order order);
    void checkBill();
    void setStageController(StageController stageController);
    void onOrderAdd(Order order);
}
