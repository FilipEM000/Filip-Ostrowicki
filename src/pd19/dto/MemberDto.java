package pd19.dto;

import java.util.List;

public record MemberDto ( long id, String name, String email, List<LoanDto>loans){
}
