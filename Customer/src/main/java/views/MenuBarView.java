package views;

import javafx.collections.ObservableList;
import javafx.scene.Node;
import javafx.scene.layout.FlowPane;
import models.Item;
import models.Order;

import java.util.List;

public class MenuBarView extends FlowPane {
    private List<Item> items;
    private RootView root;

    private OnClickAddOrderListener onClickItemListener = new OnClickAddOrderListener() {
        public void onClick(Order order) {
            root.addOrder(order);
        }
    };

    public void setItems(List<Item> items){
        this.items = items;
    }

    public void setRoot(RootView root){
        this.root = root;
    }
    public void createMenus(){
        for(Item item : items){
            ItemView itemView = ItemViewPool.getInstance().getItemView(item, onClickItemListener);
            if (itemView.getParent() != null)
                ((FlowPane) itemView.getParent()).getChildren().remove(itemView);
//            itemView.setListener(onClickItemListener);
            getChildren().addAll(itemView);
//            Runnable r = new LoadItemViewTask(getChildren(), onClickItemListener, item);
//            Thread thread = new Thread(r);
//            thread.start();
        }

    }


}

