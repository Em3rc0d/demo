package design_patterns.decorator;

import design_patterns.model.Product;

public abstract class ProductDecorator extends Product {
    protected Product product;

    public ProductDecorator(Product product) {
        super(product.getName(), product.getPrice(), product.getDescription());
        this.product = product;
    }

    public abstract double getPrice();
}
