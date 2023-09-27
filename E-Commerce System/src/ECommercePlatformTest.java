import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class ECommercePlatformTest {
    private Seller jane;
    private Seller mike;
    private Customer alice;
    private Customer bob;
    private Product laptop;
    private Product headphones;
    private Discount sellerDiscount;
    private Discount customerDiscount;

    @BeforeEach
    public void setUp() {
        jane = new Seller("Jane", 2001);
        mike = new Seller("Mike", 2002);
        laptop = new Product("Laptop", 3001, jane, 1000.0, 10);
        headphones = new Product("Headphones", 3002, mike, 100.0, 20);
        alice = new Customer("Alice", 1001, 1000.0);
        bob = new Customer("Bob", 1002, 500.0);
        sellerDiscount = new Discount(10.0);
        customerDiscount = new Discount(5.0);
    }

    @Test
    public void testProductListing() {
        assertEquals("Laptop", laptop.getName());
        assertEquals(jane, laptop.getSeller());
    }

    @Test
    public void testCustomerCart() {
        alice.addToCart(laptop);
        assertEquals(1, alice.getCart().size());
        assertTrue(alice.getCart().contains(laptop));
    }

    @Test
    public void testPlacingOrder() {
        alice.addToCart(laptop);
        Order order = alice.placeOrder();
        assertNotNull(order);
        assertEquals(1, alice.getPastOrders().size());
    }

    @Test
    public void testPaymentProcessing() {
        alice.addToCart(laptop);
        Order order = alice.placeOrder();
        Payment payment = alice.makePayment(order, PaymentMethod.WALLET);
        assertNotNull(payment);
        assertTrue(payment.isSuccessful());
    }

    @Test
    public void testSellerRating() {
        alice.addToCart(laptop);
        alice.placeOrder();
        alice.makePayment(alice.getPastOrders().get(0), PaymentMethod.WALLET);
        alice.leaveReview(laptop, 5);
        assertEquals(5.0, jane.getRating());
    }

    @Test
    public void testDiscountApplication() {
        laptop.setDiscount(sellerDiscount);
        headphones.setDiscount(customerDiscount);

        assertEquals(900.0, laptop.calculateDiscountedPrice());
        assertEquals(90.0, headphones.calculateDiscountedPrice());
    }

    @Test
    public void testShippingStatus() {
        alice.addToCart(laptop);
        Order order = alice.placeOrder();
        alice.makePayment(order, PaymentMethod.WALLET);
        order.markShipped();
        assertEquals(OrderStatus.SHIPPED, order.getStatus());
    }
}
