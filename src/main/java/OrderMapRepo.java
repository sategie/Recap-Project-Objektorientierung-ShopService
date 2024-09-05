import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class OrderMapRepo implements OrderRepo{
    private Map<String, Order> orders = new HashMap<>();

    @Override
    public List<Order> getOrders() {
        return new ArrayList<>(orders.values());
    }

    @Override
    public Order getOrderById(String id) {
        return orders.get(id);
    }

    @Override
    public Order addOrder(Order newOrder) {
        orders.put(newOrder.id(), new Order(newOrder.id(), newOrder.products(), OrderStatus.PROCESSING));
        return orders.get(newOrder.id());
    }

    @Override
    public void removeOrder(String id) {
        orders.remove(id);
    }

    @Override
    public void updateOrderStatus(String id, OrderStatus status) {
        Order order = orders.get(id);
        if (order != null) {
            orders.put(id, new Order(order.id(), order.products(), status));
        }
    }
}
