package views;

import controllers.CoreController;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import models.Order;

import java.io.IOException;

public class MenuView extends AnchorPane implements RootView{
    @FXML private MenuBarGrilledView menuBarGrilled;
    @FXML private MenuBarDelicatessenView menuBarDelicatessenView;
    @FXML private MenuBarDessertView menuBarDessertView;
    @FXML private MenuBarBeverageView menuBarBeverageView;
    private CoreController controller;


    @FXML
    public void initialize(){

    }


    public void setController(CoreController controller) {
        this.controller = controller;
        menuBarGrilled.setMenuBarGrilled(this.menuBarGrilled);
        menuBarGrilled.setController(controller);


    }

    public void addOrder(Order order) {

    }

    public void checkBill() {

    }
}
