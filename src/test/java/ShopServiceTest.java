import org.junit.jupiter.api.Test;

import java.time.Instant;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

class ShopServiceTest {

    @Test
    void addOrderTest() {
        //GIVEN
        ShopService shopService = new ShopService();
        List<String> productsIds = List.of("1");
        Instant beforeCreation = Instant.now();


        //WHEN
        Order actual = shopService.addOrder(productsIds);
        Instant afterCreation = Instant.now();


        //THEN
        Order expected = new Order(
                actual.id(),
                List.of(new Product("1", "Apfel")),
                OrderStatus.PROCESSING,
                actual.timestamp()
        );

        assertEquals(expected.products(), actual.products());
        assertEquals(expected.status(), actual.status());
        assertNotNull(actual.id());
        assertTrue(actual.timestamp().isAfter(beforeCreation) || actual.timestamp().equals(beforeCreation));
        assertTrue(actual.timestamp().isBefore(afterCreation) || actual.timestamp().equals(afterCreation));

    }

    @Test
    void addOrderTest_whenInvalidProductId_expectException() {
        //GIVEN
        ShopService shopService = new ShopService();
        List<String> productsIds = List.of("1", "2");

        //WHEN & THEN
        ProductNotFoundException exception = assertThrows(ProductNotFoundException.class, () -> {
            shopService.addOrder(productsIds);
        });
        assertEquals("Product with id 2 not found", exception.getMessage());
    }

    //    Add getOrdersByStatusTest
    @Test
    void getOrdersByStatusTest() {
        //GIVEN
        ShopService shopService = new ShopService();
        List<String> productsIds1 = List.of("1");
        Order order1 = shopService.addOrder(productsIds1);
        // Update the status of the order
        shopService.updateOrderStatus(order1.id(), OrderStatus.IN_DELIVERY);
        //WHEN
        List<Order> inDeliveryOrders = shopService.getOrdersByStatus(OrderStatus.IN_DELIVERY);
        //THEN
        assertEquals(1, inDeliveryOrders.size());
        assertEquals(OrderStatus.IN_DELIVERY, inDeliveryOrders.get(0).status());
    }

    @Test
    void updateOrderTest() {
        // GIVEN
        ShopService shopService = new ShopService();
        List<String> productsIds = List.of("1");
        Order order = shopService.addOrder(productsIds);
        // WHEN
        Optional<Order> updatedOrderOpt = shopService.updateOrder(order.id(), OrderStatus.COMPLETED);
        // THEN
        assertTrue(updatedOrderOpt.isPresent());
        Order updatedOrder = updatedOrderOpt.get();
        assertEquals(OrderStatus.COMPLETED, updatedOrder.status());
        assertEquals(order.id(), updatedOrder.id());
        assertEquals(order.products(), updatedOrder.products());
    }
    @Test
    void updateOrderTest_whenOrderNotFound() {
        // GIVEN
        ShopService shopService = new ShopService();
        // WHEN
        Optional<Order> updatedOrderOpt = shopService.updateOrder("non-existent-id", OrderStatus.COMPLETED);
        // THEN
        assertFalse(updatedOrderOpt.isPresent());
    }
}
