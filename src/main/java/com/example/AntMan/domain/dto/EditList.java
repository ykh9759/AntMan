package com.example.AntMan.domain.dto;

import java.util.Date;

import lombok.*;

@Getter
public class EditList {
	
	private Integer no;
	
	private Integer boardDiv; // 게시판 분류번호
	
	private Integer userNo;
	
	private String title;
	
	private Date created_time;
	
	private Date updated_time;
	
	@Builder
    public EditList(Integer no, Integer boardDiv, Integer userNo, String title, Date created_time, Date updated_time) {
        this.no = no;
        this.boardDiv = boardDiv;
        this.userNo = userNo;
        this.title = title;
        this.created_time = created_time;
        this.updated_time = updated_time;
    }
	
}
