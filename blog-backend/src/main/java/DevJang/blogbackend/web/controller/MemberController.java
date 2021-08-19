package DevJang.blogbackend.web.controller;

import DevJang.blogbackend.domain.member.Member;
import DevJang.blogbackend.domain.service.MemberService;
import DevJang.blogbackend.web.form.MemberForm;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Slf4j
@Controller
@RequiredArgsConstructor
@RequestMapping("/members")
public class MemberController {
    private final MemberService memberService;

    @GetMapping("/new")
    public String signup(Model model) {
        model.addAttribute("memberForm", new MemberForm());
        log.info("SIGN UP");
        return "members/memberAdd";
    }

    @PostMapping("/new")
    public String addMember(@Validated @ModelAttribute("memberForm") MemberForm form, BindingResult bindingResult, RedirectAttributes redirectAttributes) {
        if (bindingResult.hasErrors()) {
            log.info("errors ={}", bindingResult);
            return "/members/memberAdd";
        }
        Member member = new Member(form.getEmail(), form.getPassword(), form.getNickName());

        memberService.join(member);
        return "redirect:/";
    }
}
