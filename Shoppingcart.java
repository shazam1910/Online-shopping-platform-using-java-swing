import java.util.HashMap;
import java.util.Map;

public class Shoppingcart {
    private Map<Product, Integer> cartProducts;
    public Shoppingcart() {
        this.cartProducts = new HashMap<>();
    }

    public Map<Product, Integer> getProducts() {
        return cartProducts;
    }


    // Method to add products in the cart
    public void addToCart(Product product, int quantity) {
        if (cartProducts.containsKey(product)) {
            int currentQuantity = cartProducts.get(product);
            cartProducts.put(product, currentQuantity + quantity);
        } else {
            cartProducts.put(product, quantity);
        }
    }

    // Method to calculate the total cost of the products
    public double calculateTotalCost() {
        double total = 0.0;
        for (Map.Entry<Product, Integer> entry : cartProducts.entrySet()) {
            total += entry.getKey().getPrice() * entry.getValue();
        }
        return total;
    }



    // Method to calculate the discount amount
    public double calculateDiscountedCost() {
        // Check if there are at least three products of the same category
        if (threeProductDis()) {
            // Calculate the total cost
            double totalCost = calculateTotalCost();

            // Calculate the discount (20% of the total cost)
            double discount = 0.2 * totalCost;

            return discount;
        } else {
            // If less than three products, It returns the regular total cost without discount
            return 0.0;
        }
    }
    // Method to check if there are at least three products of the same category
    private boolean threeProductDis() {
        for (Map.Entry<Product, Integer> entry : cartProducts.entrySet()) {
            if (entry.getValue() >= 3) {
                return true;
            }
        }
        return false;
    }




}
