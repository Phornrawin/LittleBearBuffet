package views;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.Pane;
import javafx.util.Callback;
import models.Order;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

public class OrderedBarView extends FlowPane implements Initializable{
    @FXML private Pane tableOrdered;
    private ObservableList<Order> orderList;
    private TableView<Order> tableOrder;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
//        this.setPrefSize(Double.MAX_VALUE, Double.MAX_VALUE);
        tableOrder = new TableView<Order>();
        tableOrder.setEditable(false);
        TableColumn<Order, String> nameMenu = new TableColumn<Order, String>("Menu");
        TableColumn<Order, Integer> amount = new TableColumn<Order, Integer>("Amount");
        TableColumn<Order, String> state = new TableColumn<>("Status");
        TableColumn<Order, String> btnDelete = new TableColumn<>("");

        nameMenu.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<Order, String>, ObservableValue<String>>() {
            public ObservableValue<String> call(TableColumn.CellDataFeatures<Order, String> param) {
                SimpleStringProperty property = new SimpleStringProperty();

                property.setValue(param.getValue().getItem().getName());
                return property;
            }
        });
        amount.setCellValueFactory(new PropertyValueFactory<Order, Integer>("amount"));
        tableOrder.getColumns().addAll(nameMenu, amount, state, btnDelete);

        tableOrder.setMinSize(475 ,600);
//        tableOrder.setPrefSize(Double.MAX_VALUE, Double.MAX_VALUE);
        tableOrder.setColumnResizePolicy(tableOrder.CONSTRAINED_RESIZE_POLICY);
        tableOrdered.getChildren().add(tableOrder);

        if (orderList != null)
            tableOrder.setItems(orderList);


    }

    public void setData(List<Order> data){
        orderList = FXCollections.observableList(data);
        if (tableOrder != null)
            tableOrder.setItems(orderList);
    }
    public void refresh(){
        tableOrder.refresh();
    }
}
