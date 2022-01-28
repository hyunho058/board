package example.board;

import example.board.domain.member.Member;
import example.board.domain.member.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

@Component
@RequiredArgsConstructor
public class TestDataInit {

    private final MemberRepository memberRepository;

    @PostConstruct
    public void init(){
        memberRepository.save(new Member("member0", "member0", "1234"));
    }
}
