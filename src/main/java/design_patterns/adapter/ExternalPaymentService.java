package design_patterns.adapter;

public class ExternalPaymentService {
    public void processPayment(double amount) {
        System.out.println("Procesando pago de $" + amount + " en el servicio externo.");
    }
}