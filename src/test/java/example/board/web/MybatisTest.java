package example.board.web;

import example.board.domain.member.service.MemberService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class MybatisTest {

    @Autowired
    MemberService memberService;

    @Test
    void MYBATIS_TEST (){
        memberService.findMember(1L);
    }
}
