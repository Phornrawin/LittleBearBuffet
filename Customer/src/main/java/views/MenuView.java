package views;

import controllers.CoreController;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.util.Callback;
import models.Order;

import java.util.ArrayList;


public class MenuView extends AnchorPane implements RootView{
    @FXML private MenuBarGrilledView menuBarGrilled;
    @FXML private MenuBarDelicatessenView menuBarDelicatessenView;
    @FXML private MenuBarDessertView menuBarDessertView;
    @FXML private MenuBarBeverageView menuBarBeverageView;
    @FXML private AnchorPane tableLayout;
    private ArrayList<MenuVbox> vboxes;
    private ObservableList<MenuVbox> menuList;
    private TableView<MenuVbox> tableOrder;
    private CoreController controller;


    @FXML
    public void initialize(){

    }


    public void setController(CoreController controller) {
        this.controller = controller;
        vboxes = new ArrayList<MenuVbox>();
        menuBarGrilled.setMenuBarGrilled(this.menuBarGrilled);
        menuBarGrilled.setMenuView(this);
        menuBarGrilled.setController(controller);
        menuBarGrilled.createMenuBar();
        buildTableView();


    }

    public void buildTableView(){
        tableOrder = new TableView<MenuVbox>();
        tableOrder.setEditable(false);
        TableColumn<MenuVbox, String> nameMenu = new TableColumn<MenuVbox, String>("Menu");
        TableColumn<MenuVbox, Integer> amount = new TableColumn<MenuVbox, Integer>("Amount");
        nameMenu.setCellValueFactory(new PropertyValueFactory<MenuVbox, String>("Menu"));
        amount.setCellValueFactory(new PropertyValueFactory<MenuVbox, Integer>("Amount"));

        menuList = FXCollections.observableArrayList(vboxes);
        tableOrder.setItems(menuList);
        tableOrder.getColumns().addAll(nameMenu, amount);

        tableOrder.setMinSize(400,550);
        tableOrder.setColumnResizePolicy(tableOrder.CONSTRAINED_RESIZE_POLICY);
        tableLayout.getChildren().add(tableOrder);

    }


    public ArrayList<MenuVbox> getVboxes() {
        return vboxes;
    }

    public void setVboxes(ArrayList<MenuVbox> vboxes) {
        this.vboxes = vboxes;
    }

    public TableView getTableOrder() {
        return tableOrder;
    }

    public void setTableOrder(TableView tableOrder) {
        this.tableOrder = tableOrder;
    }

    public ObservableList<MenuVbox> getMenuList() {
        return menuList;
    }

    public void setMenuList(ObservableList<MenuVbox> menuList) {
        this.menuList = menuList;
    }

    public void addOrder(Order order) {

    }

    public void checkBill() {

    }
}
