package com.example.AntMan.controller;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.ui.Model;

import com.example.AntMan.domain.dto.BoardDetail;
import com.example.AntMan.domain.dto.BoardList;
import com.example.AntMan.domain.dto.Edit;
import com.example.AntMan.domain.dto.EditList;
import com.example.AntMan.domain.dto.ReplyList;
import com.example.AntMan.domain.dto.ReplySave;
import com.example.AntMan.domain.entity.Board;
import com.example.AntMan.domain.entity.BoardDivList;
import com.example.AntMan.domain.entity.Member;
import com.example.AntMan.domain.entity.Reply;
import com.example.AntMan.service.BoardService;
import com.example.AntMan.utils.Alert;


@Controller
public class CommunityController {
	
	@Autowired
    BoardService boardService;

	Board board;
	
	Reply reply;
	
	@RequestMapping(value = "/community/detail/{id}")
	public String detail(HttpServletRequest request, HttpServletResponse response, Model model, @PathVariable("id") Integer id) {
		// 게시물 디테일
		BoardDetail boardDetail = this.boardService.getBoardDetail(id);
		model.addAttribute("boardDetail", boardDetail);
		// 게시물 아이디의 댓글
		List<ReplyList> replyList = this.boardService.getReplyList(id);
		model.addAttribute("replyList", replyList);		
		// 게시물 아이디
		model.addAttribute("boardNo", id);
		
		HttpSession session = request.getSession(false);
        Member member = null;

        if (session != null) {
            member = (Member) session.getAttribute("LOGIN_MEMBER");
        } else {
            Alert.alertAndMovePage(response, "로그인 후 이용이 가능합니다.", "/");
            return "";
        }
        model.addAttribute("member", member);
		
		
		return "community/community_detail";
	}
	
	@GetMapping("/community")
    public String community(HttpServletRequest request, HttpServletResponse response, Model model, @RequestParam String id) {
		List<EditList> editList = this.boardService.getList(Integer.valueOf(id));
		List<BoardList> boardList = this.boardService.getDivList();
		BoardDivList boardName = this.boardService.getDivName(Integer.valueOf(id));
		model.addAttribute("boardName", boardName);
        model.addAttribute("editList", editList);
        model.addAttribute("boardList", boardList);
        
        HttpSession session = request.getSession(false);
        Member member = null;

        if (session != null) {
            member = (Member) session.getAttribute("LOGIN_MEMBER");
        }
        
        model.addAttribute("member", member);
        
		return "community/board";
    }
	
	@GetMapping("/board-edit")
    public String boardEdit(HttpServletRequest request, HttpServletResponse response, Model model) {
		
		HttpSession session = request.getSession(false);
        Member member = null;

        if (session != null) {
            member = (Member) session.getAttribute("LOGIN_MEMBER");
        } else {
            Alert.alertAndMovePage(response, "로그인 후 이용이 가능합니다.", "/");
            return "";
        }
        model.addAttribute("member", member);
		
        return "community/boardEdit";
    }	
	
	@PostMapping("/board-edit/save")
	public void EditCreate(@Valid Edit edit, Errors errors, HttpServletResponse response) throws Exception {
        
		if (errors.hasErrors()) {
            Map<String, String> validatorResult = boardService.validateHandling(errors);

            for (String key : validatorResult.keySet()) {
            	Alert.alertAndBackPage(response, validatorResult.get(key));
                return;
            }
        }
		
		board = edit.toEntity();

        boardService.boardSave(board);

        Alert.alertAndMovePage(response, "등록 되었습니다.", "/community?id=1");
		
		return;
    }
	
	// 댓글 SAVE
	@PostMapping("/community/reply/save/{id}")
	public void replyCreate(@Valid ReplySave replySave, @PathVariable("id") Integer id, Errors errors, HttpServletResponse response) throws Exception {
        
		if (errors.hasErrors()) {
            Map<String, String> validatorResult = boardService.validateHandling(errors);

            for (String key : validatorResult.keySet()) {
            	Alert.alertAndBackPage(response, validatorResult.get(key));
                return;
            }
        }
		
		replySave.setBoardNo(id);
		
		reply = replySave.toEntity();

        boardService.replySave(reply);

        Alert.alertAndMovePage(response, "등록 되었습니다.", "/community/detail/"+id.toString());
		
		return;
    }
	
}
