package design_patterns.GUI;

import design_patterns.singleton.CartManager;
import design_patterns.factory.*;
import design_patterns.builder.ProductBuilder;
import design_patterns.adapter.ExternalPaymentService;
import design_patterns.adapter.PaymentAdapter;
import design_patterns.decorator.WarrantyDecorator;
import design_patterns.model.Product;

public class OnlineStoreApp {
    public static void main(String[] args) {
        // Singleton: Crear instancia única de Carrito
        CartManager cart = CartManager.getInstance();

        // Builder: Construir producto específico
        Product customLaptop = new ProductBuilder()
                .setName("Gaming Laptop")
                .setPrice(1500.0)
                .setDescription("Laptop para juegos de alto rendimiento")
                .build();
        
        // Factory Method: Crear productos a través de fábricas
        ProductFactory laptopFactory = new LaptopFactory();
        Product laptop = laptopFactory.createProduct();

        // Abstract Factory: Crear productos de una marca específica
        BrandFactory appleFactory = new AppleFactory();
        Product appleLaptop = appleFactory.createLaptop();
        
        // Adapter: Procesar pago con un servicio externo
        ExternalPaymentService externalService = new ExternalPaymentService();
        PaymentAdapter paymentAdapter = new PaymentAdapter(externalService);

        // Decorator: Agregar garantía al producto
        Product laptopWithWarranty = new WarrantyDecorator(laptop);

        // Agregar productos al carrito y mostrar
        cart.addProduct(customLaptop);
        cart.addProduct(laptopWithWarranty);
        cart.getProducts().forEach(p -> System.out.println(p.getName() + ": $" + p.getPrice()));

        // Procesar pago total
        double total = cart.getProducts().stream().mapToDouble(Product::getPrice).sum();
        paymentAdapter.pay(total);
    }
}
