package pd19.repository;

import pd19.entity.Member;

import java.util.HashMap;
import java.util.Map;

public class MemberRepository {
    private Map<Long, Member> members = new HashMap<>();

    public Map<Long, Member> findAll() {
        return members;
    }

    public void save(Member member) {
        members.put(member.getId(), member);
    }

    public Member findById(Long id) {
        return members.get(id);
    }
}
