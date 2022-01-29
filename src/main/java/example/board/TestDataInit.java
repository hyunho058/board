package example.board;

import example.board.domain.item.Item;
import example.board.domain.item.ItemRepository;
import example.board.domain.member.Member;
import example.board.domain.member.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

@Component
@RequiredArgsConstructor
public class TestDataInit {

    private final MemberRepository memberRepository;
    private final ItemRepository itemRepository;


    @PostConstruct
    public void init() {
        memberRepository.save(new Member("member0", "member0", "1234"));
        itemRepository.save(new Item("itemA", 10000, 10));
        itemRepository.save(new Item("itemB", 20000, 20));
    }
}
