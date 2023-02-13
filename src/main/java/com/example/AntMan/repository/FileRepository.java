package com.example.AntMan.repository;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.example.AntMan.domain.entity.FileData;

@Repository
public interface FileRepository extends JpaRepository<FileData, Integer> {

	public List<FileData> findByboardNo(Integer id);
	
	@Transactional
    @Modifying
    @Query("delete from FileData where board_no = :no")
    public void deleteByboardNo(@Param("no") Integer boardNo);

}
