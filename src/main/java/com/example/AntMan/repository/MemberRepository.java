package com.example.AntMan.repository;

import com.example.AntMan.domain.Member;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MemberRepository extends JpaRepository<Member, Integer> {
    // 비워있어도 잘 작동함.
    // long 이 아니라 Long으로 작성. ex) int => Integer 같이 primitive형식 사용못함

    // findBy뒤에 컬럼명을 붙여주면 이를 이용한 검색이 가능하다
    public List<Member> findById(String id);

    public List<Member> findByName(String name);

    boolean existsById(String id);

    boolean existsByPhoneNumber(String phoneNumber);

    boolean existsByEmail(String email);

}