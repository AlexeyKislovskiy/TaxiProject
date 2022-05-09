package fertdt.validation.validator;

import fertdt.validation.annotation.WordsNumber;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class WordsNumberValidator implements ConstraintValidator<WordsNumber, String> {
    private Integer min;
    private Integer max;

    @Override
    public void initialize(WordsNumber constraintAnnotation) {
        min = constraintAnnotation.min();
        max = constraintAnnotation.max();
    }

    @Override
    public boolean isValid(String s, ConstraintValidatorContext constraintValidatorContext) {
        if (s == null) return false;
        String[] words = s.strip().split(" ");
        return words.length >= min && words.length <= max;
    }
}
