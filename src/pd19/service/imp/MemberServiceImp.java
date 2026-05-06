package pd19.service.imp;

import pd19.entity.Loan;
import pd19.entity.Member;
import pd19.exception.MemberNotFoundException;
import pd19.repository.MemberRepository;
import pd19.service.MemberService;

import java.util.List;

public class MemberServiceImp implements MemberService {
    MemberRepository memberRepository;

    public MemberServiceImp(MemberRepository memberRepository) {
    }

    @Override
    public void register(Member member) {
        memberRepository.save(member);
    }

    @Override
    public Member findById(long memberId) {
        return memberRepository.findAll().stream()
                .filter(member -> member.getId() == memberId)
                .findFirst()
                .orElseThrow(() -> new MemberNotFoundException("Nie odnaleziono osoby o id " + memberId));
    }

    @Override
    public List<Loan> getActiveLoans(Long memberId) {
        return findById(memberId)
                .getLoans()
                .stream()
                .filter(loan -> loan.getReturnedAt() == null)
                .toList();
    }
}
