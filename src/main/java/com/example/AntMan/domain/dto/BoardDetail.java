package com.example.AntMan.domain.dto;

import java.util.Date;

import lombok.*;

@Getter
public class BoardDetail {
	
	private Integer boardDiv; 
	
	private Integer no;
	
	private String userName;
	
	private String title;
	
	private String contents;
	
	private Date created_time;
	
	private Integer viewCount;
	
	@Builder
    public BoardDetail(Integer boardDiv, Integer no, String userName, String title, String contents, Date created_time, Integer viewCount) {
        this.boardDiv = boardDiv;
        this.no = no;
        this.userName = userName;
        this.title = title;
        this.contents = contents;
        this.created_time = created_time;
        this.viewCount = viewCount;
    }

}
