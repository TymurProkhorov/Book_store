package mate.academy.bookstore.dto.user;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import mate.academy.bookstore.validation.Password;

@Getter
@Setter
@NoArgsConstructor
public class UserRegistrationRequest {
    @NotBlank
    @Size(min = 4, max = 50)
    private String email;
    @NotBlank
    @Size(min = 6, max = 20)
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
