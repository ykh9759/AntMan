package com.example.AntMan.controller;

import java.util.List;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.ui.Model;

import com.example.AntMan.domain.dto.Edit;
import com.example.AntMan.domain.entity.Board;
import com.example.AntMan.service.BoardService;
import com.example.AntMan.utils.Utils;


@Controller
public class CommunityController {
	
	@Autowired
    BoardService boardService;

	Board board;
	
	@RequestMapping("/community")
    public String community(Model model) {
        List<Board> boardList = this.boardService.getList();
        model.addAttribute("boardList", boardList);
		return "community/board";
    }
	
	@GetMapping("/board-edit")
    public String signUp() {
        return "community/boardEdit";
    }	
	
	//@Valid SignUp signUp, Errors errors, HttpServletResponse response
	//TODO 글쓰기 등록 로직 만들기 
	@PostMapping("/board-edit/save")
	public void questionCreate(@Valid Edit edit, Errors errors, HttpServletResponse response) throws Exception {
        
		//TODO 에러확인 만들기 (null 체크)
		
		board = edit.toEntity();

        boardService.boardSave(board);

        Utils.alertAndMovePage(response, "등록 되었습니다.", "/community");
		
		return;
    }
	
}
