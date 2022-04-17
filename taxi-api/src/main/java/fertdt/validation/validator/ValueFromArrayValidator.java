package fertdt.validation.validator;

import fertdt.validation.annotation.ValueFromArray;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class ValueFromArrayValidator implements ConstraintValidator<ValueFromArray, String> {
    private String[] possibleValues;

    @Override
    public void initialize(ValueFromArray constraintAnnotation) {
        possibleValues = constraintAnnotation.possibleValues();
    }

    @Override
    public boolean isValid(String s, ConstraintValidatorContext constraintValidatorContext) {
        if (s == null) return false;
        for (String value : possibleValues) {
            if (s.equals(value)) return true;
        }
        return false;
    }
}
