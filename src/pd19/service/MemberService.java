package pd19.service;

import pd19.entity.Loan;
import pd19.entity.Member;

import java.util.List;

public interface MemberService {
    void register(Member member);

    Member findById(long id);

    List<Loan> getActiveLoans(Long memberId);
}
