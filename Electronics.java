public class Electronics extends Product {
    private String brand;
    private int warrantyPeriod;


    //Constructor
    public Electronics(String productId, String productName, int availableItems, double price, String brand, int warrantyPeriod){
        super(productId, productName, availableItems, price);
        this.brand = brand;
        this.warrantyPeriod = warrantyPeriod;
    }

    public String getInfo(){
        return brand + "" + warrantyPeriod;
    }

    //Encapsulation
    //Getter

    public String getBrand(){
        return brand;
    }

    public int getWarrantyPeriod() {
        return warrantyPeriod;
    }

    //Setter

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public void setWarrantyPeriod(int warrantyPeriod) {
        this.warrantyPeriod = warrantyPeriod;
    }
}
