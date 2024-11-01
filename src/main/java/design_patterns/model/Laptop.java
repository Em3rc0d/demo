package design_patterns.model;

public class Laptop extends Product {
    public Laptop() {
        super("Laptop genérica", 1000.0, "Laptop estándar sin detalles específicos.");
    }

    public Laptop(String name, double price) {
        super(name, price, "Laptop con detalles específicos.");
    }
}
