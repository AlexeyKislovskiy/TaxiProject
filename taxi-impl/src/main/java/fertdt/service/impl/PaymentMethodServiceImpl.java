package fertdt.service.impl;

import fertdt.dto.response.PaymentMethodResponse;
import fertdt.exception.notFound.PaymentMethodNotFoundException;
import fertdt.repository.PaymentMethodRepository;
import fertdt.service.PaymentMethodService;
import fertdt.util.mapper.PaymentMethodMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class PaymentMethodServiceImpl implements PaymentMethodService {
    private final PaymentMethodRepository paymentMethodRepository;
    private final PaymentMethodMapper paymentMethodMapper;

    @Override
    public PaymentMethodResponse getPaymentMethodById(UUID paymentMethodId) {
        return paymentMethodMapper.toResponse(
                paymentMethodRepository.findById(paymentMethodId).orElseThrow(PaymentMethodNotFoundException::new)
        );
    }
}
