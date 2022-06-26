package fertdt.annotation;

import fertdt.dto.response.ExceptionResponse;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

import java.lang.annotation.*;

@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
@ApiResponses(value = {
        @ApiResponse(code = 401, message = "Unauthorized. Возможные проблемы: срок действия токена истек, токен некорректный",
                response = ExceptionResponse.class),
        @ApiResponse(code = 403, message = "Forbidden. Возможные проблемы: не передан access токен," +
                " нет доступа к данному ресурсу", response = ExceptionResponse.class)
}
)
public @interface GlobalApiResponses {
}
