package GDG_INU.testCode.Users;

import GDG_INU.testCode.Users.Dto.LoginRequestDto;
import GDG_INU.testCode.Users.Dto.SignUpRequestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @PostMapping("/signup")
    public String signUp(SignUpRequestDto signUpRequestDto) {
        userService.signUp(signUpRequestDto);
        return "redirect:/login";
    }

    @PostMapping("/login")
    public String login(LoginRequestDto loginRequestDto) {
        userService.login(loginRequestDto);
        return "redirect:/home";
    }
}
