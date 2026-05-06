package pd19.repository;

import pd19.entity.Member;

import java.util.HashSet;
import java.util.Set;

public class MemberRepository {
    private Set<Member> members = new HashSet<>();

    public Set<Member> findAll(){
        return members;
    }

    public void save(Member member){
        members.add(member);
    }
}
