package com.example.AntMan.repository;

import com.example.AntMan.domain.entity.User;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {

    // findBy뒤에 컬럼명을 붙여주면 이를 이용한 검색이 가능하다
    public Optional<User> findById(String id);
    
    public Optional<User> findByno(Integer no);

    boolean existsById(String id);

    boolean existsByPhoneNumber(String phoneNumber);

    boolean existsByEmail(String email);

}