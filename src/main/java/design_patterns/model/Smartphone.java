package design_patterns.model;

public class Smartphone extends Product {
    public Smartphone() {
        super("Smartphone genérico", 500.0, "Smartphone estándar sin detalles específicos.");
    }

    public Smartphone(String name, double price) {
        super(name, price, "Smartphone con detalles específicos.");
    }
}
