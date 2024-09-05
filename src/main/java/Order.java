import java.util.List;

public record Order(
        String id,
        List<Product> products,
        OrderStatus status) {
}

// Add enum to Order record
enum OrderStatus {
    PROCESSING,
    IN_DELIVERY,
    COMPLETED
}
