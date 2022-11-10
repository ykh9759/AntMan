package com.example.AntMan.domain.entity;

import javax.persistence.*;
import lombok.*;

@Getter
@EqualsAndHashCode(callSuper = false)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity(name = "board_div_list")
public class BoardDivList extends Time {

	@Id // 테이블 기본키
    @GeneratedValue(strategy = GenerationType.IDENTITY) // 기본키 자동증가
    private Integer no;
	
	private String name;
	
	@Builder
    public BoardDivList(Integer no, String name) {
		this.no = no;
        this.name = name;
	}
	
}
