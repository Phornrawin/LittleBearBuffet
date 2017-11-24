package listeners;

import models.Order;

public interface OrderListener {
    void onOrderAdd(Order order);
    void onOrderChange(Order order);
    void onOrderRemove(Order order);
}
