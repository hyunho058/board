package example.board.domain.login;

import example.board.domain.member.Member;
import example.board.domain.member.MemberRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Slf4j
@Service
@RequiredArgsConstructor
public class LoginService {
    private final MemberRepository memberRepository;

    /**
     *
     * @return null이면 로그인 실패
     */
    public Member login(String loginId, String password){
        Optional<Member> findMemberOptional = memberRepository.findByLoginId(loginId);
//        Member member = findMemberOptional.get();
//        log.info("member={}",member);
//        if (member.getPassword().equals(password)){
//            return member;
//        }else {
//            return null;
//        }
        return findMemberOptional.filter(m -> m.getPassword().equals(password)).orElse(null);

    }
}
