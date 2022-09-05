package com.example.AntMan.repository;

import com.example.AntMan.domain.entity.Board;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BoradRepository extends JpaRepository<Board, Integer> {

}
