package fertdt.validation.annotation;

import fertdt.validation.validator.WordsNumberValidator;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

@Documented
@Constraint(validatedBy = WordsNumberValidator.class)
@Target({ElementType.METHOD, ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
public @interface WordsNumber {
    int min() default 1;

    int max() default Integer.MAX_VALUE;

    String message() default "Number of words should be between {min} and {max}";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
