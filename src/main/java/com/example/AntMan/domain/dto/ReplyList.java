package com.example.AntMan.domain.dto;

import java.util.Date;


import lombok.Builder;
import lombok.Getter;

@Getter
public class ReplyList {
	
	private String UserName;
	
	private String comment;
	
	private Date created_time;
	
	private Date updated_time;
	
	@Builder
	public ReplyList(String UserName, String comment, Date created_time, Date updated_time) {
		this.UserName = UserName;
		this.comment = comment;
		this.created_time = created_time;
		this.updated_time = updated_time;
	}

}
