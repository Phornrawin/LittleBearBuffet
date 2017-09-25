package views;

import controllers.CoreController;
import models.Order;

/**
 * Created by PC301 on 25/9/2560.
 */
public interface RootView {

    public void setController(CoreController controller);

    public void addOrder(Order order);

    public void checkBill();
}
