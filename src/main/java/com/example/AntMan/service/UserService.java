package com.example.AntMan.service;

import com.example.AntMan.repository.UserRepository;
import com.example.AntMan.utils.Encrypt;
import com.example.AntMan.domain.entity.User;
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
public class UserService {
    @Autowired
    private UserRepository userRepository;

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
    public Optional<String> checkDuplication(SignUp signUp) {

        Optional<String> returnStr;
        boolean usernameDuplicate = userRepository.existsById(signUp.getId());
        boolean phoneNumberDuplicate = userRepository.existsByPhoneNumber(signUp.getPhoneNumber());
        boolean emailDuplicate = userRepository.existsByEmail(signUp.getEmail());

        if (usernameDuplicate) {
            returnStr = Optional.of("이미 존재하는 아이디입니다.");
        } else if (phoneNumberDuplicate) {
            returnStr = Optional.of("이미 존재하는 전화번호입니다.");
        } else if (emailDuplicate) {
            returnStr = Optional.of("이미 존재하는 이메일입니다.");
        } else {
            returnStr = Optional.empty();
        }

        return returnStr;
    }

    // 회원가입
    @Transactional
    public User userSave(User user) {
        String salt = Encrypt.getSalt();
        String encodePassword = Encrypt.getEncrypt(user.getPassword(), salt);

        user.passwordEncrypt(encodePassword, salt);

        userRepository.save(user);

        return user;
    }

    // 로그인
    public Optional<User> login(String loginId, String password) {

        String loginPassword;

        Optional<User> user = userRepository.findById(loginId);

        if (user.isPresent()) {

            loginPassword = Encrypt.getEncrypt(password, user.get().getSalt());

            if (user.get().getPassword().equals(loginPassword)) {
                return user;
            } else {
                return Optional.empty();
            }
        } else {
            return Optional.empty();
        }
    }

}