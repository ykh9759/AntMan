package com.example.AntMan.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.AntMan.domain.entity.BoardDivList;

@Repository
public interface BoardDivRepository extends JpaRepository<BoardDivList, Integer> {
	
}
