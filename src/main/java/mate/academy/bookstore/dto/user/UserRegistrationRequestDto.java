package mate.academy.bookstore.dto.user;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import mate.academy.bookstore.validation.FieldMatch;
import mate.academy.bookstore.validation.Password;

@Data
@FieldMatch(
        field = "password",
        fieldMatch = "repeatPassword",
        message = "Passwords mismatch!"
)
public class UserRegistrationRequestDto {
    @Email
    @Size(min = 8, max = 50)
    private String email;
    @NotBlank
    @Size(min = 8, max = 20)
    @Password
    private String password;
    private String repeatPassword;
    @NotBlank
    @Size(min = 2, max = 35)
    private String firstName;
    @Size(min = 2, max = 35)
    private String lastName;
    @NotBlank
    @Size(min = 5, max = 100)
    private String shippingAddress;
}
