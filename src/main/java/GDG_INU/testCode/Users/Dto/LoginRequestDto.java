package GDG_INU.testCode.Users.Dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class LoginRequestDto {

    private String email;

    private String password;

    private LoginRequestDto(String email, String password) {
        this.email = email;
        this.password = password;
    }

    public static LoginRequestDto of(String email, String password) {
        return new LoginRequestDto(email, password);
    }
}
