package wsjmdev.wsjmdev.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import wsjmdev.wsjmdev.domain.Member;

import java.util.Optional;

public interface SpringDataJpaMemberRepository extends JpaRepository<Member, Long>, MemberRepository {  //인터페이스가 인터페이스 상속받을 땐 extends 사용
//인터페이스에 레포지터리만 구현해놓으면 스프링 데이터 jpa가 인터페이스에 대한 구현체를 자동 생성해주고 스프링 빈에 등록해놓음

    //JPQL select m from Member m where m.name = ?
    @Override
    Optional<Member> findByName(String name);
}
