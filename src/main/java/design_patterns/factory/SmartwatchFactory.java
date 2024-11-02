package design_patterns.factory;

import design_patterns.model.Smartwatch;

public class SmartwatchFactory implements ProductFactory {
    @Override
    public Smartwatch createProduct() {
        return new Smartwatch("Apple Watch", 500.0); // Proporciona valores predeterminados
    }
}
