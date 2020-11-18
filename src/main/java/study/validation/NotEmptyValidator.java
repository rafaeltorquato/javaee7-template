package study.validation;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.List;

public class NotEmptyValidator implements ConstraintValidator<NotEmpty, Object> {

    @Override
    public void initialize(NotEmpty constraintAnnotation) {
    }

    @Override
    public boolean isValid(Object value, ConstraintValidatorContext context) {
        if (value == null) return false;

        if (value instanceof String) {
            return !((String) value).trim().isEmpty();
        } else if (value instanceof List) {
            return !((List<?>) value).isEmpty();
        }
        return true;
    }
}
