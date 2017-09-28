package views;

import javafx.event.EventHandler;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;
import models.Item;
import models.Order;

public class ItemView extends VBox{
    private Item item;
    private ImageView menuImage;
    private Label menuName;

    public ItemView(Item item){
        try {
            this.item = item;
            menuImage = new ImageView();
//            System.out.println("createUrl() = " + createUrl());
            menuImage.setImage(new Image(createUrl()));
            menuName = new Label(item.getName());
            this.menuImage.setFitWidth(150);
            this.menuImage.setFitHeight(150);
            getChildren().addAll(menuImage, menuName);
        } catch (Exception e) {
            System.out.println("createUrl() = " + createUrl());
        }
    }

    public void setListener(final OnClickAddOrderListener listener){
        addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {
            public void handle(MouseEvent event) {
                Order order = new Order(0, 1, item, 0);
                listener.onClick(order);
            }
        });
    }

    private String createUrl(){
        return "/images/" + item.getName() + ".jpg";
    }


}
