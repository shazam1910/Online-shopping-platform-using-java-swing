import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.*;
import java.awt.*;


public class ShoppingCentreGUI extends JFrame {
    private JLabel selectProductLabel, headingLabel, productIdLabel, nameLabel, categoryLabel, electronicLabel, clothingLabel, quantityLabel;
    private JButton shoppingCartButton, addShoppingCartButton, sortProductsButton;
    private JTable productsTable;
    private JComboBox productCategoryComboBox;
    private JScrollPane productsScrollPane;
    private JPanel detailsPanel;
    private Shoppingcart cart;
    private LoginGUI login;

    String[] columnNames = { "Product Id", "Name", "Category", "Price", "Info" };
    public ShoppingCentreGUI(){
        setTitle("Westminster Shopping Centre");
        setLayout(new GridBagLayout());
        initializeComponents();
        layoutGUI(new Shoppingcart());
        pack();
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);

        pack();
        setLocationRelativeTo(null);

    }

    private void initializeComponents() {
        selectProductLabel = new JLabel("Select Product Category:");
        headingLabel = new JLabel("Selected Product - Details");
        productIdLabel = new JLabel("Product ID");
        nameLabel = new JLabel("Name");
        categoryLabel = new JLabel("Category");
        electronicLabel = new JLabel("Electronic");
        clothingLabel = new JLabel("Clothing");
        quantityLabel = new JLabel("Quantity");

        shoppingCartButton = new JButton("Shopping Cart");
        shoppingCartButton.setBackground(new Color(239,198,46));
        addShoppingCartButton = new JButton("Add to Shopping Cart");
        addShoppingCartButton.setBackground(new Color(239,198,46));
        sortProductsButton = new JButton("Sort");
        sortProductsButton.setBackground(new Color(239,198,46));

        productsTable = new JTable();
        productCategoryComboBox = new JComboBox<>(new String[]{"All", "Electronics", "Clothing"});

        ArrayList<Product> products = WestminsterShoppingManager.getProductList();
        DefaultTableModel model = new DefaultTableModel(convertListToData(products), columnNames);
        productsTable = new JTable(model);
        productsTable.getTableHeader().setBackground(new Color(239,198,46));
        productsTable.getTableHeader().setFont(new Font("Times New Roman", Font.BOLD, 12));

        productsScrollPane = new JScrollPane(productsTable);
    }

    public void layoutGUI(Shoppingcart shoppingCart){
        this.cart = shoppingCart;
        GridBagConstraints gridBagConstraints = new GridBagConstraints();
        gridBagConstraints.fill = GridBagConstraints.HORIZONTAL;
        gridBagConstraints.insets = new Insets(1,1,1,1);
        gridBagConstraints.gridwidth = 1;
        gridBagConstraints.gridy = 0;


        gridBagConstraints.gridx = 1;
        gridBagConstraints.weightx = 1;
        add(shoppingCartButton,gridBagConstraints);


        gridBagConstraints.gridy++;
        gridBagConstraints.gridx = 0;
        add(selectProductLabel, gridBagConstraints);

        gridBagConstraints.gridx = 1;
        add(productCategoryComboBox,gridBagConstraints);

        gridBagConstraints.gridy++;
        gridBagConstraints.gridx = 1;
        add(sortProductsButton, gridBagConstraints);

        gridBagConstraints.gridwidth = 2;

        gridBagConstraints.gridy++;
        gridBagConstraints.gridx = 0;
        add(productsScrollPane, gridBagConstraints);

        gridBagConstraints.gridy++;
        add(headingLabel , gridBagConstraints);

        detailsPanel = new JPanel();
        detailsPanel.setLayout(new GridBagLayout());
        GridBagConstraints gridBagConstraints1 = new GridBagConstraints();
        gridBagConstraints1.fill = GridBagConstraints.HORIZONTAL;
        gridBagConstraints1.weightx = 1;
        gridBagConstraints1.anchor = GridBagConstraints.LINE_START;
        gridBagConstraints1.insets = new Insets(5, 0, 5, 10);
        gridBagConstraints1.gridwidth = 1;

        gridBagConstraints1.gridy = 0;
        gridBagConstraints1.gridx = 0;
        detailsPanel.add(productIdLabel , gridBagConstraints1);
        gridBagConstraints1.gridy++;
        detailsPanel.add(categoryLabel , gridBagConstraints1);
        gridBagConstraints1.gridy++;
        detailsPanel.add(nameLabel , gridBagConstraints1);
        gridBagConstraints1.gridy++;
        detailsPanel.add(electronicLabel , gridBagConstraints1);
        gridBagConstraints1.gridy++;
        detailsPanel.add(clothingLabel , gridBagConstraints1);
        gridBagConstraints1.gridy++;
        detailsPanel.add(quantityLabel , gridBagConstraints1);
        detailsPanel.setVisible(false);

        gridBagConstraints.gridy++;
        add(detailsPanel , gridBagConstraints);



        gridBagConstraints.insets = new Insets(10, 10, 10, 10);
        gridBagConstraints.gridwidth = 2;
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy++;
        add(addShoppingCartButton, gridBagConstraints);

        shoppingCartButton.addActionListener(e -> {
            new ShoppingCartGUI(shoppingCart);
        });

        productCategoryComboBox.addActionListener(e -> {
            String selectedCategory = (String) productCategoryComboBox.getSelectedItem();
            ArrayList<Product> products = WestminsterShoppingManager.getProductList();
            if (selectedCategory.equals("All")) {
                productsTable.setModel(new DefaultTableModel(convertListToData(products),
                        new String[] { "Product Id", "Name", "Category", "Price", "Info" }));
                        productsTable.setBackground(Color.cyan);
            } else if (selectedCategory.equals("Electronics")) {
                ArrayList<Product> electronics = new ArrayList<>();
                for (Product product : products) {
                    if (product instanceof Electronics) {
                        electronics.add(product);
                    }
                }
                productsTable.setModel(new DefaultTableModel(convertListToData(electronics),
                        new String[] { "Product Id", "Name", "Category", "Price", "Info" }));
            } else if (selectedCategory.equals("Clothing")) {
                ArrayList<Product> clothing = new ArrayList<>();
                for (Product product : products) {
                    if (product instanceof Clothing) {
                        clothing.add(product);
                    }
                }
                productsTable.setModel(new DefaultTableModel(convertListToData(clothing),
                        new String[] { "Product Id", "Name", "Category", "Price", "Info" }));
            }
        });

        sortProductsButton.addActionListener(e -> {
            ArrayList<Product> products = WestminsterShoppingManager.getProductList();
            Collections.sort(products, Comparator.comparing(Product::getProductId));
            productsTable.setModel(new DefaultTableModel(convertListToData(products),
                    new String[] { "Product Id", "Name", "Category", "Price", "Info" }));
        });

        productsTable.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                int row = productsTable.getSelectedRow();
                String productId = (String) productsTable.getValueAt(row, 0);
                Product product = WestminsterShoppingManager.getProduct(productId);

                detailsPanel.setVisible(true);
                pack();

                if (product.getAvailableItems() == 0) {
                    addShoppingCartButton.setEnabled(false);
                } else {
                    addShoppingCartButton.setEnabled(true);
                }

                productIdLabel.setText("Product Id: " + product.getProductId());
                categoryLabel.setText("Category: " + product.getClass().getName());
                nameLabel.setText("Name: " + product.getProductName());
                if (product instanceof Electronics) {
                    electronicLabel.setText("Brand: " + ((Electronics) product).getBrand());
                    clothingLabel.setText("Warranty Period: " + ((Electronics) product).getWarrantyPeriod());
                } else if (product instanceof Clothing) {
                    electronicLabel.setText("Size: " + ((Clothing) product).getSize());
                    clothingLabel.setText("Colour: " + ((Clothing) product).getColour());
                }
                quantityLabel.setText("Quantity: " + product.getAvailableItems());
            }
        });

        productsTable.setDefaultRenderer(Object.class, new DefaultTableCellRenderer() {
            @Override
            public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected,
                                                           boolean hasFocus, int row, int column) {
                Component c = super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
                String productId = (String) table.getValueAt(row, 0);
                Product product = WestminsterShoppingManager.getProduct(productId);
                if (product != null && product.getAvailableItems() <= 2) {
                    c.setForeground(Color.RED);
                } else {
                    c.setForeground(Color.BLACK);
                }
                return c;
            }
        });

        addShoppingCartButton.addActionListener(e -> {
            int row = productsTable.getSelectedRow();
            if (row >= 0){
                String productID = (String) productsTable.getValueAt(row,0);
                Product product = WestminsterShoppingManager.getProduct(productID);
                if(product != null && product.getAvailableItems() > 0) {

                    shoppingCart.addToCart(product, 1);
                    productsTable.clearSelection();
                    JOptionPane.showMessageDialog(null, product.getProductName() + " added to the cart.");
                    product.setAvailableItems(product.getAvailableItems() - 1);
                    detailsPanel.setVisible(false);

                }
                ShoppingCartGUI cartGUI = new ShoppingCartGUI(cart);
                cartGUI.setVisible(false);
                cartGUI.updateCart();
                pack();
            }
        });
    }


    private Object[][] convertListToData (ArrayList<Product> productArrayList) {
        Object[][] data = new Object[productArrayList.size()][5];
        for (int i = 0; i < productArrayList.size(); i++) {
            Product product = productArrayList.get(i);
            data[i][0] = product.getProductId();
            data[i][1] = product.getProductName();
            data[i][2] = product.getClass().getName();
            data[i][3] = product.getPrice();
            if (product instanceof Electronics) {
                data[i][4] = ((Electronics) product).getInfo();
            } else if (product instanceof Clothing) {
                data[i][4] = ((Clothing) product).getInfo();
            }
        }
        return data;
    }
}