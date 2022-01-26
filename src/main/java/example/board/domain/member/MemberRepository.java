package example.board.domain.member;

import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface MemberRepository {
    Member save(Member member);
    Member findById(Long id);
    Optional<Member> findByLoginId(String loginId);
    List<Member> findAll();

}
