package design_patterns.adapter;

public class PaymentAdapter {
    private ExternalPaymentService paymentService;

    public PaymentAdapter(ExternalPaymentService service) {
        this.paymentService = service;
    }

    public void pay(double amount) {
        paymentService.processPayment(amount);
    }
}