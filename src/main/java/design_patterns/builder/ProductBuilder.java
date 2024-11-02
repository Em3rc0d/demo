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
        validateProductData();
        Product product = new Product(name, price, description);
        reset(); // Reinicia el builder para su reutilización
        return product;
    }

    private void validateProductData() {
        if (name == null || name.isEmpty()) {
            throw new IllegalArgumentException("El nombre del producto no puede estar vacío.");
        }
        if (price < 0) {
            throw new IllegalArgumentException("El precio no puede ser negativo.");
        }
        if(description == null || description.isEmpty()) {
            throw new IllegalArgumentException("La descripción del producto no puede estar vacío.");
        }
        if(description.length() > 100) {
            throw new IllegalArgumentException("La descripción del producto no puede exceder los 100 caracteres.");
        }
        if(description.length() < 10) {
            throw new IllegalArgumentException("La descripción del producto debe tener al menos 10 caracteres.");
        }
    }

    public void reset() {
        this.name = null;
        this.price = 0.0;
        this.description = null;
    }
}
