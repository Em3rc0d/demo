package design_patterns.GUI;

import design_patterns.singleton.CartManager;
import design_patterns.factory.*;
import design_patterns.model.Product;
import design_patterns.adapter.ExternalPaymentService;
import design_patterns.adapter.PaymentAdapter;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class StoreGUI extends JFrame {
    private CartManager cartManager;
    private JTextArea cartDisplay;
    private JLabel totalLabel;
    private JLabel productDetailsLabel;
    private ProductFactory laptopFactory;
    private ProductFactory smartphoneFactory;

    public StoreGUI() {
        cartManager = CartManager.getInstance();
        laptopFactory = new LaptopFactory();
        smartphoneFactory = new SmartphoneFactory();
        setupGUI();
    }

    private void setupGUI() {
        setTitle("Tienda Online de Electrónicos");
        setSize(600, 500);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());
        setLocationRelativeTo(null);

        // Panel de selección de productos
        JPanel productPanel = new JPanel();
        productPanel.setLayout(new GridLayout(3, 2, 10, 10));
        productPanel.setBorder(BorderFactory.createTitledBorder("Seleccione un producto"));

        JButton laptopButton = new JButton("Agregar Laptop");
        JButton smartphoneButton = new JButton("Agregar Smartphone");

        laptopButton.addActionListener(e -> showProductDetails(laptopFactory.createProduct()));
        smartphoneButton.addActionListener(e -> showProductDetails(smartphoneFactory.createProduct()));

        productPanel.add(new JLabel("Seleccione un producto:"));
        productPanel.add(new JLabel(""));
        productPanel.add(laptopButton);
        productPanel.add(smartphoneButton);

        // Área de visualización del carrito
        cartDisplay = new JTextArea();
        cartDisplay.setEditable(false);
        JScrollPane scrollPane = new JScrollPane(cartDisplay);
        scrollPane.setBorder(BorderFactory.createTitledBorder("Productos en el carrito"));

        // Panel de detalles del producto
        productDetailsLabel = new JLabel("Seleccione un producto para ver los detalles.");
        JPanel productDetailsPanel = new JPanel(new BorderLayout());
        productDetailsPanel.setBorder(BorderFactory.createTitledBorder("Detalles del Producto"));
        productDetailsPanel.add(productDetailsLabel, BorderLayout.CENTER);

        // Panel de pago y total
        JPanel checkoutPanel = new JPanel();
        checkoutPanel.setLayout(new BorderLayout());
        
        JButton checkoutButton = new JButton("Procesar Pago");
        checkoutButton.addActionListener(e -> processPayment());

        JButton clearCartButton = new JButton("Vaciar Carrito");
        clearCartButton.addActionListener(e -> clearCart());

        totalLabel = new JLabel("Total: $0.0");
        totalLabel.setFont(new Font("Arial", Font.BOLD, 16));

        checkoutPanel.add(totalLabel, BorderLayout.NORTH);
        checkoutPanel.add(checkoutButton, BorderLayout.WEST);
        checkoutPanel.add(clearCartButton, BorderLayout.EAST);
        checkoutPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        // Añadir componentes a la ventana principal
        add(productPanel, BorderLayout.NORTH);
        add(productDetailsPanel, BorderLayout.WEST);
        add(scrollPane, BorderLayout.CENTER);
        add(checkoutPanel, BorderLayout.SOUTH);
    }

    private void showProductDetails(Product product) {
        String details = "<html><strong>Nombre:</strong> " + product.getName() + "<br>" +
                         "<strong>Precio:</strong> $" + product.getPrice() + "<br>" +
                         "<strong>Descripción:</strong> " + product.getDescription() + "</html>";
        productDetailsLabel.setText(details);
        // Botón para agregar el producto al carrito
        int result = JOptionPane.showConfirmDialog(this, details + "\n\n¿Deseas agregar este producto al carrito?", 
                                                   "Detalles del Producto", JOptionPane.YES_NO_OPTION);
        if (result == JOptionPane.YES_OPTION) {
            cartManager.addProduct(product);
            updateCartDisplay();
        }
    }

    private void updateCartDisplay() {
        StringBuilder displayText = new StringBuilder("Productos en el carrito:\n");
        double total = 0.0;

        for (Product product : cartManager.getProducts()) {
            displayText.append(product.getName()).append(" - $").append(product.getPrice()).append("\n");
            total += product.getPrice();
        }

        cartDisplay.setText(displayText.toString());
        totalLabel.setText("Total: $" + total);
    }

    private void processPayment() {
        double total = cartManager.getProducts().stream().mapToDouble(Product::getPrice).sum();

        if (total > 0) {
            PaymentAdapter paymentAdapter = new PaymentAdapter(new ExternalPaymentService());
            paymentAdapter.pay(total);
            JOptionPane.showMessageDialog(this, "Pago de $" + total + " procesado exitosamente.");
            cartManager.getProducts().clear();
            updateCartDisplay();
        } else {
            JOptionPane.showMessageDialog(this, "El carrito está vacío.");
        }
    }

    private void clearCart() {
        cartManager.getProducts().clear();
        updateCartDisplay();
        JOptionPane.showMessageDialog(this, "El carrito ha sido vaciado.");
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            StoreGUI storeGUI = new StoreGUI();
            storeGUI.setVisible(true);
        });
    }
}
