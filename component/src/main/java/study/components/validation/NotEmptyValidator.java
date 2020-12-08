package study.components.validation;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.Collection;

/**
 *   Works only with String and Collection.
 *   Return true for null values.
 */
public class NotEmptyValidator implements ConstraintValidator<NotEmpty, Object> {

    @Override
    public void initialize(NotEmpty constraintAnnotation) {
    }

    @Override
    public boolean isValid(Object value, ConstraintValidatorContext context) {
        if (value == null) return true;

        if (value instanceof String) {
            return !((String) value).trim().isEmpty();
        } else if (value instanceof Collection) {
            return !((Collection<?>) value).isEmpty();
        }
        return true;
    }
}
