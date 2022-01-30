package example.board.domain.member.service;

import example.board.domain.member.Member;
import example.board.domain.member.dto.MemberDto;
import example.board.mapper.MemberMapper;
import example.board.web.login.LoginForm;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Slf4j
@Service
@RequiredArgsConstructor
public class MemberService {

    private final MemberMapper memberMapper;

    public void createMember(Member member){
        try {
            memberMapper.save(member.getLoginId(), member.getName(), member.getPassword());
        }catch (Exception e){
            throw e;
        }
    }

    public void findMember(Long id) {
        log.info("test------------------------");
        MemberDto memberDto = memberMapper.findById(id);
        log.info("memberDto is = {}", memberDto.toString());
    }

    public MemberDto loginCheck(LoginForm loginForm){
        Optional<MemberDto> findMemberOptional = memberMapper.loginCheck(loginForm.getLoginId(), loginForm.getPassword());

        return findMemberOptional.filter(m -> m.getLogin_id().equals(loginForm.getLoginId())).orElse(null);
    }

}
