import lombok.With;

import java.util.List;

public record Order(
        String id,
        List<Product> products,
        @With OrderStatus status
        ) {
}

// Add enum to Order record
enum OrderStatus {
    PROCESSING,
    IN_DELIVERY,
    COMPLETED
}
