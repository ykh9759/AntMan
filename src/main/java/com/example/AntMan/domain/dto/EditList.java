package com.example.AntMan.domain.dto;

import lombok.*;

@Getter
public class EditList {
	
	private Integer no;
	
	private Integer boardDiv; // 게시판 분류번호
	
	private Integer userNo;
	
	private String title;
	
	@Builder
    public EditList(Integer no, Integer boardDiv, Integer userNo, String title) {
        this.no = no;
        this.boardDiv = boardDiv;
        this.userNo = userNo;
        this.title= title;
    }
	
}
