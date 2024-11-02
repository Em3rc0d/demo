package design_patterns.model;

public class Smartphone extends Product {
    public Smartphone() {
        super("Smartphone Avanzado", 500.0, "Smartphone con capacidades multimedia, diseño elegante y funcionalidades inteligentes, perfecto para mantenerse conectado en cualquier lugar.");
    }

    public Smartphone(String name, double price) {
        super(name, price, "Smartphone personalizado con características avanzadas para un rendimiento óptimo en comunicación y entretenimiento.");
    }
}
