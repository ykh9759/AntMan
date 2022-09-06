package com.example.AntMan.service;

import com.example.AntMan.repository.MemberRepository;
import com.example.AntMan.utils.Encrypt;
import com.example.AntMan.domain.entity.Member;
import com.example.AntMan.domain.dto.SignUp;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.Errors;
import org.springframework.validation.FieldError;

@Service
public class MemberService {
    @Autowired
    private MemberRepository memberRepository;

    @Autowired
    private Encrypt encrypt;

    // 회원가입 유효성 확인
    public Map<String, String> validateHandling(Errors errors) {
        Map<String, String> validatorResult = new HashMap<>(); /* 유효성 검사에 실패한 필드 목록을 받음 */
        for (FieldError error : errors.getFieldErrors()) {
            String validKeyName = String.format("%s", error.getField());
            validatorResult.put(validKeyName, error.getDefaultMessage());
        }
        return validatorResult;
    }

    /* 아이디, 닉네임, 이메일 중복 여부 확인 */
    public String checkDuplication(SignUp signUp) {
        boolean usernameDuplicate = memberRepository.existsById(signUp.getId());
        boolean phoneNumberDuplicate = memberRepository.existsByPhoneNumber(signUp.getPhoneNumber());
        boolean emailDuplicate = memberRepository.existsByEmail(signUp.getEmail());

        if (usernameDuplicate) {
            return "이미 존재하는 아이디입니다.";
        } else if (phoneNumberDuplicate) {
            return "이미 존재하는 전화번호입니다.";
        } else if (emailDuplicate) {
            return "이미 존재하는 이메일입니다.";
        } else {
            return null;
        }
    }

    // 회원가입
    @Transactional
    public Member memberSave(Member member) {
        String salt = encrypt.getSalt();
        String encodePassword = encrypt.getEncrypt(member.getPassword(), salt);

        member.passwordEncrypt(encodePassword, salt);

        memberRepository.save(member);

        return member;
    }

    public Member login(String loginId, String password) {

        String encodePassword;

        Optional<Member> member = memberRepository.findById(loginId);

        if (member.isPresent()) {

            encodePassword = encrypt.getEncrypt(password, encrypt.getSalt());
            System.out.println(encodePassword);

            if (member.get().getPassword().equals(encodePassword)) {
                return member.get();
            } else {
                return null;
            }
        } else {
            return null;
        }
    }

}