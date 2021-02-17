package wsjmdev.wsjmdev.repository;

import org.springframework.stereotype.Repository;
import wsjmdev.wsjmdev.domain.Member;
import java.util.*;

// @Repository     //리포지터리에서 데이터를 저장
public class MemoryMemberRepository implements MemberRepository {

    private static Map<Long, Member> store = new HashMap<>();
    private static long sequence = 0L;      //DB에 save 했을 때 자동으로 증가됨

    @Override
    public Member save(Member member) {
        member.setId(++sequence);       //시스템에서 id를 셋팅
        store.put(member.getId(), member);
        return member;
    }

    @Override
    public Optional<Member> findById(Long id) {
        return Optional.ofNullable(store.get(id));
    }

    @Override
    public Optional<Member> findByName(String name) {
        return store.values().stream()
                .filter(member -> member.getName().equals(name))
                .findAny();
    }

    @Override
    public List<Member> findAll() {
        return new ArrayList<>(store.values());
    }

    public void clearStore() {
        store.clear();
    }
}
