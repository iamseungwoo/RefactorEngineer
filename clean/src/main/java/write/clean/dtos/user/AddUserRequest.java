package write.clean.dtos.user;

import jakarta.validation.constraints.Email;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AddUserRequest {
    @Email
    private String email;
    private String password;
}
