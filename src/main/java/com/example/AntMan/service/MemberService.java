package com.example.AntMan.service;

import com.example.AntMan.repository.MemberRepository;
import com.example.AntMan.domain.Member;

import com.example.AntMan.domain.dto.SignUp;

import java.util.LinkedHashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.Errors;
import org.springframework.validation.FieldError;

@Service
public class MemberService {
    @Autowired
    private MemberRepository memberRepository;

    // 회원가입 유효성 확인
    public Map<String, String> validateHandling(Errors errors) {
        Map<String, String> validatorResult = new LinkedHashMap<>(); /* 유효성 검사에 실패한 필드 목록을 받음 */
        for (FieldError error : errors.getFieldErrors()) {
            String validKeyName = String.format("%s", error.getField());
            validatorResult.put(validKeyName, error.getDefaultMessage());
        }
        return validatorResult;
    }

    /* 아이디, 닉네임, 이메일 중복 여부 확인 */
    public void checkIdDuplication(SignUp signUp) {
        boolean usernameDuplicate = memberRepository.existsById(signUp.getId());
        if (usernameDuplicate) {
            throw new IllegalStateException("이미 존재하는 아이디입니다.");
        }
    }

    public void checkPhoneNumberDuplication(SignUp signUp) {
        boolean phoneNumberDuplicate = memberRepository.existsByPhoneNumber(signUp.getPhoneNumber());
        if (phoneNumberDuplicate) {
            throw new IllegalStateException("이미 존재하는 전화번호입니다.");
        }
    }

    public void checkEmailDuplication(SignUp signUp) {
        boolean emailDuplicate = memberRepository.existsByEmail(signUp.getEmail());
        if (emailDuplicate) {
            throw new IllegalStateException("이미 존재하는 이메일입니다.");
        }
    }

    // 회원가입
    @Transactional
    public Member memberSave(Member member) {
        memberRepository.save(member);
        return member;
    }

    public Member login(String loginId, String password) {
        Member member = memberRepository.findById(loginId);

        if (member.getPassword().equals(password)) {
            return member;
        } else {
            return null;
        }

    }

}