package com.example.AntMan.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;


import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
public class CommunityController {

	@GetMapping("/board-edit")
    public String signUp() {
        return "community/boardEdit";
    }	
	
	//TODO 글쓰기 등록 로직 만들기 
	@PostMapping("/board-edit/save")
	public String questionCreate() {
        return "redirect:/community";
    }
	
}
