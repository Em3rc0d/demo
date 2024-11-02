package design_patterns.decorator;

import design_patterns.model.Product;

public class WarrantyDecorator extends Product {
    private Product product;

    public WarrantyDecorator(Product product) {
        this.product = product;
    }

    @Override
    public String getName() {
        return product.getName() + " (con garantía extendida)";
    }

    @Override
    public String getDescription() {
        return product.getDescription() + " + Garantía extendida de 1 año";
    }

    @Override
    public double getPrice() {
        return product.getPrice() + 100; // Añade $100 al precio original
    }

    @Override
    public int getQuantity() {
        return product.getQuantity();
    }

    @Override
    public void setQuantity(int quantity) {
        product.setQuantity(quantity);
    }

    @Override
    public double getTotalPrice() {
        return getPrice() * getQuantity(); // Calcula el total basado en el precio con garantía
    }
}
