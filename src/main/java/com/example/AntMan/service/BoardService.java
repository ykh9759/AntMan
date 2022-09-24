package com.example.AntMan.service;

import com.example.AntMan.repository.BoardRepository;
import com.example.AntMan.domain.dto.EditList;
import com.example.AntMan.domain.entity.Board;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.validation.Errors;
import org.springframework.validation.FieldError;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

@Service
public class BoardService {
	@Autowired
    private BoardRepository boardRepository;
	
	public Map<String, String> validateHandling(Errors errors) {
        Map<String, String> validatorResult = new LinkedHashMap<>(); /* 유효성 검사에 실패한 필드 목록을 받음 */
        for (FieldError error : errors.getFieldErrors()) {
            String validKeyName = String.format("%s", error.getField());
            validatorResult.put(validKeyName, error.getDefaultMessage());
        }
        return validatorResult;
    }
	
	public List<EditList> getList(Integer id) {
		List<Board> board = boardRepository.findByboardNo(id);
		List<EditList> editDtoList = new ArrayList<>();
		
		for (Board boardEntity : board) {
			EditList editList = EditList.builder()
					.no(boardEntity.getNo())
					.boardNo(boardEntity.getBoardNo())
					.userNo(boardEntity.getUserNo())
					.title(boardEntity.getTitle())
					.build();
			
			editDtoList.add(editList);
		}
		
		return editDtoList;
	}
	
	// 글저장
	@Transactional
    public Board boardSave(Board board) {
		boardRepository.save(board);
        return board;
    }
	
}
