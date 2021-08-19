package DevJang.blogbackend.domain.service;

import DevJang.blogbackend.domain.member.Member;
import DevJang.blogbackend.domain.member.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class LoginService {
    private final MemberRepository memberRepository;

    public Member login(String email, String password) {
        return memberRepository.findByEmail(email)
                .filter(m -> m.getPassword().equals(password)).orElse(null);
    }
}
