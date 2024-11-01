package design_patterns.builder;

import design_patterns.model.Product;

public class ProductBuilder {
    private String name;
    private double price;
    private String description;

    public ProductBuilder setName(String name) {
        this.name = name;
        return this;
    }

    public ProductBuilder setPrice(double price) {
        this.price = price;
        return this;
    }

    public ProductBuilder setDescription(String description) {
        this.description = description;
        return this;
    }

    public Product build() {
        return new Product(name, price, description);
    }
}