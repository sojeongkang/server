package wsjmdev.wsjmdev.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import wsjmdev.wsjmdev.domain.Member;
import wsjmdev.wsjmdev.repository.MemberRepository;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

// @Service        //서비스에서 비즈니스 로직을 만듦 (컴포넌트 스캔 방식)
@Transactional          //JPA는 Join 들어올 때 모든 데이터 변경이 다 transaction 안에서 실행되어야 함
public class MemberService {

    private final MemberRepository memberRepository;

   // @Autowired  //멤버서비스 생성 시 스프링 컨테이너에 있는 멤버 리포지터리를 넣어줌
    public MemberService(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }

    public Long join(Member member) {

        validateDuplicateMember(member);    //중복 회원 검증
        memberRepository.save(member);
        return member.getId();
    }

    private void validateDuplicateMember(Member member) {
        memberRepository.findByName(member.getName())
                .ifPresent(m -> {
                    throw new IllegalStateException("이미 존재하는 회원입니다.");
                });
    }

    //전체 회원 조회
    public List<Member> findMembers() {
        return memberRepository.findAll();
    }

    public Optional<Member> findOne(Long memberId) {
        return memberRepository.findById(memberId);
    }
}
