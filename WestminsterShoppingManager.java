import java.io.*;
import java.util.*;

public class WestminsterShoppingManager implements ShoppingManager {

    private static final int MAX_PRODUCTS = 50; //To set the limit for product adding
    private static ArrayList<Product> productList;  //Setting an arraylist to collect the product data
    private static ArrayList<User> userDetails;     //Setting an arraylist to collect the user details

    private Scanner scanner;
    static Scanner input = new Scanner(System.in);
    String productId = "";

    // Constructor
    public WestminsterShoppingManager() {
        WestminsterShoppingManager.productList = new ArrayList<>();
        WestminsterShoppingManager.userDetails = new ArrayList<>();
        this.scanner = new Scanner(System.in);
    }


    public static ArrayList<Product> getProductList() {
        return productList;
    }

    public static Product getProduct(String productId) {
        Product product = null;
        for (Product p : productList) {
            if (p.getProductId().equals(productId)) {
                product = p;
                break;
            }
        }
        return product;
    }

    // Method to validate product ID from user
    public String productIdValidation() {
        boolean validInput1 = false;
        while (!validInput1) {
            System.out.print("Enter the Product ID:  ");
            if (input.hasNextInt()) {
                System.out.println("Error: Please enter a correct input.");
                input.next();
            } else {
                productId = input.next();
                validInput1 = true;
            }
        }
        return productId;

    }

    // Implementation of interface methods
    @Override
    public void addProduct(Product product) {
        if (productList.size() < MAX_PRODUCTS) {
            productList.add(product);
            System.out.println(product.getProductName() + " added to the system.");
        } else {
            System.out.println("Maximum limit of products reached. Cannot add more products.");
        }
    }

    // Method to add a new product to the system based on user input
    private void addNewProduct() {
        boolean validInput = false;
        while (!validInput) {

            try {
                System.out.println("\nAdding a new product to the system:");
                System.out.println("1. Add Electronics");
                System.out.println("2. Add Clothing");
                System.out.println("3. Back to main menu");
                System.out.print("Enter your choice: ");
                int productType = scanner.nextInt();
                scanner.nextLine(); // Consume the newline character

                switch (productType) {
                    case 1 -> {
                        addElectronics(); // Method to add electronic products
                        validInput = true;
                    }
                    case 2 -> {
                        addClothing(); // Method to add clothing products
                        validInput = true;
                    }
                    case 3 -> {
                        displayMenu(); // Method to get back to the main menu
                        validInput = true;
                    }
                    default -> System.out.println("Invalid choice. Please enter the correct option.");
                }

            } catch (InputMismatchException e) {
                System.out.println("Invalid input. Please enter the correct type for each field.");
                scanner.nextLine(); // clear the scanner
            }
        }
    }

    // Method to add cloths in the productList
    private void addElectronics() {
        boolean validInput = false;
        while (!validInput) {
            try {
                productIdValidation();
                System.out.println("Enter the product name: ");
                String productName = scanner.nextLine();

                // Price input validation
                double price;
                while (true) {
                    try {
                        System.out.print("Enter the price: ");
                        price = scanner.nextDouble();
                        break; // Break the loop if input is valid
                    } catch (InputMismatchException e) {
                        System.out.println("Invalid input. Please enter a valid price.");
                        scanner.nextLine(); // clear the scanner
                    }
                }

                // Product quantity input validation
                int productQty;
                while (true) {
                    try {
                        System.out.print("Enter the product quantity: ");
                        productQty = scanner.nextInt();
                        break; // Break the loop if input is valid
                    } catch (InputMismatchException e) {
                        System.out.println("Invalid input. Please enter a valid quantity.");
                        scanner.nextLine(); // clear the scanner
                    }
                }

                scanner.nextLine(); // Consume the newline character
                System.out.print("Enter the brand: ");
                String brand = scanner.nextLine();

                // Warranty period input validation
                int warrantyPeriod;
                while (true) {
                    try {
                        System.out.print("Enter the warranty period (in months): ");
                        warrantyPeriod = scanner.nextInt();
                        break; // Break the loop if input is valid
                    } catch (InputMismatchException e) {
                        System.out.println("Invalid input. Please enter a valid warranty period.");
                        scanner.nextLine(); // clear the scanner
                    }
                }

                Electronics electronics = new Electronics(productId, productName, productQty, price, brand, warrantyPeriod);
                addProduct(electronics);
                validInput = true;
            } catch (InputMismatchException e) {
                System.out.println("Invalid input. Please enter the valid input.");
                scanner.nextLine(); // clear the scanner
            }
        }
    }


    // Method to add cloths in the productList
    private void addClothing() {
        boolean validInput = false;
        while (!validInput) {
            try {
                productIdValidation();
                System.out.println("Enter the product name: ");
                String productName = scanner.nextLine();

                // Price input validation
                double price;
                while (true) {
                    try {
                        System.out.print("Enter the price: ");
                        price = scanner.nextDouble();
                        break; // Break the loop if input is valid
                    } catch (InputMismatchException e) {
                        System.out.println("Invalid input. Please enter a valid price.");
                        scanner.nextLine(); // clear the scanner
                    }
                }

                // Product quantity input validation
                int productQty;
                while (true) {
                    try {
                        System.out.print("Enter the product quantity: ");
                        productQty = scanner.nextInt();
                        break; // Break the loop if input is valid
                    } catch (InputMismatchException e) {
                        System.out.println("Invalid input. Please enter a valid quantity.");
                        scanner.nextLine(); // clear the scanner
                    }
                }

                scanner.nextLine(); // Consume the newline character
                System.out.print("Enter the size: ");
                String size = scanner.nextLine();
                System.out.print("Enter the colour: ");
                String colour = scanner.nextLine();

                Clothing clothing = new Clothing(productId, productName, productQty, price, size, colour);
                addProduct(clothing);
                validInput = true;
            } catch (InputMismatchException e) {
                System.out.println("Invalid input. Please enter the valid input.");
                scanner.nextLine(); // clear the scanner
            }
        }
    }

