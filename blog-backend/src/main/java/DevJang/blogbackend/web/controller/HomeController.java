package DevJang.blogbackend.web.controller;

import DevJang.blogbackend.domain.member.Member;
import DevJang.blogbackend.web.SessionConst;
import DevJang.blogbackend.web.argumentResolver.Login;
import DevJang.blogbackend.web.form.MemberForm;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.SessionAttribute;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@Slf4j
@Controller
@RequiredArgsConstructor
public class HomeController {

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String home(@Login Member loginMember, Model model) {
        model.addAttribute("member", loginMember);
        log.info("START HomeController");
        return "home";
    }
}
