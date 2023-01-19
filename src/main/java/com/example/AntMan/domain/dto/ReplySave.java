package com.example.AntMan.domain.dto;

import javax.persistence.Column;
import javax.validation.constraints.NotNull;

import com.example.AntMan.domain.entity.Reply;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ReplySave {
	
	private Integer boardNo;
	
	private Integer UserNo;
	
	private Integer ReplyNo;
	
	@Column(columnDefinition = "LONGTEXT")
	@NotNull(message = "댓글을 입력해 주세요.")
	private String comment;
	
	public Reply toEntity() {
        return Reply.builder()
                .BoardNo(boardNo)
                .UserNo(UserNo)//.UserNo(UserNo)
                .ReplyNo(null)//.ReplyNo(ReplyNo)
                .comment(comment)
                .build();
    }
	
}
