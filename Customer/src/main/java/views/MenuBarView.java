package views;

import controllers.CoreController;
import javafx.fxml.FXML;
import javafx.scene.layout.AnchorPane;
import models.Order;

import java.util.ArrayList;

public class MenuBarView implements RootView {
    @FXML private AnchorPane anchorPaneMain;
    private ArrayList<String> imageUrls;
    private CoreController controller;

    public void createMenuBar(){

    }

    public void addImageUrlList(){
        imageUrls = new ArrayList<String>();
        imageUrls.add("https://goo.gl/Az5j7H");

    }
    public void setController(CoreController controller) {
        this.controller = controller;


    }

    public void addOrder(Order order) {

    }

    public void checkBill() {

    }
}
