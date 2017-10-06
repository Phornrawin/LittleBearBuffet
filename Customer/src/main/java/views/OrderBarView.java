package views;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.FlowPane;
import javafx.util.Callback;
import models.Order;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

public class OrderBarView extends FlowPane implements Initializable{
    @FXML private AnchorPane tableLayout;
    @FXML private Button btnConfirm;
    private RootView root;
    private ObservableList<Order> orderList;
    private TableView<Order> tableOrder;
    private OnConfirmListener confirmListener;



    @Override
    public void initialize(URL location, ResourceBundle resources) {
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

        tableOrder.getColumns().addAll(nameMenu, amount);

        tableOrder.setMinSize(400,550);
        tableOrder.setColumnResizePolicy(tableOrder.CONSTRAINED_RESIZE_POLICY);
        tableLayout.getChildren().add(tableOrder);

        btnConfirm.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                if (confirmListener != null)
                    confirmListener.perform();
                tableOrder.refresh();
            }
        });

        if (orderList != null)
            tableOrder.setItems(orderList);
    }

    public void setData(List<Order> data){
        orderList = FXCollections.observableList(data);
        if (tableOrder != null)
            tableOrder.setItems(orderList);
    }

    public void setOnConfirmListener(OnConfirmListener listener){
        this.confirmListener = listener;
    }


    public void setRoot(RootView root) {
        this.root = root;
    }

    public void refresh(){
        tableOrder.refresh();
    }

    public interface OnConfirmListener{
        void perform();
    }

    @Override
    public String toString() {
        return "OrderBarView&" + hashCode();
    }
}