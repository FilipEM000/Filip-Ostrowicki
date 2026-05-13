package pd19.mapper;

import pd19.dto.MemberDto;
import pd19.entity.Member;

import java.util.ArrayList;

public class MemberMapper {
    public static MemberDto mapToDto(Member member){
        return new MemberDto(member.getId(), member.getName(), member.getEmail());
    }

    public static Member mapToEntity(MemberDto memberDto){
        return new Member(memberDto.id(), memberDto.name(), memberDto.email(), new ArrayList<>());
    }
}
