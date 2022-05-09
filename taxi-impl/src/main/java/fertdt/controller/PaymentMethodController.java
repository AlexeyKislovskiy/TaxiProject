package fertdt.controller;

import fertdt.api.PaymentMethodApi;
import fertdt.dto.response.PaymentMethodResponse;
import fertdt.service.PaymentMethodService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@RestController
@RequiredArgsConstructor
public class PaymentMethodController implements PaymentMethodApi {
    private final PaymentMethodService paymentMethodService;

    @Override
    public PaymentMethodResponse getPaymentMethod(UUID paymentMethodId) {
        return paymentMethodService.getPaymentMethodById(paymentMethodId);
    }
}
