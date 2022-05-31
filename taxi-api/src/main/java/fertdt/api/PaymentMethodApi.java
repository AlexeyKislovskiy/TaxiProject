package fertdt.api;

import fertdt.dto.response.ExceptionResponse;
import fertdt.dto.response.PaymentMethodResponse;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.util.UUID;

import static org.springframework.util.MimeTypeUtils.APPLICATION_JSON_VALUE;

@RequestMapping("/api/payment-methods")
public interface PaymentMethodApi {
    @ApiOperation(value = "Получить информацию о способе платежа по его ID")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "OK. Способ платежа успешно получен", response = PaymentMethodResponse.class),
            @ApiResponse(code = 404, message = "Not Found. Способ платежа с таким ID не найден", response = ExceptionResponse.class)
    }
    )
    @GetMapping(value = "/{payment-method-id}", produces = APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
    PaymentMethodResponse getPaymentMethod(@ApiParam(value = "ID способа платежа", example = "3fa85f64-5717-4562-b3fc-2c963f66afa6")
                                           @PathVariable("payment-method-id") UUID paymentMethodId);
}
