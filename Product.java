import java.io.Serializable;

public abstract class Product implements Serializable {
    private String productId;
    private String productName;
    private int availableItems;

    private double price;


    // Constructor for product class
    public Product(String productId, String productName, int availableItems, double price) {
        this.productId = productId;
        this.productName = productName;
        this.availableItems = availableItems;
        this.price = price;
    }

    // Encapsulation
    //  Getter methods
    public String getProductId(){
        return productId;
    }

    public String getProductName(){
        return productName;
    }

    public int getAvailableItems() {
        return availableItems;
    }

    public double getPrice() {
        return price;
    }


    //Setter methods

    public void setAvailableItems(int availableItems) {
        this.availableItems = availableItems;
    }

}
