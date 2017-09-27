package views;

import controllers.CoreController;
import javafx.fxml.FXML;
import javafx.geometry.Pos;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import models.Order;
import models.UrlStorage;


public class MenuBarGrilledView extends FlowPane implements RootView {
    @FXML private MenuBarGrilledView menuBarGrilled;
    private CoreController controller;
    private UrlStorage url;


    @FXML
    public void initialize(){

    }
    public void createMenuBar(){
        ImageView imageView = new ImageView();
        imageView.setImage(new Image(url.getImageGrilledUrl().get("Asparagus")));
        VBox vbox1 = new VBox();
        vbox1.getChildren().add(imageView);
        menuBarGrilled.getChildren().add(vbox1);
        menuBarGrilled.initialize();

//        anchorPaneMain.getChildren().add(root);
//        UrlStorage urlStorage = new UrlStorage();
//        ArrayList<String> urls = new ArrayList<String>();
//        urls.addAll(urlStorage.getImageGrilledUrl().values());
//        for(int i = 0; i < urls.size(); i++){
//            System.out.println(urls.get(i));
//            ImageView imageView = new ImageView();
//            imageView.setImage(new Image(urls.get(i)));
//            VBox vBox = new VBox();
//            vBox.setAlignment(Pos.CENTER);
//            vBox.getChildren().add(imageView);
//            flowPaneMenu.getChildren().add(vBox);
//
//        }
    }
    public void setMenuBarGrilled(MenuBarGrilledView root){
        this.menuBarGrilled = root;
    }

    public void setController(CoreController controller) {
        System.out.println("in MenuBar class");
        this.controller = controller;
        url = new UrlStorage();
        createMenuBar();


    }

    public void addOrder(Order order) {

    }

    public void checkBill() {

    }
}