    // Method to remove products from the product list
    @Override
    public void deleteProduct() {
        System.out.print("\nEnter the product ID to delete: ");
        String productIdToDelete = scanner.nextLine();

        // Find the product with the given ID
        Product productToDelete = null;
        for (Product product : productList) {
            if (product.getProductId().equals(productIdToDelete)) {
                productToDelete = product;
                break;
            }
        }
        if (productToDelete != null) {
            // Checking if the product is electronics or clothing
            String productType = (productToDelete instanceof Electronics) ? "Electronics" : "Clothing";

            // Remove the product from the system
            if (productList.remove(productToDelete)) {
                System.out.println(productToDelete.getProductName() + " removed from the system.");
                System.out.println("Total number of products left in the system: " + productList.size());
                // Save products to file after each removal
                saveFile();
            } else {
                System.out.println("Product not found in the system. ");
            }
        }  else {
            System.out.println("Product with ID " + productIdToDelete + " not found in the system.");
        }
    }

    // Method to print the product details from the product arraylist
    @Override
    public void printProducts() {
        if (productList.isEmpty()) {
            System.out.println("No products have been added.");
        }
        else{
        Collections.sort(productList, Comparator.comparing(Product::getProductId));
        System.out.println("Products in the system:");
        for (Product product : productList) {
            System.out.println("----------------------------------------");
            System.out.println("Product Name: " + product.getProductName());
            System.out.println("Product ID: " + product.getProductId());
            System.out.println("Price: $" + product.getPrice());
            System.out.println("Quantity: " + product.getAvailableItems());
            if (product instanceof Clothing) {
                Clothing clothing = (Clothing) product;
                System.out.println("Type: Clothing");
                System.out.println("Size: " + clothing.getSize());
                System.out.println("Color: " + clothing.getColour());
            } else if (product instanceof Electronics) {
                Electronics electronics = (Electronics) product;
                System.out.println("Type: Electronics");
                System.out.println("Brand: " + electronics.getBrand());
                System.out.println("Warranty Period: " + electronics.getWarrantyPeriod() + " months");
            }
            System.out.println("----------------------------------------");
        }
        }
    }

    // Method to save the list of products to a file
    @Override
    public void saveFile() {
        try (ObjectOutputStream prodSave = new ObjectOutputStream(new FileOutputStream("products.txt"))) {
            prodSave.writeObject(productList);
            System.out.println("Products saved to file successfully.");
        } catch (IOException e) {
            System.out.println("Error saving products to file: " + e.getMessage());
        }
    }


    // Method to load data from the file
    @SuppressWarnings("unchecked")
    public void loadFile() {
        try (ObjectInputStream prodLoad = new ObjectInputStream(new FileInputStream("products.txt"))) {
            Object obj = prodLoad.readObject();
            if (obj != null && obj instanceof List) {
                productList = (ArrayList<Product>) obj;
                System.out.println("Products loaded from file successfully.");
            } else {
                productList = new ArrayList<>(); // Initialize productList if obj is null
                System.out.println("No products found in file.");
            }
        } catch (IOException | ClassNotFoundException e) {
            System.out.println("Error loading products from file: " + e.getMessage());
        }
    }

    //Method to open the GUI
    @Override
    public void openGUI(){
        new LoginGUI();
    }

    @Override
    // Method to display the console menu
    public void displayMenu() {
        int choice;
        do {
            System.out.println("--------------------------------------------------");
            System.out.println("--------Westminster Shopping Manager Menu---------");
            System.out.println("--------------------------------------------------");
            System.out.println("1. Add a new product to the system ");
            System.out.println("2. Delete a product from the system ");
            System.out.println("3. Print the list of products in the system ");
            System.out.println("4. Save products to file ");
            System.out.println("5. Load products from the file ");
            System.out.println("6. Open GUI ");
            System.out.println("7. Exit ");
            System.out.println("--------------------------------------------------");
            System.out.print("Enter your choice: ");

            try {
                choice = scanner.nextInt();
                scanner.nextLine(); // Consume the newline character

                switch (choice) {
                    case 1:
                        addNewProduct(); // Method to add products
                        break;
                    case 2:
                        deleteProduct(); // Method to delete products
                        break;
                    case 3:
                        printProducts(); // Method to print the product details
                        break;
                    case 4:
                        saveFile(); // Method to save the product details in a file
                        break;
                    case 5:
                        loadFile(); // Method to load the product details from a file
                        break;
                    case 6:
                        openGUI(); // Method to open the graphical user interface of the shopping system
                        break;
                    case 7:
                        System.out.println("Exiting the system manager.");
                        break;
                    default:
                        System.out.println("Invalid choice. Please enter a valid option.");
                }
            } catch (InputMismatchException e) {
                System.out.println("Invalid input. Please enter a number.");
                scanner.nextLine(); // clear the scanner
                choice = 0; // reset choice
            }
        } while (choice != 7);
    }
}
