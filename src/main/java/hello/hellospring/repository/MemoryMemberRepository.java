package hello.hellospring.repository;

import hello.hellospring.domain.Member;
import org.springframework.stereotype.Repository;

import javax.swing.text.html.Option;
import java.util.*;
//@Repository
public class MemoryMemberRepository implements MemberRepository{

    private static Map<Long,Member> strore = new HashMap<>();
    private static long sequence = 0L;

    @Override
    public Member save(Member member) {
         member.setId(++sequence);
         strore.put(member.getId(),member);
         return member;
    }

    @Override
    public Optional<Member> findById(Long id) {
        return Optional.ofNullable(strore.get(id));
    }

    @Override
    public Optional<Member> findByName(String name) {
        return strore.values().stream()
                .filter(member -> member.getName().equals(name))
                .findAny();
    }

    @Override
    public List<Member> findAll() {
        return new ArrayList<>(strore.values());
    }

    public void clearStore(){

        strore.clear();
    }

}
