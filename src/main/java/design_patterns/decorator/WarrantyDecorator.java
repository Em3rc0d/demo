package design_patterns.decorator;

import design_patterns.model.Product;

public class WarrantyDecorator extends ProductDecorator {
    public WarrantyDecorator(Product product) {
        super(product);
    }

    @Override
    public double getPrice() {
        return product.getPrice() + 100.0;
    }
}
