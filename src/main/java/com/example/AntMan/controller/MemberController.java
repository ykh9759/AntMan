package com.example.AntMan.controller;

import com.example.AntMan.service.MemberService;
import com.example.AntMan.utils.Utils;
import com.example.AntMan.domain.Member;

import com.example.AntMan.domain.dto.SignUp;
import com.example.AntMan.domain.dto.Login;

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

    Member member;

    @PostMapping("/sign-up/save")
    public void save(@Valid SignUp signUp, Errors errors, HttpServletResponse response)
            throws Exception {

        if (errors.hasErrors()) {
            Map<String, String> validatorResult = memberService.validateHandling(errors);

            for (String key : validatorResult.keySet()) {
                Utils.alertAndBackPage(response, validatorResult.get(key));
                return;
            }
        }

        String password = signUp.getPassword(); // 비밀번호
        String passwordCheck = signUp.getPasswordCheck(); // 비밀번호 재확인

        if (!Objects.equals(password, passwordCheck)) {
            Utils.alertAndBackPage(response, "비밀번호가 일치하지 않습니다.");
            return;
        }

        // 아이디, 전화번호, 이메일 중복 체크
        try {
            memberService.checkIdDuplication(signUp);
            memberService.checkPhoneNumberDuplication(signUp);
            memberService.checkEmailDuplication(signUp);
        } catch (IllegalStateException e) {
            Utils.alertAndBackPage(response, e.getMessage());
            return;
        }

        member = signUp.toEntity();

        memberService.memberSave(member);

        Utils.alertAndMovePage(response, "회원가입이 완료 되었습니다.", "/");
        return;
    }

    @PostMapping("/login")
    public void login(@Valid Login login, Errors errors, HttpServletResponse response, HttpServletRequest request)
            throws IOException {

        if (errors.hasErrors()) {
            Map<String, String> validatorResult = memberService.validateHandling(errors);

            for (String key : validatorResult.keySet()) {
                Utils.alertAndBackPage(response, validatorResult.get(key));
                return;
            }
        }

        String id = login.getId();
        String password = login.getPassword();
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