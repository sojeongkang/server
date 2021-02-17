package wsjmdev.wsjmdev;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import wsjmdev.wsjmdev.repository.JpaMemberRepository;
import wsjmdev.wsjmdev.repository.MemberRepository;
import wsjmdev.wsjmdev.service.MemberService;

@Configuration
public class SpringConfig {

    /*  JDBC 사용시
    private final DataSource dataSource;
    public SpringConfig(DataSource dataSource) {
        this.dataSource = dataSource;
    }
     */

    /* Jpa 사용시
    private EntityManager em;

    @Autowired
    public SpringConfig(EntityManager em){
        this.em = em;
    }
     */

    private final MemberRepository memberRepository;

    @Autowired
    public SpringConfig(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }

    @Bean
    //스프링이 뜰 때 configuration 읽고
    public MemberService memberService(){
        return new MemberService(memberRepository);
    }

    /* 스프링 데이터 Jpa 사용시 필요 X
    @Bean
    public MemberRepository memberRepository(){
        //return new DbMemberRepository();           // 컴포넌트 스캔 말고 직접 스프링 빈 등록하면
                                                    //메모리에서 db 교체시에 여기부분만 변경해주면 됨
        //return new MemoryMemberRepository();
       // return new JdbcTemplateMemberRepository(dataSource);
        //return new JpaMemberRepository(em);
    }
     */
}
