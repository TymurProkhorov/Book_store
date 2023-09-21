package mate.academy.bookstore.validation;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import java.util.regex.Pattern;

public class PasswordValidator implements ConstraintValidator<Password, String> {
    public static final String PASSWORD_PATTERN =
            "^(?=.*[a-zA-Z])(?=.*\\d)[a-zA-Z\\d]{8,20}$";

    @Override
    public boolean isValid(
            String password, ConstraintValidatorContext constraintValidatorContext) {
        return password != null
                && Pattern.compile(PASSWORD_PATTERN).matcher(password).matches();
    }
}
