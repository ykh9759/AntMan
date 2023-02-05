package com.example.AntMan;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.example.AntMan.domain.entity.Board;
import com.example.AntMan.service.BoardService;

@SpringBootTest
class AntManApplicationTests {
	
	@Autowired
	private BoardService boardService;

	@Test
	void contextLoads() {
	}
	
	@Test
    void testJpa() {
				
        for (int i = 1; i <= 300; i++) {
        	
        	String subject = String.format("테스트 데이터입니다:[%03d]", i);
            String content = "내용무";
        	
        	Board board = Board.builder()
                    .boardDiv(1)
                    .userNo(2)
                    .title(subject)
                    .contents(content)
                    .viewCount(0)
                    .build();
            
            this.boardService.boardSave(board);
        }
    }

}
