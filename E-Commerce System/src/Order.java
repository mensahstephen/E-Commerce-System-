import java.util.HashMap;
import java.util.Map;

public class Order {

    private int orderId;
    private Customer customer;
    private Seller seller;
    private Map<Product, Integer> productsWithQuantities;
    private OrderStatus status;
    private Payment payment;

    public Order(int orderID, Seller seller, Customer customer) {
        this.orderId = orderID;
        this.seller = seller;
        this.customer = customer;
        status = OrderStatus.PLACED;
        this.payment = null;
        productsWithQuantities = new HashMap<>();
    }

    public int getOrderId() {
        return orderId;
    }

    public Customer getCustomer() {
        return customer;
    }

    public Seller getSeller() {
        return seller;
    }

    public Payment getPayment() {
        return payment;
    }

    public void processPayment(Payment payment){
        this.payment = payment;
    }

    public Map<Product, Integer> getProductsWithQuantities() {
        return new HashMap<>(productsWithQuantities);
    }

    public double getOrderTotal(){
        double sum = 0.0;

        for (Map.Entry<Product, Integer> entry : productsWithQuantities.entrySet()) {
            Product product = entry.getKey();
            int quantity = entry.getValue();

            sum += product.getPrice() * quantity;
        }

        return sum;
    }

    public OrderStatus setStatus(OrderStatus newStatus) {

        OrderStatus currentStatus = this.status;

        switch (newStatus) {
            case PLACED:
                if (currentStatus == null) {
                    this.status = newStatus;
                }
                break;
            case PAID:
                if (currentStatus == OrderStatus.PLACED) {
                    this.status = newStatus;
                }
                break;
            case SHIPPED:
                if (currentStatus == OrderStatus.PAID) {
                    this.status = newStatus;
                }
                break;
            case DELIVERED:
                if (currentStatus == OrderStatus.SHIPPED) {
                    this.status = newStatus;
                }
                break;
            case CANCELLED:
                this.status = newStatus;
                break;
            default:
                throw new IllegalArgumentException("Unsupported state transition.");
        }

        return status;

    }


    public void markShipped() {
        status = OrderStatus.SHIPPED;
    }

    public OrderStatus getStatus() {
        return status;
    }
}
