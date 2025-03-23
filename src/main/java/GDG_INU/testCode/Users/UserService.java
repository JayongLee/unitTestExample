package GDG_INU.testCode.Users;

import GDG_INU.testCode.Users.Dto.LoginRequestDto;
import GDG_INU.testCode.Users.Dto.SignUpRequestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;

    public Long signUp(SignUpRequestDto signUpRequestDto) {

        validateDuplicateMember(signUpRequestDto);
        Users saveUser = buildUser(signUpRequestDto);
        return userRepository.save(saveUser).getId();

    }

    public Long login(LoginRequestDto loginRequestDto) {
        Users user = userRepository.findByEmail(loginRequestDto.getEmail())
                .orElseThrow(() -> new IllegalArgumentException("가입되지 않은 이메일입니다."));

        if (!user.getPassword().equals(loginRequestDto.getPassword())) {
            throw new IllegalArgumentException("비밀번호가 일치하지 않습니다.");
        }

        return user.getId();
    }

    private void validateDuplicateMember(SignUpRequestDto signUpRequestDto) {
        if (userRepository.existsByEmail(signUpRequestDto.getEmail())) {
            throw new IllegalStateException("이미 존재하는 이메일입니다.");
        }
    }

    private Users buildUser(SignUpRequestDto signUpRequestDto) {
        return Users.builder()
                .email(signUpRequestDto.getEmail())
                .password(signUpRequestDto.getPassword())
                .name(signUpRequestDto.getName())
                .build();
    }
}
