package wsjmdev.wsjmdev.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import wsjmdev.wsjmdev.domain.Member;
import wsjmdev.wsjmdev.service.MemberService;

import java.util.List;

@Controller         //컨트롤러에서 외부 요청을 받음
                    //스프링 빈 등록 - 컴포넌트 스캔 방식
public class MemberController {

    private final MemberService memberService;

    //@Autowired private MemberSerive memberService;  //필드 주입 방식

    /*
    @Autowired
    public void setMemberService(MemberService memberService) {      //setter 주입 방식
        this.memberService = memberService;
    }
     */

    @Autowired      //컨트롤러에서 서비스를 연결
    public MemberController(MemberService memberService) {      //생성자 주입 방식 (가장 좋음)
        //Dependency Injection (의존관계 주입)
        //멤버컨트롤러가 생성될 때 스프링 빈에 등록되어 있는 멤버서비스 객체를 가져다가 넣어줌
        this.memberService = memberService;
    }

    @GetMapping("/members/new")     //조회
    public String createForm() {
        return "members/createMemberForm";
    }

    @PostMapping("/members/new")    //데이터를 폼형태로 전달해 등록
    public String create(MemberForm form){
        Member member = new Member();
        member.setName(form.getName());

        //System.out.println("member = "+member.getName());

        memberService.join(member);

        return "redirect:/";
    }

    @GetMapping("/members")
    public String list(Model model) {
        List<Member> members = memberService.findMembers();
        model.addAttribute("members", members);
        return "members/memberList";
    }
}
