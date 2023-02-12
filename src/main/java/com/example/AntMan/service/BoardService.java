package com.example.AntMan.service;

import com.example.AntMan.repository.BoardDivRepository;
import com.example.AntMan.repository.BoardRepository;
import com.example.AntMan.repository.ReplyRepository;
import com.example.AntMan.repository.UserRepository;
import com.example.AntMan.domain.dto.BoardDetail;
import com.example.AntMan.domain.dto.BoardList;
import com.example.AntMan.domain.dto.EditList;
import com.example.AntMan.domain.dto.ReplyList;
import com.example.AntMan.domain.entity.Board;
import com.example.AntMan.domain.entity.BoardDivList;
import com.example.AntMan.domain.entity.Reply;
import com.example.AntMan.domain.entity.User;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.validation.Errors;
import org.springframework.validation.FieldError;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
public class BoardService {
	@Autowired
    private UserRepository userRepository;
	
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
	    
	    Optional<User> user = userRepository.findByno(board.get().getUserNo());  
	        
	    BoardDetail boardDetail = BoardDetail.builder()
	        		.boardDiv(board.get().getBoardDiv())
	        		.no(board.get().getUserNo())
	        		.userName(user.get().getName())
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
			
			User user = userRepository.findByno(ReplyEntity.getUserNo()).get();
			
			ReplyList replyList = ReplyList.builder()
					.id(ReplyEntity.getNo())
					.no(ReplyEntity.getUserNo())
					.UserName(user.getName())
					.comment(ReplyEntity.getComment())
					.created_time(ReplyEntity.getCreated_time())
					.updated_time(ReplyEntity.getUpdated_time())
					.build();				
				
			replyDtoList.add(replyList);
		}
			
		return replyDtoList;
	}
	
	/*
	// 게시판 구분별 리스트
	public List<EditList> getList(Integer id) {
		List<Board> board = boardRepository.findByboardDiv(id);
		List<EditList> editDtoList = new ArrayList<>();
		
		for (Board boardEntity : board) {
		
	        User user = userRepository.findByno(boardEntity.getUserNo()).get();        
			
			EditList editList = EditList.builder()
					.no(boardEntity.getNo())
					.boardDiv(boardEntity.getBoardDiv())
					.userName(user.getName())
					.title(boardEntity.getTitle())
					.created_time(boardEntity.getCreated_time())
					.updated_time(boardEntity.getUpdated_time())
					.build();
			
			editDtoList.add(editList);
		}
		
		return editDtoList;
	}
	*/
	
	// 게시판 구분별 리스트
	public Page<EditList> getList(Integer id, int page) {
		
		List<Sort.Order> sorts = new ArrayList<>();
        sorts.add(Sort.Order.desc("no"));
		
		Pageable pageable = PageRequest.of(page, 10, Sort.by(sorts));
		
		Page<Board> board = boardRepository.findByboardDiv(id, pageable);
		//List<EditList> editDtoList = new ArrayList<>();
		
		Page<EditList> editDtoList = board.map(m -> EditList.builder()
				.no(m.getNo())
				.boardDiv(m.getBoardDiv())
				.userName(userRepository.findByno(m.getUserNo()).get().getName())
				.title(m.getTitle())
				.created_time(m.getCreated_time())
				.updated_time(m.getUpdated_time())
				.build());
		
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
	
	// 댓글저장
	@Transactional
	public Reply replySave(Reply reply) {
		replyRepository.save(reply);
	    return reply;
	}
	
	// 댓글수정
	public void replymodify(Reply reply, String comment) {
		reply.setComment(comment);
		this.replyRepository.save(reply);
	}
	
	// 댓글삭제 
	public void replydelete(Reply reply) {
		this.replyRepository.delete(reply);
	}
	
	// 게시글 삭제 
	public void boarddelete(Board board) {
		this.boardRepository.delete(board); // 게시물 삭제
		this.replyRepository.deleteByboardNo(board.getNo()); // 게시물에 해당하는 댓글삭제
	}
	
	// 게시글 수정
	public void boardmodify(Board board, String title, String contents) {
		board.setTitle(title);
		board.setContents(contents);
		this.boardRepository.save(board);
	}
	
	// 댓글
	public Reply getReply(Integer id) {
		Optional<Reply> reply = this.replyRepository.findById(id);
		return reply.get();		
	}
	
	// 게시글
	public Board getBoard(Integer id) {
		Optional<Board> board = this.boardRepository.findById(id);
		return board.get();		
	}
	
	
}
