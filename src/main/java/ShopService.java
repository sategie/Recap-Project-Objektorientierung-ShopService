import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

public class ShopService {
    private ProductRepo productRepo = new ProductRepo();
    private OrderRepo orderRepo = new OrderMapRepo();

//    Modify method to accommodate Optional<Product>
    public Order addOrder(List<String> productIds) {
        List<Product> products = new ArrayList<>();
        for (String productId : productIds) {
            Optional<Product> productToOrderOpt = productRepo.getProductById(productId);
            if (productToOrderOpt.isEmpty()) {
                System.out.println("Product mit der Id: " + productId + " konnte nicht bestellt werden!");
                return null;
            }
            products.add(productToOrderOpt.get());
        }

        Order newOrder = new Order(UUID.randomUUID().toString(), products, OrderStatus.PROCESSING);

        return orderRepo.addOrder(newOrder);
    }

    //    Method which filters the orders list by status and uses the collect method to collect the orders into a list
    public List<Order> getOrdersByStatus(OrderStatus status) {
        return orderRepo.getOrders().stream()
                .filter(order -> order.status().equals(status))
                .collect(Collectors.toList());
    }

    public void updateOrderStatus(String orderId, OrderStatus status) {
        orderRepo.updateOrderStatus(orderId, status);
    }

}
