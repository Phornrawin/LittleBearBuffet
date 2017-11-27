package views;

import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.control.Label;
import javafx.scene.effect.ColorAdjust;
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
    private boolean previousStatus;

    public ItemView(Item item){
        this.item = item;
        item.regisObserver(this);
        previousStatus = item.isAvailable();
        build();
    }

    private void build() {
        menuImage = new ImageView();
        menuName = new Label(item.getName());
        this.menuImage.setFitWidth(150);
        this.menuImage.setFitHeight(150);
        this.getChildren().addAll(menuImage, menuName);
        this.setPadding(new Insets(10));
//        this.maxHeight(Double.NEGATIVE_INFINITY);
//        this.maxWidth(Double.NEGATIVE_INFINITY);
        setPrefWidth(150);

        if (item.isAvailable())
            buildAvailable();
        else
            buildUnavailable();
    }
    private void buildAvailable(){
        try {
            menuImage.setImage(new Image(createUrl()));
            menuImage.setEffect(null);
        } catch (Exception e) {
            System.out.println("load image error " + createUrl());
            try{
                menuImage.setImage(new Image(item.getName() + ".jpg"));
            } catch (Exception e1){
                System.out.println("load image error " + item.getName());
            }
        }
    }
    private void buildUnavailable(){
        try {
            menuImage.setImage(new Image(createUrl()));
            ColorAdjust colorAdjust = new ColorAdjust();
            colorAdjust.setBrightness(-0.5);
            menuImage.setEffect(colorAdjust);
        } catch (Exception e) {
            System.out.println("createUrl() = " + createUrl());
        }

    }

    public void setListener(OnClickAddOrderListener listener){
        addEventHandler(MouseEvent.MOUSE_CLICKED, (event)->{
            if (item.isAvailable()){
                Order order = new Order("0", 1, item, null);
                item.decreaseBalance(1);
                System.out.println("item.getBalance() = " + item.getBalance());
                listener.onClick(order);
            }
        });
    }

    private String createUrl(){
        return "/images/" + item.getName() + ".jpg";
    }

    @Override
    public void onModelChange(Item model) {
        System.out.println("onModelChange " + previousStatus + " " + model.isAvailable());
        if (previousStatus != model.isAvailable()) {
            previousStatus = model.isAvailable();
            if (item.isAvailable()){
                buildAvailable();
            }
            else
                buildUnavailable();
        }
    }
}
