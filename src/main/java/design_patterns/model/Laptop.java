package design_patterns.model;

public class Laptop extends Product {
    public Laptop() {
        super("Laptop Estándar", 1000.0, "Laptop diseñada para uso general, ideal para trabajo y entretenimiento, con un rendimiento equilibrado y una batería de larga duración.");
    }

    public Laptop(String name, double price) {
        super(name, price, "Laptop personalizada con características específicas, ideal para satisfacer las necesidades del usuario.");
    }
}
