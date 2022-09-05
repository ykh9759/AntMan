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
	
	private Integer board_no; // 게시판 분류번호
	
	private Integer user_no;
	
	private String title;
	
	@Column(columnDefinition = "LONGTEXT")
	private String contents;
	
	private Integer view_count;
	
	@Builder
    public Board(Integer board_no, Integer user_no, String title, String contents, Integer view_count) {
        this.board_no = board_no;
        this.user_no = user_no;
        this.title = title;
        this.contents = contents;
        this.view_count = view_count;
    }
	
}
