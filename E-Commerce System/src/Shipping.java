public class Shipping {
    private String shippingMethod;
    private String address;
    private ShippingStatus status;

    public Shipping(String shippingMethod, String address) {
        this.shippingMethod = shippingMethod;
        this.address = address;
        this.status = ShippingStatus.NOT_SHIPPED;
    }

    public String getShippingMethod() {
        return shippingMethod;
    }

    public String getAddress() {
        return address;
    }

    public void markShipped() {
        status = ShippingStatus.SHIPPED;
    }

    public ShippingStatus getStatus() {
        return status;
    }


}

