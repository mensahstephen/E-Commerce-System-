import java.util.ArrayList;
import java.util.List;

public class Seller {
    private final String name;
    private final int id;
    private List<Product> productList;
    private Discount discount;

    double rating;
    public Seller(String name, int id) {
        this.name = name;
        this.id = id;
        productList = new ArrayList<>();
    }

    public String getName() {
        return name;
    }

    public int getId() {
        return id;
    }

    public List<Product> getProductList() {
        return new ArrayList<>(productList);
    }

    public void setRating(double rating) {
        this.rating = rating;
    }

    public void addProduct(Product product){
        productList.add(product);
    }

    public double getRating() {
        return rating;
    }
}
