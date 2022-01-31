package example.board.web.board;

import example.board.domain.board.Board;
import example.board.domain.item.Item;
import example.board.mapper.BoardMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Slf4j
@Controller
@RequestMapping("/boards")
@RequiredArgsConstructor
public class BoardController {

    private final BoardMapper boardMapper;

    @GetMapping
    public String boards(Model model){
        List<Board> boards =  boardMapper.findAll();
        model.addAttribute("boards", boards);
        return "boards/boards";
    }

    @GetMapping("/add")
    public String addForm(Model model) {
        model.addAttribute("board", new Board());
        return "boards/addForm";
    }
}
