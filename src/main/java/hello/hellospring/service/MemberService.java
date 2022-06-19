package hello.hellospring.service;

import hello.hellospring.domain.Member;
import hello.hellospring.repository.MemberRepository;
import hello.hellospring.repository.MemoryMemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Transactional  //Jpa를 쓰려면 트랜잭션이 서비스에 있어야한다. 데이터를 저장하거나 변경할때 필요함
public class MemberService {
    private final MemberRepository memberRepository;

    public MemberService(MemberRepository memberRepository) {
        this.memberRepository= memberRepository;
    }
    /**
    회원가입
    * */

    public Long join (Member member){
        long start = System.currentTimeMillis();
            //같은 이름이 있는 중복 회훤 x
            validateDuplicateMember(member); //중복 회원 검증
            memberRepository.save(member);
            return member.getId();
    }

    private void validateDuplicateMember(Member member) {
        memberRepository.findByName(member.getName())
                .ifPresent(m ->{
            throw new IllegalMonitorStateException("이미 존재하는 회원입니다.");
        });
    }
    
    /**
     * 전체 회원 조회
     * */
    public List<Member> findMembers(){
        long start = System.currentTimeMillis();
            return memberRepository.findAll();
    }

    public Optional<Member> findOne(Long memberId){
        return memberRepository.findById(memberId);
    }
}
