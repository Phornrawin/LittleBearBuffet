package views;

import controllers.CoreController;
import javafx.fxml.FXML;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.ScrollPane;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.VBox;
import models.Order;
import models.UrlStorage;
import java.util.ArrayList;


public class MenuBarGrilledView extends FlowPane implements RootView {
    @FXML private MenuBarGrilledView menuBarGrilled;
    private MenuView menuView;
    private CoreController controller;
    private UrlStorage url;


    @FXML
    public void initialize(){

    }
    public void createMenuBar(){
        UrlStorage urlStorage = new UrlStorage();
        ArrayList<String> urls = urlStorage.getImageGrilledUrl();
        for(int i = 0; i < urls.size(); i++){
            String s = String.format("/images/" + urls.get(i) + ".jpg");
            System.out.println(s);
            MenuVbox vBox = new MenuVbox(s, urls.get(i));
            vBox.setPadding(new Insets(10));
//            vBox.setVBoxListener();
            menuBarGrilled.getChildren().add(vBox);
        }


    }
    public void setMenuBarGrilled(MenuBarGrilledView root){
        this.menuBarGrilled = root;
    }

    public void setController(CoreController controller) {
        System.out.println("in MenuBar class");
        this.controller = controller;
        url = new UrlStorage();
    }
    public void setMenuView(MenuView menuView){
        this.menuView = menuView;
    }

    public void addOrder(Order order) {

    }

    public void checkBill() {

    }
}
