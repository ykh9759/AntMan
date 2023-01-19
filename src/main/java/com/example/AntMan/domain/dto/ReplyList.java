package com.example.AntMan.domain.dto;

import java.util.Date;


import lombok.Builder;
import lombok.Getter;

@Getter
public class ReplyList {
	
	private Integer id;
	
	private Integer no;
	
	private String UserName;
	
	private String comment;
	
	private Date created_time;
	
	private Date updated_time;
	
	@Builder
	public ReplyList(Integer id, Integer no, String UserName, String comment, Date created_time, Date updated_time) {
		this.id = id;
		this.no = no;
		this.UserName = UserName;
		this.comment = comment;
		this.created_time = created_time;
		this.updated_time = updated_time;
	}

}
