package design_patterns.GUI;

import design_patterns.singleton.CartManager;
import design_patterns.factory.*;
import design_patterns.model.Product;
import design_patterns.adapter.ExternalPaymentService;
import design_patterns.adapter.PaymentAdapter;
import design_patterns.decorator.WarrantyDecorator;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;

public class StoreGUI extends JFrame {
    private CartManager cartManager;
    private JLabel totalLabel;
    private JLabel productDetailsLabel;
    private ProductFactory laptopFactory;
    private ProductFactory smartphoneFactory;
    private ProductFactory smartwatchFactory; 
    private ProductFactory tabletFactory;       
    private JTable productTable;
    private DefaultTableModel productTableModel;

    public StoreGUI() {
        cartManager = CartManager.getInstance();
        laptopFactory = new LaptopFactory();
        smartphoneFactory = new SmartphoneFactory();
        smartwatchFactory = new SmartwatchFactory(); 
        tabletFactory = new TabletFactory();         
        setupGUI();
    }

    private class NonEditableTableModel extends DefaultTableModel {
        public NonEditableTableModel(Object[] columnNames, int rowCount) {
            super(columnNames, rowCount);
        }

        @Override
        public boolean isCellEditable(int row, int column) {
            return false; // Deshabilitar edición
        }
    }
    
    private void setupGUI() {
        setTitle("Tienda Online de Electrónicos");
        setSize(600, 500);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());
        setLocationRelativeTo(null);

        // Establecer colores y estilos
        UIManager.put("Panel.background", new Color(249, 249, 249));
        UIManager.put("Button.background", new Color(76, 175, 80));
        UIManager.put("Button.foreground", Color.WHITE);
        UIManager.put("Label.foreground", new Color(51, 51, 51));
        UIManager.put("TextArea.background", Color.WHITE);
        UIManager.put("TextArea.foreground", new Color(51, 51, 51));

        Font font = new Font("Arial", Font.PLAIN, 14);
        UIManager.put("Button.font", font);
        UIManager.put("Label.font", font);
        UIManager.put("TextArea.font", font);
        UIManager.put("TextField.font", font);

        // Panel de selección de productos
        JPanel productPanel = new JPanel();
        productPanel.setLayout(new GridLayout(5, 2, 10, 10));
        productPanel.setBorder(BorderFactory.createTitledBorder("Seleccione un producto"));

        JButton laptopButton = new JButton("Agregar Laptop");
        JButton smartphoneButton = new JButton("Agregar Smartphone");
        JButton smartwatchButton = new JButton("Agregar Smartwatch"); 
        JButton tabletButton = new JButton("Agregar Tablet");         

        laptopButton.addActionListener(e -> showProductDetails(laptopFactory.createProduct()));
        smartphoneButton.addActionListener(e -> showProductDetails(smartphoneFactory.createProduct()));
        smartwatchButton.addActionListener(e -> showProductDetails(smartwatchFactory.createProduct())); 
        tabletButton.addActionListener(e -> showProductDetails(tabletFactory.createProduct()));             

        productPanel.add(new JLabel("Seleccione un producto:"));
        productPanel.add(new JLabel("")); // Espacio vacío
        productPanel.add(laptopButton);
        productPanel.add(smartphoneButton);
        productPanel.add(smartwatchButton); 
        productPanel.add(tabletButton);       

        // Área de visualización del carrito
        String[] columnNames = {"Producto", "Precio", "Cantidad", "Total"};
        productTableModel = new NonEditableTableModel(columnNames, 0); // Usar modelo no editable
        productTable = new JTable(productTableModel);
        JScrollPane productScrollPane = new JScrollPane(productTable);
        productScrollPane.setBorder(BorderFactory.createTitledBorder("Productos en el carrito"));

        // Panel de pago y total
        JPanel checkoutPanel = new JPanel();
        checkoutPanel.setLayout(new BorderLayout());

        JButton checkoutButton = new JButton("Procesar Pago");
        checkoutButton.addActionListener(e -> processPayment());

        JButton clearCartButton = new JButton("Vaciar Carrito");
        clearCartButton.addActionListener(e -> clearCart());

        JButton removeProductButton = new JButton("Eliminar Producto");
        removeProductButton.addActionListener(e -> removeProduct());

        totalLabel = new JLabel("Total: $0.0");
        totalLabel.setFont(new Font("Arial", Font.BOLD, 16));

