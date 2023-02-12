package com.example.AntMan.domain.entity;

import javax.persistence.*;
import lombok.*;

@Getter
@Setter
@EqualsAndHashCode(callSuper = false)
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity(name = "reply")
public class Reply extends Time {
	
	@Id // 테이블 기본키
    @GeneratedValue(strategy = GenerationType.IDENTITY) // 기본키 자동증가
    private Integer no;
	
	private Integer boardNo;
	
	private Integer UserNo;
	
	private Integer ReplyNo;
	
	@Column(columnDefinition = "LONGTEXT")
	private String comment;
	
	@Builder
	public Reply(Integer BoardNo, Integer UserNo, Integer ReplyNo, String comment) {
		this.boardNo = BoardNo;
		this.UserNo = UserNo;
		this.ReplyNo = ReplyNo;
		this.comment = comment;
	}

}
