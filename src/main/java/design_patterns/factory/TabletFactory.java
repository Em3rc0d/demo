package design_patterns.factory;

import design_patterns.model.Tablet;

public class TabletFactory implements ProductFactory {
    @Override
    public Tablet createProduct() {
        return new Tablet("iPad", 800.0); // Proporciona valores predeterminados
    }
}
