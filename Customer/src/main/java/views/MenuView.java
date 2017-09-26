package views;

import controllers.CoreController;
import javafx.fxml.FXML;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.layout.Pane;
import models.Order;

public class MenuView implements RootView{
    @FXML private Tab tabGrilled;
    private CoreController controller;
    private MenuBarView menuBarView;

    @FXML
    public void initialize(){

    }

    public void createTab(){
        tabGrilled = new Tab();

    }
    public void setController(CoreController controller) {
        this.controller = controller;
        menuBarView = new MenuBarView();
        menuBarView.setController(controller);


    }

    public void addOrder(Order order) {

    }

    public void checkBill() {

    }
}
