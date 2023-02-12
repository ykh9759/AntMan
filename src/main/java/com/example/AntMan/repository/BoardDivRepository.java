package com.example.AntMan.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.AntMan.domain.entity.BoardDivList;

public interface BoardDivRepository extends JpaRepository<BoardDivList, Integer> {
	
}
