package fertdt.api;

import fertdt.dto.response.PaymentMethodResponse;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.util.UUID;

import static org.springframework.util.MimeTypeUtils.APPLICATION_JSON_VALUE;

@RequestMapping("/api/payment-methods")
public interface PaymentMethodApi {
    @GetMapping(value = "/{payment-method-id}", produces = APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
    PaymentMethodResponse getPaymentMethod(@PathVariable("payment-method-id") UUID paymentMethodId);
}
