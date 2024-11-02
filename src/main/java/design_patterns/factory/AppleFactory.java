package design_patterns.factory;

import design_patterns.model.Laptop;
import design_patterns.model.Smartphone;
import design_patterns.model.Smartwatch;
import design_patterns.model.Tablet;

public class AppleFactory implements BrandFactory {
    private final ProductFactory laptopFactory;
    private final ProductFactory smartphoneFactory;
    private final ProductFactory tabletFactory;
    private final ProductFactory smartwatchFactory;

    public AppleFactory() {
        this.laptopFactory = new LaptopFactory();
        this.smartphoneFactory = new SmartphoneFactory();
        this.tabletFactory = new TabletFactory();
        this.smartwatchFactory = new SmartwatchFactory();
    }

    @Override
    public Laptop createLaptop() {
        return (Laptop) laptopFactory.createProduct();
    }

    @Override
    public Smartphone createSmartphone() {
        return (Smartphone) smartphoneFactory.createProduct();
    }

    @Override
    public Tablet createTablet() {
        return (Tablet) tabletFactory.createProduct();
    }

    @Override
    public Smartwatch createSmartwatch() {
        return (Smartwatch) smartwatchFactory.createProduct();
    }
}
