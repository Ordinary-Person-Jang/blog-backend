package DevJang.blogbackend.web.api;

import DevJang.blogbackend.domain.member.Member;
import DevJang.blogbackend.domain.service.LoginService;
import DevJang.blogbackend.web.form.login.LoginForm;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.Cookie;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class LoginApiController {

    private final LoginService loginService;

    @PostMapping("/login")
    public Object login(@RequestBody @Validated LoginForm form, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return bindingResult.getAllErrors();
        }

        Member loginMember = loginService.login(form.getEmail(), form.getPassword());
        if (loginMember == null) {
            bindingResult.reject("loginFail", "아이디 또는 비밀번호가 맞지 않습니다");
            return bindingResult.getAllErrors();
        }
        Cookie cookie = createCookie(String.valueOf(loginMember.getId()), 60, "loginCookie");

        return new LoginResponse(cookie, loginMember.getNickName());
    }

    @PostMapping("/logout")
    public Object logout() {
        Cookie cookie = createCookie(null, 0, "loginCookie");
        return new LoginResponse(cookie, null);
    }

    private Cookie createCookie(String o, int age, String memberId) {
        Cookie cookie = new Cookie(memberId, o);
        cookie.setMaxAge(age);
        return cookie;
    }

    @Data
    static class LoginResponse {
        private Cookie cookie;
        private String nickName;

        public LoginResponse(Cookie cookie, String nickName) {
            this.cookie = cookie;
            this.nickName = nickName;
        }
    }
}
