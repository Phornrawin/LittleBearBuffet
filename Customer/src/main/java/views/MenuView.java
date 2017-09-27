package views;

import controllers.CoreController;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.layout.AnchorPane;
import javafx.util.Callback;
import models.Order;


public class MenuView extends AnchorPane implements RootView{
    @FXML private MenuBarGrilledView menuBarGrilled;
    @FXML private MenuBarDelicatessenView menuBarDelicatessenView;
    @FXML private MenuBarDessertView menuBarDessertView;
    @FXML private MenuBarBeverageView menuBarBeverageView;
    @FXML private AnchorPane tableLayout;
    private TableView tableOrder;
    private CoreController controller;


    @FXML
    public void initialize(){

    }


    public void setController(CoreController controller) {
        this.controller = controller;
        menuBarGrilled.setMenuBarGrilled(this.menuBarGrilled);
        menuBarGrilled.setMenuView(this);
        menuBarGrilled.setController(controller);
        buildTableView();
    }

    public void buildTableView(){
        tableOrder = new TableView();
        tableOrder.setEditable(false);
        TableColumn nameMenu = new TableColumn("Menu");
        TableColumn amount = new TableColumn("Amount");
        tableOrder.getColumns().addAll(nameMenu, amount);
        tableLayout.getChildren().add(tableOrder);
//        tableOrder.setColumnResizePolicy(new Callback<TableView.ResizeFeatures, Boolean>() {
//            @Override
//            public Boolean call(TableView.ResizeFeatures p) {
//                return true;
//            }
//        });
    }

    public void addOrder(Order order) {

    }

    public void checkBill() {

    }
}
