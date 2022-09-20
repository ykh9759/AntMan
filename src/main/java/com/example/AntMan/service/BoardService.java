package com.example.AntMan.service;

import com.example.AntMan.repository.BoardRepository;
import com.example.AntMan.domain.entity.Board;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.validation.Errors;
import org.springframework.validation.FieldError;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

@Service
public class BoardService {
	@Autowired
    private BoardRepository boradRepository;
	
	public Map<String, String> validateHandling(Errors errors) {
        Map<String, String> validatorResult = new LinkedHashMap<>(); /* 유효성 검사에 실패한 필드 목록을 받음 */
        for (FieldError error : errors.getFieldErrors()) {
            String validKeyName = String.format("%s", error.getField());
            validatorResult.put(validKeyName, error.getDefaultMessage());
        }
        return validatorResult;
    }
	
	public List<Board> getList(Integer id) {
		return this.boradRepository.findByboardNo(id);
	}
	
	// 글저장
	@Transactional
    public Board boardSave(Board board) {
		boradRepository.save(board);
        return board;
    }
	
}
