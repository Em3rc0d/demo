package design_patterns.factory;

import design_patterns.model.Laptop;

public class LaptopFactory implements ProductFactory {
    @Override
    public Laptop createProduct() {
        return new Laptop("Laptop", 1000.0); // Proporciona valores predeterminados
    }
}
