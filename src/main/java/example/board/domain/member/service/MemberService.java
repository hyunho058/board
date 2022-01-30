package example.board.domain.member.service;

import example.board.domain.member.Member;
import example.board.domain.member.dto.MemberDto;
import example.board.mapper.MemberMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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

}
