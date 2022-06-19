package hello.hellospring;

import hello.hellospring.aop.TimeTraceAop;
import hello.hellospring.repository.JpaMemberRepository;
import hello.hellospring.repository.MemberRepository;
import hello.hellospring.repository.SpringDataJpaMemberRepository;
import hello.hellospring.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.persistence.EntityManager;
import javax.sql.DataSource;


// 설정파일이라고 인식
//@Configuration
//public class SpringConfig {
//
//////    private DataSource dataSource;
////
//////    @Autowired
//////    public SpringConfig(DataSource dataSource){
//////        this.dataSource = dataSource;
//////    }
////
//////    private EntityManager em;
//////    @Autowired
//////    public SpringConfig(EntityManager em) {
//////        this.em = em;
//////    }
////
////    private final MemberRepository memberRepository;
////
////    public SpringConfig(MemberRepository memberRepository) {
////        this.memberRepository = memberRepository;
////    }
////
////    @Bean
////    public MemberService memberService() {
////        return new MemberService(memberRepository);
////    }
////////    @Bean
////////    public MemberRepository memberRepository() {
////////         //return new MemoryMemberRepository();
////////        //return new JdbcMemberRepository(dataSource);
////////        //return new JdbcTemplateMemberRepository(dataSource);
////////        //return new JpaMeberRepository(em);
////////
////////        }
//////    }
//        private final MemberRepository memberRepository;
//        public SpringConfig(MemberRepository memberRepository) {
//            this.memberRepository = memberRepository;
//        }
//        @Bean
//        public MemberService memberService() {
//            return new MemberService(memberRepository);
//        }
//
//}
@Configuration
public class SpringConfig {
    private final MemberRepository memberRepository;
    public SpringConfig(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }
    @Bean
    public MemberService memberService() {
        return new MemberService(memberRepository);
    }
}