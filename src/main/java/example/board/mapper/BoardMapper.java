package example.board.mapper;

import example.board.domain.board.Board;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface BoardMapper {
    List<Board> findAll();
}
