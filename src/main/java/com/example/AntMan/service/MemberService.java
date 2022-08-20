package com.example.AntMan.service;

import com.example.AntMan.repository.MemberRepository;
import com.example.AntMan.domain.Member;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MemberService {
    @Autowired
    private MemberRepository memberRepository;

    public Member save(Member member) {

        member.setStatus(1);

        memberRepository.save(member);
        return member;
    }

}