        checkoutPanel.add(totalLabel, BorderLayout.NORTH);
        checkoutPanel.add(checkoutButton, BorderLayout.WEST);
        checkoutPanel.add(removeProductButton, BorderLayout.CENTER);
        checkoutPanel.add(clearCartButton, BorderLayout.EAST);
        checkoutPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        // Añadir componentes a la ventana principal
        add(productPanel, BorderLayout.NORTH);
        add(productScrollPane, BorderLayout.CENTER);
        add(checkoutPanel, BorderLayout.SOUTH);
    }

    private void showProductDetails(Product product) {
    String details = "<html><strong>Nombre:</strong> " + product.getName() + "<br>" +
                     "<strong>Precio:</strong> $" + product.getPrice() + "<br>" +
                     "<strong>Descripción:</strong> " + product.getDescription() + "</html>";

    // Crear un campo para ingresar la cantidad
    JSpinner quantitySpinner = new JSpinner(new SpinnerNumberModel(1, 1, 100, 1)); // 1 es el mínimo, 100 es el máximo

    // Crear un checkbox para la garantía extendida
    JCheckBox warrantyCheckBox = new JCheckBox("Agregar garantía extendida (+$100)");

    // Mostrar el diálogo con detalles del producto, campo de cantidad y checkbox de garantía
    Object[] message = {
        details,
        "Cantidad:", quantitySpinner,
        warrantyCheckBox
    };

    int result = JOptionPane.showConfirmDialog(this, message, "Detalles del Producto", JOptionPane.OK_CANCEL_OPTION);
    if (result == JOptionPane.OK_OPTION) {
        int quantity = (Integer) quantitySpinner.getValue(); // Obtener la cantidad ingresada
        if (warrantyCheckBox.isSelected()) {
            // Aplicar el decorador de garantía extendida si está seleccionado
            product = new WarrantyDecorator(product);
        }
        product.setQuantity(quantity); // Supone que tienes un método setQuantity en Product
        cartManager.addProduct(product);
        updateCartDisplay();
        JOptionPane.showMessageDialog(this, product.getName() + " ha sido agregado al carrito.");
    }
}

    

private void updateCartDisplay() {
    productTableModel.setRowCount(0); // Limpiar la tabla actual

    double total = 0.0;

    for (Product product : cartManager.getProducts()) {
        total += product.getTotalPrice(); // Usar el precio total con la garantía incluida, si aplica
        productTableModel.addRow(new Object[]{product.getName(), product.getPrice(), product.getQuantity(), product.getTotalPrice()});
    }

    totalLabel.setText("Total: $" + total);
}


    private void removeProduct() {
        String productName = JOptionPane.showInputDialog(this, "Ingrese el nombre del producto a eliminar:");
    
        if (productName != null && !productName.trim().isEmpty()) {
            Product productToRemove = null;
            for (Product product : cartManager.getProducts()) {
                if (product.getName().equalsIgnoreCase(productName)) {
                    productToRemove = product;
                    break;
                }
            }
    
            if (productToRemove != null) {
                String[] options = {"Eliminar toda la cantidad", "Eliminar cantidad específica"};
                int choice = JOptionPane.showOptionDialog(this, "¿Qué desea hacer?", "Eliminar Producto",
                        JOptionPane.DEFAULT_OPTION, JOptionPane.QUESTION_MESSAGE, null, options, options[0]);
    
                if (choice == 0) {
                    // Opción 1: Eliminar toda la cantidad
                    cartManager.removeProduct(productName);
                    JOptionPane.showMessageDialog(this, productName + " ha sido eliminado del carrito.");
                } else if (choice == 1) {
                    // Opción 2: Eliminar cantidad específica
                    String quantityInput = JOptionPane.showInputDialog(this, "Ingrese la cantidad a eliminar:");
                    try {
                        int quantityToRemove = Integer.parseInt(quantityInput);
                        if (quantityToRemove > 0 && quantityToRemove <= productToRemove.getQuantity()) {
                            productToRemove.setQuantity(productToRemove.getQuantity() - quantityToRemove);
                            if (productToRemove.getQuantity() == 0) {
                                cartManager.removeProduct(productName); // Si la cantidad llega a cero, eliminamos el producto
                            }
                            updateCartDisplay();
                            JOptionPane.showMessageDialog(this, "Se han eliminado " + quantityToRemove + " de " + productName + " del carrito.");
                        } else {
                            JOptionPane.showMessageDialog(this, "Cantidad no válida.", "Error", JOptionPane.ERROR_MESSAGE);
                        }
                    } catch (NumberFormatException e) {
                        JOptionPane.showMessageDialog(this, "Entrada no válida. Debe ser un número entero.", "Error", JOptionPane.ERROR_MESSAGE);
                    }
                }
                updateCartDisplay(); // Actualiza la visualización del carrito
            } else {
                JOptionPane.showMessageDialog(this, "No se encontró el producto: " + productName);
            }
        } else {
            JOptionPane.showMessageDialog(this, "No se ingresó un nombre de producto válido.");
        }
    }
    

    private void processPayment() {
        double total = cartManager.getTotal(); // Usa el método que ya considera los precios totales
    
        if (total > 0) {
            try {
                PaymentAdapter paymentAdapter = new PaymentAdapter(new ExternalPaymentService());
                paymentAdapter.pay(total);
                JOptionPane.showMessageDialog(this, "Pago de $" + total + " procesado exitosamente.");
                cartManager.clearCart(); // Limpiar el carrito después del pago
                updateCartDisplay();
            } catch (Exception e) {
                JOptionPane.showMessageDialog(this, "Error al procesar el pago: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            }
        } else {
            JOptionPane.showMessageDialog(this, "El carrito está vacío.");
        }
    }
    

    private void clearCart() {
        cartManager.clearCart();
        updateCartDisplay(); // Actualiza la visualización del carrito
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            StoreGUI storeGUI = new StoreGUI();
            storeGUI.setVisible(true);
        });
    }
}
