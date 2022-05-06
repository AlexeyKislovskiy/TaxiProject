package fertdt.util.mapper;

import fertdt.dto.request.RentedCarUsingRequest;
import fertdt.dto.response.PaymentForCarUsingResponse;
import fertdt.model.CarUsingEntity;
import fertdt.model.PaymentForCarUsingEntity;
import fertdt.model.PaymentStatus;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring", imports = {PaymentStatus.class})
public interface PaymentForCarUsingMapper {
    PaymentForCarUsingResponse toResponse(PaymentForCarUsingEntity paymentForCarUsingEntity);

    @Mapping(target = "uuid", ignore = true)
    @Mapping(target = "createDate", ignore = true)
    @Mapping(target = "carUsing", source = "carUsingEntity")
    @Mapping(target = "rentedForDays", source = "carUsingRequest.rentedForDays")
    @Mapping(target = "cost", expression = "java(carUsingEntity.getCar().getDailyRentalPrice()*carUsingRequest.getRentedForDays())")
    @Mapping(target = "paymentStatus", expression = "java(PaymentStatus.NOT_PAID)")
    PaymentForCarUsingEntity toEntity(CarUsingEntity carUsingEntity, RentedCarUsingRequest carUsingRequest);
}
