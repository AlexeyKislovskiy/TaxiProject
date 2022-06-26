package fertdt.exception.notFound;

public class PaymentMethodNotFoundException extends NotFoundException {
    public PaymentMethodNotFoundException() {
        super();
        this.message = "Payment method not found";
    }
}
