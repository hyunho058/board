package example.board.domain.member.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class MemberDto {
    private Long id;

    private String login_id;

    private String name;

    private String password;

    @Override
    public String toString() {
        return "MemberDto{" +
                "id=" + id +
                ", login_id='" + login_id + '\'' +
                ", name='" + name + '\'' +
                ", password='" + password + '\'' +
                '}';
    }
}
