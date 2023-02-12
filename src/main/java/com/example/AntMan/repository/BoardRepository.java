package com.example.AntMan.repository;

import com.example.AntMan.domain.entity.Board;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

@Repository
public interface BoardRepository extends JpaRepository<Board, Integer> {
	
	//게시판 id로 게시물 리스트 검색
	public Page<Board> findByboardDiv(Integer boardDiv, Pageable pageable);
	
}
