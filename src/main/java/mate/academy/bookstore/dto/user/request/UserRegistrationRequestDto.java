package mate.academy.bookstore.dto.user.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import mate.academy.bookstore.validation.FieldMatch;
import mate.academy.bookstore.validation.Password;

@Getter
@Setter
@NoArgsConstructor
@FieldMatch(
        field = "password",
        fieldMatch = "repeatPassword",
        message = "Passwords mismatch!"
)
public class UserRegistrationRequestDto {
    @NotBlank
    @Size(min = 8, max = 100)
    private String email;
    @NotBlank
    @Size(min = 6, max = 100)
    @Password
    private String password;
    private String repeatPassword;
    @NotBlank
    @Size(min = 1, max = 100)
    private String firstName;
    @NotBlank
    @Size(min = 1, max = 100)
    private String lastName;
    @NotBlank
    @Size(min = 4, max = 100)
    private String shippingAddress;
}
