package com.example.AntMan.controller;

import com.example.AntMan.service.MemberService;
import com.example.AntMan.domain.Member;

import java.util.Map;
import java.util.Objects;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.Errors;
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
    public String save(@Valid Member member, Errors errors, HttpServletResponse response) throws Exception {

        if (errors.hasErrors()) { /* 회원가입 실패시 입력 데이터 값을 유지 */
            Map<String, String> validatorResult = memberService.validateHandling(errors);

            for (String key : validatorResult.keySet()) {
                if (key.equals("name")) {
                    Utils.alertAndBackPage(response, validatorResult.get(key));
                    return "";
                } else if (key.equals("id")) {
                    Utils.alertAndBackPage(response, validatorResult.get(key));
                    return "";
                } else if (key.equals("password")) {
                    Utils.alertAndBackPage(response, validatorResult.get(key));
                    return "";
                } else if (key.equals("passwordCheck")) {
                    Utils.alertAndBackPage(response, validatorResult.get(key));
                    return "";
                } else if (key.equals("phoneNumber")) {
                    Utils.alertAndBackPage(response, validatorResult.get(key));
                    return "";
                }
            }
        }

        String password = member.getPassword(); // 비밀번호
        String passwordCheck = member.getPasswordCheck(); // 비밀번호 재확인

        if (!Objects.equals(password, passwordCheck)) {
            Utils.alertAndBackPage(response, "비밀번호와 비밀번호 재확인이 틀립니다.");
            return "";
        }

        memberService.memberSave(member);
        Utils.alertAndMovePage(response, "회원가입이 완료 되었습니다.", "/");
        return "redirect:/";
    }

}