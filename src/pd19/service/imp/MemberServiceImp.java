package pd19.service.imp;

import pd19.dto.LoanDto;
import pd19.dto.MemberDto;
import pd19.entity.Member;
import pd19.exception.MemberNotFoundException;
import pd19.mapper.LoanMapper;
import pd19.mapper.MemberMapper;
import pd19.repository.MemberRepository;
import pd19.service.MemberService;

import java.util.List;

public class MemberServiceImp implements MemberService {
    MemberRepository memberRepository;

    public MemberServiceImp(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }

    @Override
    public void register(MemberDto memberDto) {
        memberRepository.save(MemberMapper.mapToEntity(memberDto));
    }

    @Override
    public MemberDto findById(long memberId) {
        Member member = memberRepository.findById(memberId);
        if (member == null) {
            throw new MemberNotFoundException("Nie odnaleziono osoby o id " + memberId);
        }
        return MemberMapper.mapToDto(member);
    }

    @Override
    public List<LoanDto> getActiveLoans(Long memberId) {
        return findEntityById(memberId)
                .getLoans()
                .stream()
                .filter(loan -> loan.getReturnedAt() == null)
                .map(LoanMapper::mapToDto)
                .toList();
    }

    @Override
    public Member findEntityById(long memberId) {
        Member member = memberRepository.findById(memberId);
        if (member == null) {
            throw new MemberNotFoundException("Nie odnaleziono osoby o id " + memberId);
        }
        return member;
    }
}
