package GDG_INU.testCode.Users;

import GDG_INU.testCode.Users.Dto.LoginRequestDto;
import GDG_INU.testCode.Users.Dto.SignUpRequestDto;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;

class UserServiceTest {

    @Mock
    private UserRepository userRepository;

    @InjectMocks
    private UserService userService;

    private SignUpRequestDto signUpRequestDto;
    private LoginRequestDto loginRequestDto;
    private Users user;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);

        reset(userRepository);

        // 필드 초기화
        signUpRequestDto = SignUpRequestDto.builder()
                .email("test@test.com")
                .password("password")
                .name("testUser")
                .build();

        loginRequestDto = LoginRequestDto.builder()
                .email("test@test.com")
                .password("password")
                .build();

        user = Users.builder()
                .email("test@test.com")
                .password("password")
                .name("testUser")
                .build();
    }

    @Test
    @DisplayName("유저 회원가입 테스트")
    void signUp() {
        // Given
        when(userRepository.existsByEmail(signUpRequestDto.getEmail())).thenReturn(false);
        when(userRepository.save(any(Users.class))).thenReturn(user);

        // When
        Long userId = userService.signUp(signUpRequestDto);

        // Then
        Assertions.assertThat(user.getId()).isEqualTo(userId);
    }

    @Test
    @DisplayName("유저 로그인 테스트")
    void login() {
        // Given
        when(userRepository.findByEmail(loginRequestDto.getEmail())).thenReturn(java.util.Optional.ofNullable(user));

        // When
        Long userId = userService.login(loginRequestDto);

        // Then
        Assertions.assertThat(user.getId()).isEqualTo(userId);
    }

    @Test
    @DisplayName("이메일 중복 테스트")
    void duplicateEmail() {
        // Given
        when(userRepository.findByEmail(anyString())).thenReturn(Optional.of(user));
        when(userRepository.existsByEmail(anyString())).thenReturn(true);

        // When
        IllegalStateException exception = Assertions.catchThrowableOfType(
                () -> userService.signUp(signUpRequestDto),
                IllegalStateException.class
        );

        // Then
        Assertions.assertThat(exception).isNotNull();
        Assertions.assertThat(exception.getMessage()).isEqualTo("이미 존재하는 이메일입니다.");

        verify(userRepository, times(1)).existsByEmail(signUpRequestDto.getEmail());
        verify(userRepository, never()).save(any(Users.class));
    }
}
