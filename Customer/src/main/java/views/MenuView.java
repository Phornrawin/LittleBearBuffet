package views;

import controllers.CoreController;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Tab;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import models.Category;
import models.Item;
import models.Order;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;


public class MenuView extends AnchorPane implements RootView{
    @FXML private MenuBarView menuBarGrilled;
    @FXML private MenuBarView menuBarDelicatessen;
    @FXML private MenuBarView menuBarDessert;
    @FXML private MenuBarView menuBarBeverage;
    @FXML private Tab orderResultTab;
    @FXML private Tab orderedTab;
    private OrderBarView orderBar;
    private OrderedBarView orderedBar;
    //    @FXML private AnchorPane tableLayout;
//    @FXML private Button btnConfirm;
//    private ObservableList<Order> orderList;
//    private TableView<Order> tableOrder;
    private CoreController controller;
    private List<Order> bufferOrders = new ArrayList<Order>();
    private StageController stageController;


    @FXML
    public void initialize(){

    }


    public void setController(CoreController controller) {
        this.controller = controller;
        initMenuBar();
        initOrderBar();
        initOrderedBar();
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
    public void initOrderBar(){
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("/OrderBar.fxml"));
            Pane orderBarLayout = loader.load();
            orderResultTab.setContent(orderBarLayout);

            orderBar = loader.getController();
            orderBar.setRoot(this);
            orderBar.setData(bufferOrders);
            orderBar.setOnConfirmListener(new OrderBarView.OnConfirmListener() {
                @Override
                public void perform() {
                    for(Order order : bufferOrders){
                        boolean isSuccess = controller.addOrder(order);
                        if (!isSuccess)
                            System.err.println("addOrder fail " + order.getItem().getName());
                    }

                    orderedBar.refresh();
                    bufferOrders.clear();
                }
            });
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void initOrderedBar(){

        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("/OrderedBar.fxml"));
            Pane tableOrdered = loader.load();
            orderedTab.setContent(tableOrdered);

            orderedBar = loader.getController();
            orderedBar.setData(controller.getOrders());


        } catch (IOException e) {
            e.printStackTrace();
        }


    }
    @FXML
    public void onClickLabelPayment(){
        System.out.println("in payment window");
        stageController.showPaymentView();
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
        orderBar.refresh();
    }

    public void checkBill() {

    }

    public void setStageController(StageController stageController) {
        this.stageController = stageController;
    }
}
