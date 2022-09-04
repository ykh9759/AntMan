package com.example.AntMan.controller;

import com.example.AntMan.service.MemberService;
import com.example.AntMan.utils.Utils;
import com.example.AntMan.domain.Member;

import com.example.AntMan.dto.SignUpMember;

import java.io.IOException;
import java.util.Map;
import java.util.Objects;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

@Controller
public class MemberController {

    @Autowired
    MemberService memberService;

    @PostMapping("/sign-up/save")
    public void save(@Valid SignUpMember signUpMember, Errors errors, HttpServletResponse response, Member member) throws Exception {

        if (errors.hasErrors()) {
            Map<String, String> validatorResult = memberService.validateHandling(errors);

            for (String key : validatorResult.keySet()) {
                if (key.equals("name")) {
                    Utils.alertAndBackPage(response, validatorResult.get(key));
                } else if (key.equals("id")) {
                    Utils.alertAndBackPage(response, validatorResult.get(key));
                } else if (key.equals("password")) {
                    Utils.alertAndBackPage(response, validatorResult.get(key));
                } else if (key.equals("passwordCheck")) {
                    Utils.alertAndBackPage(response, validatorResult.get(key));
                } else if (key.equals("phoneNumber")) {
                    Utils.alertAndBackPage(response, validatorResult.get(key));
                }
            }
        }

        String password = signUpMember.getPassword(); // 비밀번호
        String passwordCheck = signUpMember.getPasswordCheck(); // 비밀번호 재확인

        if (!Objects.equals(password, passwordCheck)) {
            Utils.alertAndBackPage(response, "비밀번호가 일치하지 않습니다.");
        }

        // 아이디, 전화번호, 이메일 중복 체크
        try {
            memberService.checkIdDuplication(signUpMember);
            memberService.checkPhoneNumberDuplication(signUpMember);
            memberService.checkEmailDuplication(signUpMember);
        } catch (IllegalStateException e) {
            Utils.alertAndBackPage(response, e.getMessage());
        }
        
        member.setId(signUpMember.getId());

        memberService.memberSave(member);
        Utils.alertAndMovePage(response, "회원가입이 완료 되었습니다.", "/");
    }

    @PostMapping("/login")
    public void login(@RequestParam Map<String, String> login, HttpServletResponse response, HttpServletRequest request)
            throws IOException {

        String id = login.get("id");
        String password = login.get("password");
        Member member = memberService.login(id, password);

        if (member != null && id.equals(member.getId())) {
            HttpSession session = request.getSession();
            session.setAttribute("LOGIN_MEMBER", member); // 로그인 회원 세션 저장

            Utils.alertAndMovePage(response, "안녕하세요. " + id + " 님", "/");
        } else {
            Utils.alertAndBackPage(response, "아이디 또는 비밀번호를 확인 해주세요.");
        }

    }

    @GetMapping("/logout")
    public String logout(HttpServletRequest request) {
        HttpSession session = request.getSession(false);
        if (session != null) {
            session.invalidate();
        }

        return "redirect:/";

    }

}