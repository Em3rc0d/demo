package design_patterns.model;

public class Product {
    private String name;
    private double price;
    private String description;
    private int quantity ; // Nuevo campo

    public Product(String name, double price, String description) {
        if (name == null || name.isEmpty()) {
            throw new IllegalArgumentException("El nombre del producto no puede estar vacío.");
        }
        if (price < 0) {
            throw new IllegalArgumentException("El precio no puede ser negativo.");
        }
        this.name = name;
        this.price = price;
        this.description = description;
    }

    public Product(String name2, double price2, int quantity) {
        this.name = name2;
        this.price = price2;
        this.quantity = quantity;
    }

    public String getName() {
        return name;
    }

    public double getPrice() {
        return price;
    }

    public String getDescription() {
        return description;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public void incrementQuantity() { // Method to increase quantity
        this.quantity++;
    }

    public double getTotalPrice() {
        return price * quantity; // Método para calcular el precio total basado en la cantidad
    }

    @Override
    public String toString() {
        return name + " - $" + price + " (Cantidad: " + quantity + ")" + " - " + "monto total: $" + getTotalPrice();
    }
}
