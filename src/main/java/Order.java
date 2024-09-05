import lombok.With;

import java.time.Instant;
import java.util.List;

public record Order(
        String id,
        List<Product> products,
        @With OrderStatus status,
        @With Instant timestamp
        ) {
}

// Add enum to Order record
enum OrderStatus {
    PROCESSING,
    IN_DELIVERY,
    COMPLETED
}
