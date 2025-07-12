package GDG_INU.testCode.Users;

import GDG_INU.testCode.Users.Dto.SignUpRequestDto;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.*;

@Entity
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Users {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String email;

    private String password;

    private String name;

    private Users(String email, String password, String name) {
        this.email = email;
        this.password = password;
        this.name = name;
    }

    public static Users create(SignUpRequestDto signUpRequestDto) {
        return new Users(signUpRequestDto.getEmail(), signUpRequestDto.getPassword(), signUpRequestDto.getName());
    }
}
