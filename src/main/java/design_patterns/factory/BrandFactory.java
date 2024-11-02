package design_patterns.factory;

import design_patterns.model.Product;

public interface BrandFactory {
    Product createLaptop();
    Product createSmartphone();
    Product createTablet();
    Product createSmartwatch();
    
}