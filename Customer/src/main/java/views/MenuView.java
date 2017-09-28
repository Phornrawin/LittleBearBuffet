package views;

import controllers.CoreController;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.util.Callback;
import models.Category;
import models.Item;
import models.Order;

import java.util.ArrayList;


public class MenuView extends AnchorPane implements RootView{
    @FXML private MenuBarView menuBarGrilled;
    @FXML private MenuBarView menuBarDelicatessen;
    @FXML private MenuBarView menuBarDessert;
    @FXML private MenuBarView menuBarBeverage;
    @FXML private AnchorPane tableLayout;
    private ArrayList<MenuVbox> vboxes;
    private ObservableList<Order> orderList;
    private TableView<Order> tableOrder;
    private CoreController controller;


    @FXML
    public void initialize(){

    }


    public void setController(CoreController controller) {
        this.controller = controller;
//        vboxes = new ArrayList<MenuVbox>();
//        menuBarGrilled.setMenuBarGrilled(this.menuBarGrilled);
//        menuBarGrilled.setMenuView(this);
//        menuBarGrilled.setController(controller);
//        menuBarGrilled.createMenuBar();
        initMenuBar();
        buildTableView();
    }

    private void initMenuBar(){
        ArrayList<Item> grilledItems = new ArrayList<Item>();
        ArrayList<Item> delicatessenItems = new ArrayList<Item>();
        ArrayList<Item> dessertItems = new ArrayList<Item>();
        ArrayList<Item> beverageItems = new ArrayList<Item>();
        System.out.println("controller = " + controller);
        System.out.println("controller.getCategories() = " + controller.getCategories());
        for(Category category : controller.getCategories()){
            System.out.println("category.getItems() = " + category.getItems());
            if("Meat".equals(category.getName()))
                grilledItems.addAll(category.getItems());
            else if("Vegetable".equals(category.getName()))
                grilledItems.addAll(category.getItems());
            else if("Dessert".equals(category.getName()))
                dessertItems.addAll(category.getItems());
            else if ("Delicatessen".equals(category.getName()))
                delicatessenItems.addAll(category.getItems());
            else if ("Beverage".equals(category.getName()))
                beverageItems.addAll(category.getItems());
        }

        System.out.println("grilledItems = " + grilledItems);
        System.out.println("dessertItems = " + dessertItems);

        menuBarGrilled.setItems(grilledItems);
        menuBarDessert.setItems(dessertItems);
        menuBarDelicatessen.setItems(delicatessenItems);
        menuBarBeverage.setItems(beverageItems);

        menuBarGrilled.createMenus();
        menuBarDessert.createMenus();
        menuBarDelicatessen.createMenus();
        menuBarBeverage.createMenus();

        menuBarGrilled.setRoot(this);
        menuBarDessert.setRoot(this);
        menuBarDelicatessen.setRoot(this);
        menuBarBeverage.setRoot(this);
    }

    public void buildTableView(){
        tableOrder = new TableView<Order>();
        tableOrder.setEditable(false);
        TableColumn<Order, String> nameMenu = new TableColumn<Order, String>("Menu");
        TableColumn<Order, Integer> amount = new TableColumn<Order, Integer>("Amount");
        nameMenu.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<Order, String>, ObservableValue<String>>() {
            public ObservableValue<String> call(TableColumn.CellDataFeatures<Order, String> param) {
                SimpleStringProperty property = new SimpleStringProperty();

                property.setValue(param.getValue().getItem().getName());
                return property;
            }
        });
        amount.setCellValueFactory(new PropertyValueFactory<Order, Integer>("amount"));

//        menuList = FXCollections.observableArrayList(vboxes);
        orderList = FXCollections.observableList(controller.getOrders());
        tableOrder.setItems(orderList);

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

//    public ObservableList<MenuVbox> getMenuList() {
//        return menuList;
//    }
//
//    public void setMenuList(ObservableList<MenuVbox> menuList) {
//        this.menuList = menuList;
//    }

    public void addOrder(Order order) {
        boolean isSuccess = controller.addOrder(order);
        if (isSuccess){
            tableOrder.refresh();
        }
    }

    public void checkBill() {

    }
}
