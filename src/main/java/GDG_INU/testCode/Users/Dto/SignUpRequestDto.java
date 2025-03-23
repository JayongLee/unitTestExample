package GDG_INU.testCode.Users.Dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class SignUpRequestDto {

    private String email;

    private String password;

    private String name;
}
