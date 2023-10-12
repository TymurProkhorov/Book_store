package mate.academy.bookstore.dto.user;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Size;
import mate.academy.bookstore.validation.Password;

public record UserLoginRequestDto(
        @Size(min = 7, max = 25)
        @Email
        String email,
        @Password
        String password
) {
}
