package com.example.AntMan.service;

import com.example.AntMan.repository.BoardDivRepository;
import com.example.AntMan.repository.BoardRepository;
import com.example.AntMan.repository.ReplyRepository;
import com.example.AntMan.domain.dto.BoardDetail;
import com.example.AntMan.domain.dto.BoardList;
import com.example.AntMan.domain.dto.EditList;
import com.example.AntMan.domain.dto.ReplyList;
import com.example.AntMan.domain.entity.Board;
import com.example.AntMan.domain.entity.BoardDivList;
import com.example.AntMan.domain.entity.Reply;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.validation.Errors;
import org.springframework.validation.FieldError;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
public class BoardService {
	@Autowired
    private BoardRepository boardRepository;
	
	@Autowired
    private BoardDivRepository boardDivRepository;
	
	@Autowired
    private ReplyRepository replyRepository;
	
	public Map<String, String> validateHandling(Errors errors) {
        Map<String, String> validatorResult = new LinkedHashMap<>(); /* 유효성 검사에 실패한 필드 목록을 받음 */
        for (FieldError error : errors.getFieldErrors()) {
            String validKeyName = String.format("%s", error.getField());
            validatorResult.put(validKeyName, error.getDefaultMessage());
        }
        return validatorResult;
    }
	
	// 게시판 내용
	public BoardDetail getBoardDetail(Integer id) {  
	    Optional<Board> board = this.boardRepository.findById(id);
	        
	    BoardDetail boardDetail = BoardDetail.builder()
	        		.boardDiv(board.get().getBoardDiv())
	        		.userNo(board.get().getUserNo())
	        		.title(board.get().getTitle())
	        		.contents(board.get().getContents())
	        		.created_time(board.get().getCreated_time())
	        		.viewCount(board.get().getViewCount())
	        		.build();
	        
	    Optional<BoardDetail> BoardDetailDto = Optional.ofNullable(boardDetail);
	        
	    return BoardDetailDto.get();
	    /*
	        if (board.isPresent()) {
	            return board.get();
	        } else {
	            throw new DataNotFoundException("boardDetail not found");
	        }
	    */
	}
	
	// 게시물의 댓글 리스트
	public List<ReplyList> getReplyList(Integer id) {  	
		List<Reply> reply = replyRepository.findByboardNo(id);
		List<ReplyList> replyDtoList = new ArrayList<>();
		
		for (Reply ReplyEntity : reply) {
			ReplyList replyList = ReplyList.builder()
					.UserNo(ReplyEntity.getUserNo())
					.comment(ReplyEntity.getComment())
					.created_time(ReplyEntity.getCreated_time())
					.updated_time(ReplyEntity.getUpdated_time())
					.build();				
				
			replyDtoList.add(replyList);
		}
			
		return replyDtoList;
	}
	
	// 게시판 구분별 리스트
	public List<EditList> getList(Integer id) {
		List<Board> board = boardRepository.findByboardDiv(id);
		List<EditList> editDtoList = new ArrayList<>();
		
		for (Board boardEntity : board) {
			EditList editList = EditList.builder()
					.no(boardEntity.getNo())
					.boardDiv(boardEntity.getBoardDiv())
					.userNo(boardEntity.getUserNo())
					.title(boardEntity.getTitle())
					.created_time(boardEntity.getCreated_time())
					.updated_time(boardEntity.getUpdated_time())
					.build();
			
			editDtoList.add(editList);
		}
		
		return editDtoList;
	}
	
	//게시판 구분 리스트
	public List<BoardList> getDivList() {
		List<BoardDivList> boardDivList = boardDivRepository.findAll();
		List<BoardList> BoardDtoList = new ArrayList<>();
		
		for (BoardDivList boardEntity : boardDivList) {
			BoardList boardList = BoardList.builder()
					.no(boardEntity.getNo())
					.name(boardEntity.getName())
					.build();
			
			BoardDtoList.add(boardList);
		}
		
		return BoardDtoList;
	}
	
	// 게시판 이름
	public BoardDivList getDivName(Integer id) {  
        Optional<BoardDivList> boardName = this.boardDivRepository.findById(id);
        
        return boardName.get();
        
    }
	
	// 글저장
	@Transactional
    public Board boardSave(Board board) {
		boardRepository.save(board);
        return board;
    }
	
}
