package fertdt.service;

import fertdt.dto.response.PaymentMethodResponse;

import java.util.UUID;

public interface PaymentMethodService {
    PaymentMethodResponse getPaymentMethodById(UUID paymentMethodId);
}
