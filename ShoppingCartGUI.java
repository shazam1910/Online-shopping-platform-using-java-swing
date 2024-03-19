import javax.swing.*;
import java.awt.*;
import java.util.Map;
import javax.swing.table.DefaultTableModel;
public class ShoppingCartGUI extends JFrame {
    private JLabel totalLabel, discount20Label, finalTotalLabel;
    private JTable cartProductsTable;
    private JScrollPane scrollPane;
    private JButton checkoutButton;
    private Shoppingcart cart;


    public ShoppingCartGUI(Shoppingcart shoppingCart){
        this.cart = shoppingCart;
        setTitle("Shopping Cart");
        setLayout(new GridBagLayout());
        initializeComp();
        layoutComponents();
        setVisible(true);
        pack();
        setLocationRelativeTo(null);
    }

    public void initializeComp(){

        double totalPrice = cart.calculateTotalCost();
        double discount20 = cart.calculateDiscountedCost();


        totalLabel = new JLabel("Total: " + totalPrice);
        discount20Label = new JLabel("Three items in same category Discount (20%):  - "+ discount20);
        finalTotalLabel = new JLabel("Final Total: "+ (totalPrice - discount20));
        checkoutButton = new JButton("Checkout");
        checkoutButton.setBackground(new Color(239,198,46));

        String[] columnNames = { "Product", "Quantity", "Price" };
        DefaultTableModel model = new DefaultTableModel(convertIntoData(cart.getProducts()), columnNames);
        cartProductsTable = new JTable(model);
        cartProductsTable.getTableHeader().setBackground(new Color(239,198,46));
        cartProductsTable.getTableHeader().setFont(new Font("Times New Roman", Font.BOLD, 12));

        scrollPane = new JScrollPane(cartProductsTable);
        cartProductsTable.setModel(model);
    }

    public void layoutComponents(){
        GridBagConstraints constraints = new GridBagConstraints();
        constraints.fill = GridBagConstraints.HORIZONTAL;
        constraints.insets = new Insets(10, 10, 10, 10);
        constraints.gridwidth = 1;

        constraints.gridy = 0;
        constraints.gridx = 0;
        add(scrollPane, constraints);

        constraints.anchor = GridBagConstraints.CENTER;

        constraints.gridy++;
        add(totalLabel, constraints);

        constraints.gridy++;
        add(discount20Label, constraints);

        constraints.gridy++;
        add(finalTotalLabel, constraints);

        constraints.gridy++;
        add(checkoutButton, constraints);

        checkoutButton.addActionListener(e -> {
            JOptionPane.showMessageDialog(null, "Checkout Successful");
            cart.getProducts().clear();
            this.dispose();
        });
    }
    private Object[][] convertIntoData(Map<Product, Integer> map) {
        Object[][] data = new Object[map.size()][3];
        int i = 0;
        for (Product product : map.keySet()) {
            if (product instanceof Electronics) {
                data[i][0] = product.getProductId() + "  " + product.getProductName() + "  "
                        + ((Electronics) product).getInfo();
            } else if (product instanceof Clothing) {
                data[i][0] = product.getProductId() + "  " + product.getProductName() + "  "
                        + ((Clothing) product).getInfo();
            }
            data[i][1] = map.get(product);
            data[i][2] = product.getPrice();
            i++;
        }
        return data;
    }

    public void updateCart() {
        Map<Product, Integer> ShoppingTableItems;
        ShoppingTableItems = cart.getProducts();
        DefaultTableModel model = (DefaultTableModel) cartProductsTable.getModel();
        model.setDataVector(convertIntoData(ShoppingTableItems), new String[] { "Product", "Quantity", "Price" });
        model.fireTableDataChanged();
        cartProductsTable.setModel(model);
    }
}