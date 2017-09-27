package views;

import controllers.CoreController;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.scene.control.Label;
import javafx.scene.control.Menu;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;
import models.Order;


public class MenuVbox extends VBox implements RootView{
    private ImageView menuImage;
    private Label menuName;
    private StringProperty menuNameString;
    private MenuView menuView;
    private IntegerProperty amount = new SimpleIntegerProperty(this, "amount");



    public MenuVbox(String urlImage, String menuName) {
        this.menuImage = new ImageView();
        this.menuImage.setImage(new Image(urlImage));
        this.menuName = new Label(menuName);
        this.menuNameString = new SimpleStringProperty(this, "menuName");
        this.menuImage.setFitWidth(150);
        this.menuImage.setFitHeight(150);
        this.getChildren().addAll(this.menuImage, this.menuName);

    }

    public void setVBoxListener(){
        this.addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {
            public void handle(MouseEvent event) {
                TableView tableView = menuView.getTableOrder();
                ObservableList<MenuVbox> menulist = menuView.getMenuList();
//                if (getTableColumnByName(tableView, menuName.getText().toString()).equals(null)){
//                    menulist.add(MenuVbox.this);
//                }else{
//                    getTableColumnByName(tableView, menuName.getText().toString()).set
//                }
            }
        });
    }
//    public  MenuVbox TableColumn<T, ?> getTableColumnByName(TableView<T> tableView, String name) {
//        for (TableColumn<T, ?> col : tableView.getColumns())
//            if (col.getText().equals(name)) return col ;
//        return null ;
//    }
    public StringProperty menuNameStringProperty(){
        return menuNameString;
    }
    public IntegerProperty amountIntegerProoerty(){
        return amount;
    }
    public void setMenuView(MenuView menuView){
        this.menuView = menuView;
    }

    public ImageView getMenuImage() {
        return menuImage;
    }

    public Label getMenuName() {
        return menuName;
    }


    public void setController(CoreController controller) {

    }

    public void addOrder(Order order) {

    }

    public void checkBill() {

    }
}
