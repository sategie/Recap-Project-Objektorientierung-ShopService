import java.util.ArrayList;
import java.util.List;

public class OrderListRepo implements OrderRepo {
    private List<Order> orders = new ArrayList<>();

    public List<Order> getOrders() {
        return orders;
    }

    public Order getOrderById(String id) {
        for (Order order : orders) {
            if (order.id().equals(id)) {
                return order;
            }
        }
        return null;
    }

    public Order addOrder(Order newOrder) {
        orders.add(newOrder);
        return newOrder;
    }

    public void removeOrder(String id) {
        for (Order order : orders) {
            if (order.id().equals(id)) {
                orders.remove(order);
                return;
            }
        }

    }

//    Implement the newly created updateOrderStatus method
    @Override
    public void updateOrderStatus(String id, OrderStatus status) {
        for (int i = 0; i < orders.size(); i++) {
            Order order = orders.get(i);
            if (order.id().equals(id)) {
//                orders.set(i, new Order(order.id(), order.products(), status));
                orders.set(i, order.withStatus(status));
                return;
            }
        }
    }
}
