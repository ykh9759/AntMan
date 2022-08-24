package com.example.AntMan.controller;

import com.example.AntMan.service.MemberService;
import com.example.AntMan.domain.Member;

import java.util.Map;
import java.util.Objects;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
public class MemberController {

    @Autowired
    MemberService memberService;

    @GetMapping("/login")
    public String login() {
        return "member/login";
    }

    @GetMapping("/sign-up")
    public String signUp() {
        return "member/sign_up";
    }

    @PostMapping("/sign-up/store")
    public void save(HttpServletResponse response, @RequestParam Map<String, String> param, Member member)
            throws Exception {

        String name = param.get("name"); // 이름
        String id = param.get("id"); // 아이디
        String password1 = param.get("password1"); // 비밀번호
        String password2 = param.get("password2"); // 비밀번호 재확인
        String phoneNumber = param.get("phoneNumber"); // 전화번호
        String email = param.get("email"); // 이메일

        if (name.isEmpty()) {
            Utils.alertAndBackPage(response, "이름 입력해주세요.");
        } else if (id.isEmpty()) {
            Utils.alertAndBackPage(response, "아이디를 입력해주세요.");
        } else if (password1.isEmpty()) {
            Utils.alertAndBackPage(response, "비밀번호를 입력해주세요.");
        } else if (password2.isEmpty()) {
            Utils.alertAndBackPage(response, "비밀번호 재확인을 입력해주세요.");
        } else if (phoneNumber.isEmpty()) {
            Utils.alertAndBackPage(response, "전화번호를 입력해주세요.");
        } else if (!Objects.equals(password1, password2)) {
            Utils.alertAndBackPage(response, "비밀번호와 비밀번호 재확인이 틀립니다.");
        }

        member.setName(name);
        member.setId(id);
        member.setPassword(password1);
        member.setPhoneNumber(phoneNumber);
        member.setEmail(email);

        memberService.save(member);
        Utils.alertAndMovePage(response, "회원가입이 완료 되었습니다.", "/");
        // return "redirect:/";
    }

}