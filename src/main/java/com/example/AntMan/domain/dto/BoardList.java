package com.example.AntMan.domain.dto;

import lombok.*;

@Getter
public class BoardList {
	
	private Integer no;
	
	private String name;
	
	@Builder
    public BoardList(Integer no, String name) {
        this.no = no;
        this.name = name;
    }

}
