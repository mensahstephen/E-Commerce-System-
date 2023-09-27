public class Product {

    private String name;
    private int id;
    private Seller seller;
    private double price;
    private int inventoryCount;
    private Discount discount;

    public Product(String name, int id, Seller seller, double price, int inventoryCount) {
        this.name = name;
        this.id = id;
        this.seller = seller;
        this.price = price;
        this.inventoryCount = inventoryCount;
    }

    public String getName() {
        return name;
    }

    public Seller getSeller() {
        return seller;
    }

    public int getId() {
        return id;
    }

    public double getPrice() {
        return price;
    }

    public int getInventoryCount() {
        return inventoryCount;
    }

    public Discount getDiscount() {
        return discount;
    }

    public double calculateDiscountedPrice() {
        return price - (price * 0.10);
    }

    public void setDiscount(Discount sellerDiscount) {
        this.discount = sellerDiscount;
    }
}
