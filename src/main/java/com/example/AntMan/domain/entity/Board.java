package com.example.AntMan.domain.entity;

import javax.persistence.*;
import lombok.*;

@Getter
@EqualsAndHashCode(callSuper = false)
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity(name = "board")
public class Board extends Time {

	@Id // 테이블 기본키
    @GeneratedValue(strategy = GenerationType.IDENTITY) // 기본키 자동증가
    private Integer no;
	
	private Integer boardDiv; // 게시판 분류번호
	
	private Integer userNo;
	
	private String title;
	
	@Column(columnDefinition = "LONGTEXT")
	private String contents;
	
	private Integer viewCount;
	
	@Builder
    public Board(Integer boardDiv, Integer userNo, String title, String contents, Integer viewCount) {
        this.boardDiv = boardDiv;
        this.userNo = userNo;
        this.title = title;
        this.contents = contents;
        this.viewCount = viewCount;
    }
	
}
