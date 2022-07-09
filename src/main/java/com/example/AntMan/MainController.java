package com.example.AntMan;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Controller
public class MainController {
	
	/*
	@GetMapping("/community")
    public String community() {
        return "community_list";
    }
    */
	
	@GetMapping("/menu_select/{id}")
    public String menuSelect(@PathVariable("id") Integer id) {
		if (id == 1) { // 홈
			return "home";
		}
		else if (id == 2) { // 검색
        	return "search";
        }
		else if (id == 3) { // 커뮤니티
        	return "community";
        }
		return "redirect:/";
    }
	
	// id : 게시판구분 id
	// gubun : 글쓰기버튼, 내용조회 등
	@GetMapping("/community_sub")  
    public String community_sub(@RequestParam(name = "id") String menuId
                              , @RequestParam(name = "gubun") String gubun){
		if ( gubun.equals("edit")) {
        	
        } else if (gubun.equals("detail")) {
        	return "community_detail";
        }
		return "redirect:/";
    }

	
}
