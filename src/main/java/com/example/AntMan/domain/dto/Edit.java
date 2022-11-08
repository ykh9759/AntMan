package com.example.AntMan.domain.dto;

import javax.validation.constraints.NotBlank;

import com.example.AntMan.domain.entity.Board;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Edit {
	
	//@NotBlank(message = "게시판을 선택해주세요.") <- integer NotBlank 쓰면 오류나는듯
	private Integer boardDiv; // 게시판 분류번호
	
	// private Integer user_no;
	
	@NotBlank(message = "제목을 입력해 주세요.")
	private String title;
	
	@NotBlank(message = "내용을 입력해 주세요.")
	private String contents;
	
	public Board toEntity() {
		return Board.builder()
                .boardDiv(boardDiv)
                .userNo(0) // test
                .title(title)
                .contents(contents)
                .viewCount(0)
                .build();
	}
	
}
