package com.linkinone.Lio.Controller;

import com.linkinone.Lio.dto.BoardDto;
import com.linkinone.Lio.dto.MemberDto;
import com.linkinone.Lio.service.MemberService;
import lombok.AllArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@AllArgsConstructor
@ResponseBody
public class MemberController {
    private MemberService memberService;

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
        String resultmsg="<script>alert('입력한 이메일로 전송된 URL을 통해 회원가입을 완료하신후 로그인해주세요!');location.href='/#open-login-modal'</script>";

        return resultmsg;
    }

    @GetMapping("/user-info-modify.html")
    public String dispUserInfo(Authentication authentication, Model model){
        String email;
        UserDetails userDetails = (UserDetails) authentication.getPrincipal();
        email = userDetails.getUsername();
        MemberDto memberDto = memberService.getInfo(email);

        model.addAttribute("memberDto", memberDto);

        return "user-info-modify.html";
    }

    @PutMapping("/user-info-modify.html")
    public String update_user_info(MemberDto memberDto){
        memberService.updateInfo(memberDto);

        return "redirect:/user-info-modify.html";
    }

    @DeleteMapping("/user-info-modify.html")
    public String delete_user(Authentication authentication, Model model){
        String email;
        UserDetails userDetails = (UserDetails) authentication.getPrincipal();
        email = userDetails.getUsername();

        memberService.deleteUser(email);
        return "redirect:/";
    }

}
