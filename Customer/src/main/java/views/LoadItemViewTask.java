package views;

import javafx.collections.ObservableList;
import javafx.scene.Node;
import models.Item;

public class LoadItemViewTask implements Runnable{
    private ObservableList<Node> children;
    private OnClickAddOrderListener listener;
    private Item item;

    public LoadItemViewTask(ObservableList<Node> children, OnClickAddOrderListener listener, Item item) {
        this.children = children;
        this.listener = listener;
        this.item = item;
    }

    @Override
    public void run() {
        ItemView itemView = new ItemView(item);
        itemView.setListener(listener);
        try {
            children.addAll(itemView);
        } catch (Exception e) {
            System.err.println(children);
        }
        System.out.println("children = " + children + ", " + item.getName());
    }
}
