package example.board.mapper;

import example.board.domain.member.dto.MemberDto;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface MemberMapper {
    MemberDto findById(long id);

    void save(String loginId, String name, String password);
}
