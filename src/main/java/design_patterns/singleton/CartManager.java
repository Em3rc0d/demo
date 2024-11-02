package design_patterns.singleton;

import design_patterns.model.Product;
import java.util.ArrayList;
import java.util.List;

public class CartManager {
    private List<Product> products = new ArrayList<>();

    private CartManager() {}

    private static class InstanceHolder {
        private static final CartManager INSTANCE = new CartManager();
    }

    public static CartManager getInstance() {
        return InstanceHolder.INSTANCE;
    }

    public void addProduct(Product product) {
        for (Product p : products) {
            if (p.getName().equalsIgnoreCase(product.getName())) {
                p.incrementQuantity(); // Increment quantity if product already exists
                return;
            }
        }
        products.add(product); // Add new product if it doesn't exist
    }

    public List<Product> getProducts() {
        return new ArrayList<>(products); 
    }

    public void clearCart() {
        products.clear();
    }

    public double getTotal() {
        return products.stream().mapToDouble(Product::getTotalPrice).sum(); // Calculate total based on quantity
    }

    public boolean removeProduct(String productName) {
        return products.removeIf(product -> product.getName().equalsIgnoreCase(productName));
    }

    @Override
    protected Object clone() throws CloneNotSupportedException {
        throw new CloneNotSupportedException("Clonación no permitida.");
    }

    @Override
    public String toString() {
        return "CartManager{" +
                "products=" + products +
                '}';
    }

    private Object readResolve() {
        return getInstance();  
    }
}
