package DevJang.blogbackend.web.api;

import DevJang.blogbackend.domain.member.Member;
import DevJang.blogbackend.domain.service.MemberService;
import DevJang.blogbackend.web.form.member.CreateMemberForm;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequestMapping("/api/members")
@RequiredArgsConstructor
public class MemberApiController {
    private final MemberService memberService;

    @PostMapping("/add")
    public Object saveMember(@RequestBody @Validated CreateMemberForm form, BindingResult bindingResult) {
        log.info("API 컨트롤러 호출");

        if (bindingResult.hasErrors()) {
            log.info("검증 오류 발생 error ={}", bindingResult);
            return bindingResult.getAllErrors();
        }
        Member joinMember = new Member(form.getEmail(), form.getPassword(), form.getNickName());

        Long result = memberService.join(joinMember);
        return new CreateMemberResponse(result);
    }

    @Data
    static class CreateMemberResponse{
        private Long id;

        public CreateMemberResponse(Long id) {
            this.id = id;
        }
    }
}
