package views;

import controllers.CoreController;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.layout.Pane;
import models.Order;

import java.io.IOException;

public class MenuView implements RootView{
    @FXML private TabPane tabPaneMenu;

    @FXML private MenuBarGrilledView menuBarGrilledView;
    @FXML private MenuBarDelicatessenView menuBarDelicatessenView;
    @FXML private MenuBarDessertView menuBarDessertView;
    @FXML private MenuBarBeverageView menuBarBeverageView;
    private CoreController controller;


    @FXML
    public void initialize(){

    }

    public void createTab(){



    }
    public void setController(CoreController controller) {
        this.controller = controller;
        menuBarGrilledView = new MenuBarGrilledView();
        menuBarGrilledView.setController(controller);
        createTab();


    }

    public void addOrder(Order order) {

    }

    public void checkBill() {

    }
}
