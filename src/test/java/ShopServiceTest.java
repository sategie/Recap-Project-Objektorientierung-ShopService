import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class ShopServiceTest {

    @Test
    void addOrderTest() {
        //GIVEN
        ShopService shopService = new ShopService();
        List<String> productsIds = List.of("1");


        //WHEN
        Order actual = shopService.addOrder(productsIds);


        //THEN
        Order expected = new Order("-1", List.of(new Product("1", "Apfel")), OrderStatus.PROCESSING);
        assertEquals(expected.products(), actual.products());
        assertNotNull(expected.id());

    }

    @Test
    void addOrderTest_whenInvalidProductId_expectNull() {
        //GIVEN
        ShopService shopService = new ShopService();
        List<String> productsIds = List.of("1", "2");

        //WHEN
        Order actual = shopService.addOrder(productsIds);

        //THEN
        assertNull(actual);

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
}
