public class Clothing extends Product{
    private String size;
    private String colour;

    //Constructor
    public Clothing(String productId, String productName, int availableItems, double price, String size, String colour){
        super(productId, productName, availableItems, price);
        this.size = size;
        this.colour = colour;
    }

    public String getInfo() {
        return size + "" + colour;
    }


    //Encapsulation
    //Getters

    public String getSize() {
        return size;
    }

    public String getColour() {
        return colour;
    }

}
