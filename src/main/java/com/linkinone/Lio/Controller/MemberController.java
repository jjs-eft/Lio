package com.linkinone.Lio.Controller;

import com.linkinone.Lio.dto.MemberDto;
import com.linkinone.Lio.service.MemberService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
@AllArgsConstructor
public class MemberController {
    private MemberService memberService;

    // 메인 페이지
    @GetMapping("/")
    public String index() {
        return "/index";
    }

    @GetMapping("/recently-trend.html")
    public String trend() {
        return "/recently-trend.html";
    }

    @GetMapping("/service-introduction.html")
    public String introduction() {
        return "/service-introduction.html";
    }


    // 회원가입 처리
    @PostMapping("/login-modal-signup")
    public String execSignup(MemberDto memberDto) {
        memberService.joinUser(memberDto);

        return "redirect:/#open-login-modal";
    }

    @GetMapping("/user-info-modify.html")
    public String dispUserInfo(){
        return "user-info-modify.html";
    }

    @GetMapping("/user-info-message.html")
    public String dispMessage(){
        return "user-info-message.html";
    }




}
