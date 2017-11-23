package views;

import models.Item;

import javax.annotation.Nullable;
import java.util.HashMap;
import java.util.Map;

public class ItemViewPool {
    private static ItemViewPool instance;
    private Map<Item, ItemView> itemViewMap = new HashMap<>();
    public static ItemViewPool getInstance(){
        if (instance == null)
            instance = new ItemViewPool();
        return instance;
    }

    public ItemView getItemView(Item item, @Nullable OnClickAddOrderListener listener){
        if (itemViewMap.get(item) == null){
            ItemView itemView = new ItemView(item);
            if (listener != null)
                itemView.setListener(listener);
            itemViewMap.put(item, itemView);
        }
        return itemViewMap.get(item);
    }


}
