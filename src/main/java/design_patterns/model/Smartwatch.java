package design_patterns.model;

public class Smartwatch extends Product {
    public Smartwatch() {
        super("Smartwatch Deportivo", 500.0, "Smartwatch diseñado para monitorear la actividad física y la salud, con notificaciones inteligentes y un diseño moderno.");
    }

    public Smartwatch(String name, double price) {
        super(name, price, "Smartwatch personalizado que combina estilo y funcionalidad, ideal para un estilo de vida activo.");
    }
}
