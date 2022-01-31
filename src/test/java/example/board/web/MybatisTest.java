package example.board.web;

import example.board.domain.board.Board;
import example.board.domain.member.service.MemberService;
import example.board.mapper.BoardMapper;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
public class MybatisTest {

    @Autowired
    MemberService memberService;
    @Autowired
    BoardMapper boardMapper;

    @Test
    void MYBATIS_TEST (){
        memberService.findMember(1L);
    }

    @Test
    void BOARD_LIST(){
        List<Board> boards = boardMapper.findAll();
        Assertions.assertThat(boards.size()).isEqualTo(2);
    }
}
