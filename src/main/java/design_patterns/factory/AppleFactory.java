package design_patterns.factory;

import design_patterns.model.Laptop;
import design_patterns.model.Smartphone;

public class AppleFactory implements BrandFactory {
    @Override
    public Laptop createLaptop() {
        return new Laptop("Apple MacBook Pro", 2000.0);
    }

    @Override
    public Smartphone createSmartphone() {
        return new Smartphone("iPhone", 1200.0);
    }
}