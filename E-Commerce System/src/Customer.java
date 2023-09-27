import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class Customer {

    private final String name;
    private final int id;
    private double wallet;
    private List<Product> cart;
    private List<Order> pastOrders;
    private double orderReview;


    public Customer(String name, int id, double wallet) {
        this.name = name;
        this.id = id;
        this.wallet = wallet;
        cart = new ArrayList<>();
        pastOrders = new ArrayList<>();
    }

    public void addToCart(Product product) {
        cart.add(product);
    }

    public List<Product> getCart() {
        return new ArrayList<>(cart);
    }

    public Order placeOrder() {
        if (cart.isEmpty()) {
            throw new IllegalStateException("Cart is empty. Cannot place an order.");
        }

        Product product = cart.get(0);
        int orderId = product.getId() * 10;
        Seller seller = new Seller("myMart", 0001);
        Order newOrder = new Order(orderId, seller, this);

        pastOrders.add(newOrder);
        cart.clear();

        return newOrder;
    }



    public List<Order> getPastOrders() {
        return new ArrayList<>(pastOrders);
    }

    public Payment makePayment(Order order, PaymentMethod paymentMethod) {
        if(wallet < order.getOrderTotal()){
            throw new IllegalArgumentException("Insufficient wallet balance.");
        }

        if(order.getStatus() != OrderStatus.PLACED){
            throw new IllegalArgumentException("Order can't be made.");
        }

        Payment payment = new Payment(order.getOrderTotal(), paymentMethod);

        if(processPayment(payment)){
            wallet -=  order.getOrderTotal();
            order.processPayment(payment);
            order.setStatus(OrderStatus.PAID);
        }

        return payment;
    }


    private boolean processPayment(Payment payment){
        return true;
    }

    public void leaveReview(Product product, double star) {
        if(star > 5){
            throw new IllegalArgumentException("Should be in context of 5 star rating");
        }
        this.orderReview = star;
        product.getSeller().setRating(star);
    }
}
