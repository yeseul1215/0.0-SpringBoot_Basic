package hello.hellospring.service;
import hello.hellospring.domain.Member;
import hello.hellospring.repository.MemberRepository;
import org.assertj.core.api.AssertionsForClassTypes;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
@SpringBootTest
@Transactional
//Transactional 테스트 케이스에 이 어노테이션이 있으면 테스트 시작전에 트랜잭션을 시작하고, 테스트 완료 후에 롤백한다. 이렇게하면 db에 데이터가 남지않음
class MemberServiceIntegrationTest {
    @Autowired MemberService memberService;
    @Autowired MemberRepository memberRepository;


    @Test
    public void 회원가입() throws Exception {
        //Given
        Member member = new Member();
        member.setName("hello");
        //When
        Long saveId = memberService.join(member);
        //Then
        Member findMember = memberRepository.findById(saveId).get();
        assertEquals(member.getName(), findMember.getName());
    }
    @Test
    public void 중복_회원_예외(){
        //given
        Member member1 = new Member();
        member1.setName("spring6");

        Member member2 = new Member();
        member2.setName("spring6");

        //when

        memberService.join(member1);

        IllegalMonitorStateException e=  assertThrows(IllegalMonitorStateException.class, ()->memberService.join(member2));
        AssertionsForClassTypes.assertThat(e.getMessage()).isEqualTo("이미 존재하는 회원입니다.");
       /* try{

            memberService.join(member2);
            fail();
       }catch (IllegalMonitorStateException e){
            assertThat(e.getMessage()).isEqualTo("이미 존재하는 회원입니다.");
        }*/
        //then

    }
}