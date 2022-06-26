package fertdt.util.mapper;

import fertdt.dto.response.PaymentMethodResponse;
import fertdt.model.PaymentMethodEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Named;

import java.util.UUID;

@Mapper(componentModel = "spring")
public interface PaymentMethodMapper {
    PaymentMethodResponse toResponse(PaymentMethodEntity paymentMethodEntity);

    @Named("toPaymentMethodEntityById")
    default PaymentMethodEntity toPaymentMethodEntityById(UUID id) {
        return PaymentMethodEntity.builder().uuid(id).build();
    }
}
