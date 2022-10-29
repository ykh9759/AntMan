package com.example.AntMan.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.AntMan.domain.entity.Reply;

@Repository
public interface ReplyRepository  extends JpaRepository<Reply, Integer> {

	//게시물 id로 댓글 리스트 검색
	public List<Reply> findByboardNo(Integer boardNo);
	
}
