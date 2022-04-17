package fertdt.validation.annotation;

import fertdt.validation.validator.ValueFromArrayValidator;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

@Documented
@Constraint(validatedBy = ValueFromArrayValidator.class)
@Target({ElementType.METHOD, ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
public @interface ValueFromArray {
    String[] possibleValues();

    String message() default "Value should be from {possibleValues}";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
