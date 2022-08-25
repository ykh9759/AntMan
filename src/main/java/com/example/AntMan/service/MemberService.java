package com.example.AntMan.service;

import com.example.AntMan.repository.MemberRepository;
import com.example.AntMan.domain.Member;

import java.util.HashMap;
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

    // 회원가입 시, 유효성 확인
    @Transactional(readOnly = true)
    public Map<String, String> validateHandling(Errors errors) {
        Map<String, String> validatorResult = new LinkedHashMap<>(); /* 유효성 검사에 실패한 필드 목록을 받음 */
        for (FieldError error : errors.getFieldErrors()) {
            String validKeyName = String.format("%s", error.getField());
            validatorResult.put(validKeyName, error.getDefaultMessage());
        }
        return validatorResult;
    }

    // 회원가입
    public Member memberSave(Member member) {

        member.setStatus(1);

        memberRepository.save(member);
        return member;
    }

}