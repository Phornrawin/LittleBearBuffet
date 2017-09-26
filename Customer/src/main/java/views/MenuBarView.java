package views;

import controllers.CoreController;
import javafx.fxml.FXML;
import javafx.geometry.Pos;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.VBox;
import models.Order;
import models.UrlStorage;

import java.util.ArrayList;

public class MenuBarView implements RootView {
    @FXML private AnchorPane anchorPaneMain;
    @FXML private FlowPane flowPaneMenu;
    private CoreController controller;

    @FXML
    public void initialize(){


    }
    public void createMenuBar(){
        UrlStorage urlStorage = new UrlStorage();
        ArrayList<String> urls = new ArrayList<String>();
        urls.addAll(urlStorage.getImageGrilledUrl().values());
        for(int i = 0; i < urls.size(); i++){
                ImageView imageView = new ImageView();
                imageView.setImage(new Image(urls.get(i)));
                VBox vBox = new VBox();
                vBox.setAlignment(Pos.CENTER);
                vBox.getChildren().add(imageView);
                flowPaneMenu.getChildren().add(vBox);

        }
    }


    public void setController(CoreController controller) {
        this.controller = controller;
        anchorPaneMain = new AnchorPane();
        flowPaneMenu = new FlowPane();
        flowPaneMenu.setAlignment(Pos.CENTER);
        createMenuBar();


    }

    public void addOrder(Order order) {

    }

    public void checkBill() {

    }
}
