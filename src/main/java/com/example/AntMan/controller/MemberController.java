package com.example.AntMan.controller;

import com.example.AntMan.service.MemberService;
import com.example.AntMan.utils.Alert;
import com.example.AntMan.domain.entity.Member;

import com.example.AntMan.domain.dto.SignUp;
import com.example.AntMan.domain.dto.Login;

import java.io.IOException;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;

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
    public void save(@Valid SignUp signUp, Errors errors, HttpServletResponse response)
            throws Exception {

        String password = signUp.getPassword(); // 비밀번호
        String passwordCheck = signUp.getPasswordCheck(); // 비밀번호 재확인
        Optional<String> checkDupl; // 중복검사 반환 문자열 저장
        Member member = signUp.toEntity(); // Entity 객체 생성

        // 회원정보 유효성 검사
        if (errors.hasErrors()) {
            Map<String, String> validatorResult = memberService.validateHandling(errors);

            for (String key : validatorResult.keySet()) {
                Alert.alertAndBackPage(response, validatorResult.get(key));
                return;
            }
        }

        // 비밀번호 재확인 확인
        if (!Objects.equals(password, passwordCheck)) {
            Alert.alertAndBackPage(response, "비밀번호가 일치하지 않습니다.");
            return;
        }

        // 아이디, 전화번호, 이메일 중복 체크
        checkDupl = memberService.checkDuplication(signUp);
        if (checkDupl.isPresent()) {
            Alert.alertAndBackPage(response, checkDupl.get());
            return;
        }

        // 회원정보 저장
        memberService.memberSave(member);

        // 회원가입 완료 알림창 띄운 후 메인으로 이동
        Alert.alertAndMovePage(response, "회원가입이 완료 되었습니다.", "/");
        return;
    }

    @PostMapping("/login")
    public void login(@Valid Login login, Errors errors, HttpServletResponse response, HttpServletRequest request)
            throws IOException {

        if (errors.hasErrors()) {
            Map<String, String> validatorResult = memberService.validateHandling(errors);

            for (String key : validatorResult.keySet()) {
                Alert.alertAndBackPage(response, validatorResult.get(key));
                return;
            }
        }

        String id = login.getId();
        String password = login.getPassword();
        Optional<Member> member = memberService.login(id, password);

        if (member.isPresent() && id.equals(member.get().getId())) {
            HttpSession session = request.getSession();
            session.setAttribute("LOGIN_MEMBER", member.get()); // 로그인 회원 세션 저장

            Alert.alertAndMovePage(response, "안녕하세요. " + id + " 님", "/");
            return;

        } else {
            Alert.alertAndBackPage(response, "아이디 또는 비밀번호를 확인 해주세요.");
            return;
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