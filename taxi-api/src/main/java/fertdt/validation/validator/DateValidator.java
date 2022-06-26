package fertdt.validation.validator;

import fertdt.validation.annotation.Date;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.time.format.ResolverStyle;

public class DateValidator implements ConstraintValidator<Date, String> {
    private DateTimeFormatter formatter;
    private String pattern;
    private LocalDate min;
    private LocalDate max;

    @Override
    public void initialize(Date constraintAnnotation) {
        pattern = constraintAnnotation.pattern();
        formatter = DateTimeFormatter.ofPattern(pattern).withResolverStyle(ResolverStyle.STRICT);
        min = LocalDate.parse(constraintAnnotation.min(), formatter);
        max = LocalDate.parse(constraintAnnotation.max(), formatter);
    }

    @Override
    public boolean isValid(String s, ConstraintValidatorContext constraintValidatorContext) {
        if (s == null) return false;
        try {
            LocalDate localDate = LocalDate.parse(s, formatter);
            return !localDate.isBefore(min) && !localDate.isAfter(max);
        } catch (DateTimeParseException e) {
            return false;
        }
    }
}
