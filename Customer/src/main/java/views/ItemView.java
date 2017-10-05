package views;

import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;
import models.Item;
import models.ModelObserver;
import models.Order;

public class ItemView extends VBox implements ModelObserver<Item> {
    private Item item;
    private ImageView menuImage;
    private Label menuName;

    public ItemView(Item item){
        try {
            this.item = item;
            item.regisObserver(this);
            menuImage = new ImageView();
//            System.out.println("createUrl() = " + createUrl());
            menuImage.setImage(new Image(createUrl()));
            menuName = new Label(item.getName());
            this.menuImage.setFitWidth(150);
            this.menuImage.setFitHeight(150);
            this.getChildren().addAll(menuImage, menuName);
            this.setPadding(new Insets(10));
//            this.maxHeight(Double.NEGATIVE_INFINITY);
//            this.maxWidth(Double.NEGATIVE_INFINITY);
            setPrefWidth(150);
        } catch (Exception e) {
            System.out.println("createUrl() = " + createUrl());
        }
    }

    public void setListener(final OnClickAddOrderListener listener){
        addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {
            public void handle(MouseEvent event) {
                Order order = new Order("0", 1, item, 0);
                listener.onClick(order);
            }
        });
    }

    private String createUrl(){
        return "/images/" + item.getName() + ".jpg";
    }


    @Override
    public void onModelChange(Item model) {

    }
}
