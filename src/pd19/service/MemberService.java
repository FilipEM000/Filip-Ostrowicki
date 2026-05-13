package pd19.service;

import pd19.dto.LoanDto;
import pd19.dto.MemberDto;
import pd19.entity.Member;

import java.util.List;

public interface MemberService {
    void register(MemberDto memberDto);

    MemberDto findById(long id);

    List<LoanDto> getActiveLoans(Long memberId);

    Member findEntityById(long id);
}
