package design_patterns.model;

public class Tablet extends Product {
    public Tablet() {
        super("Tablet Multifuncional", 500.0, "Tablet con pantalla de alta definición, perfecta para ver películas, leer libros y trabajar sobre la marcha, con una amplia gama de aplicaciones disponibles.");
    }

    public Tablet(String name, double price) {
        super(name, price, "Tablet personalizada que ofrece un rendimiento mejorado y características específicas para adaptarse a las necesidades del usuario.");
    }
}
