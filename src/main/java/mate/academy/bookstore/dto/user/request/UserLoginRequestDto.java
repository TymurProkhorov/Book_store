package mate.academy.bookstore.dto.user.request;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Size;
import lombok.Data;
import mate.academy.bookstore.validation.Password;

@Data
public class UserLoginRequestDto {
    @Size(min = 8, max = 100)
    @Email
    private String email;
    @Password
    private String password;
}
