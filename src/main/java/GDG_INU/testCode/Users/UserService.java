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
        Users saveUser = Users.create(signUpRequestDto);
        return userRepository.save(saveUser).getId();
    }

    public Long login(LoginRequestDto loginRequestDto) {
        Users user = userRepository.findByEmail(loginRequestDto.getEmail())
                .orElseThrow(() -> new IllegalArgumentException("가입되지 않은 이메일입니다."));

        validatePassword(loginRequestDto.getPassword(), user);

        return user.getId();
    }

    private static void validatePassword(String password, Users user) {
        if (!user.getPassword().equals(password)) {
            throw new IllegalArgumentException("비밀번호가 일치하지 않습니다.");
        }
    }

    private void validateDuplicateMember(SignUpRequestDto signUpRequestDto) {
        if (userRepository.existsByEmail(signUpRequestDto.getEmail())) {
            throw new IllegalStateException("이미 존재하는 이메일입니다.");
        }
    }
}
