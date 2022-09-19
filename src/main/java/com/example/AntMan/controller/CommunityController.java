package com.example.AntMan.controller;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
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
	
	/*
	@RequestMapping("/community")
    public String community(Model model) {
        List<Board> boardList = this.boardService.getList();
        model.addAttribute("boardList", boardList);
		return "community/board";
    }
    */
    
	@GetMapping("/community")
    public String community(Model model, @RequestParam String id) {
        // TODO getList(id)로 수정하기
		List<Board> boardList = this.boardService.getList();
        model.addAttribute("boardList", boardList);
		return "community/board";
    }
	
	@GetMapping("/board-edit")
    public String boardEdit() {
        return "community/boardEdit";
    }	
	
	@PostMapping("/board-edit/save")
	public void questionCreate(@Valid Edit edit, Errors errors, HttpServletResponse response) throws Exception {
        
		if (errors.hasErrors()) {
            Map<String, String> validatorResult = boardService.validateHandling(errors);

            for (String key : validatorResult.keySet()) {
                Utils.alertAndBackPage(response, validatorResult.get(key));
                return;
            }
        }
		
		board = edit.toEntity();

        boardService.boardSave(board);

        Utils.alertAndMovePage(response, "등록 되었습니다.", "/community");
		
		return;
    }
	
}
