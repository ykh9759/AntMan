package com.example.AntMan.service;

import com.example.AntMan.repository.BoardRepository;
import com.example.AntMan.domain.entity.Board;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BoardService {
	@Autowired
    private BoardRepository boradRepository;
	
	public List<Board> getList() {
		// 일단 테스트로 전체조회 
		return this.boradRepository.findAll();
	}
	
	// 글저장
	@Transactional
    public Board boardSave(Board board) {
		boradRepository.save(board);
        return board;
    }
	
}
