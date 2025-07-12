package GDG_INU.testCode.Users.Dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class SignUpRequestDto {

    private String email;

    private String password;

    private String name;

    private SignUpRequestDto(String email, String password, String name) {
        this.email = email;
        this.password = password;
        this.name = name;
    }

    public static SignUpRequestDto of(String email, String password, String name) {
        return new SignUpRequestDto(email, password, name);
    }
}
