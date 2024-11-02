package design_patterns.adapter;

public class PaymentAdapter implements Payment {
    private ExternalPaymentService paymentService;

    public PaymentAdapter(ExternalPaymentService service) {
        this.paymentService = service;
    }

    @Override
    public void pay(double amount) {
        paymentService.processPayment(amount);
    }
}
