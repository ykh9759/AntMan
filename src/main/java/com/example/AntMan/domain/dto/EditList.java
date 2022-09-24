package com.example.AntMan.domain.dto;

import lombok.*;

@Getter
public class EditList {
	
	private Integer no;
	
	private Integer boardNo; // 게시판 분류번호
	
	private Integer userNo;
	
	private String title;
	
	@Builder
    public EditList(Integer no, Integer boardNo, Integer userNo, String title) {
        this.no = no;
        this.boardNo = boardNo;
        this.userNo = userNo;
        this.title= title;
    }
	
}
