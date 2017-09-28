package views;

import controllers.CoreController;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.util.Callback;
import models.Category;
import models.Item;
import models.Order;

import java.util.ArrayList;
import java.util.List;


public class MenuView extends AnchorPane implements RootView{
    @FXML private MenuBarView menuBarGrilled;
    @FXML private MenuBarView menuBarDelicatessen;
    @FXML private MenuBarView menuBarDessert;
    @FXML private MenuBarView menuBarBeverage;
    @FXML private AnchorPane tableLayout;
    @FXML private Button btnConfirm;
    private ObservableList<Order> orderList;
    private TableView<Order> tableOrder;
    private CoreController controller;
    private List<Order> bufferOrders = new ArrayList<Order>();
    private StageController stageController;


    @FXML
    public void initialize(){

    }


    public void setController(CoreController controller) {
        this.controller = controller;
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
        orderList = FXCollections.observableList(bufferOrders);
        tableOrder.setItems(orderList);

        tableOrder.getColumns().addAll(nameMenu, amount);

        tableOrder.setMinSize(400,550);
        tableOrder.setColumnResizePolicy(tableOrder.CONSTRAINED_RESIZE_POLICY);
        tableLayout.getChildren().add(tableOrder);

        btnConfirm.setOnMouseClicked(new EventHandler<MouseEvent>() {
            public void handle(MouseEvent event) {
                System.out.println("onClick confirm");
                for(Order order : bufferOrders){
                    // TODO handle isSuccess
                    boolean isSuccess = controller.addOrder(order);
                    if (!isSuccess)
                        System.err.println("addOrder fail " + order.getItem().getName());
                }

                bufferOrders.clear();
                tableOrder.refresh();
            }
        });


    }
    @FXML
    public void onClickLabelPayment(){
        System.out.println("in payment window");
        stageController.showPaymentView();
    }


    public TableView getTableOrder() {
        return tableOrder;
    }

    public void setTableOrder(TableView tableOrder) {
        this.tableOrder = tableOrder;
    }


    public void addOrder(Order order) {
        int i = 0;
        boolean isAdd = false;
        while (i<bufferOrders.size()) {
            if (bufferOrders.get(i).getItem().getId() == order.getItem().getId()) {
                bufferOrders.get(i).increaseAmount(order.getAmount());
                isAdd = true;
            }
            i++;
        }

        if (!isAdd)
            bufferOrders.add(order);

        tableOrder.refresh();
    }

    public void checkBill() {

    }

    public void setStageController(StageController stageController) {
        this.stageController = stageController;
    }
}
