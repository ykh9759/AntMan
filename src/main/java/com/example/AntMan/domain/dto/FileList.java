package com.example.AntMan.domain.dto;

import lombok.Builder;
import lombok.Getter;

@Getter
public class FileList {

    private Integer id;

    private Integer boardNo;

    private String orgfileName;

    private String fileName;

    private String filePath;
    
    @Builder
	public FileList(Integer id, Integer boardNo, String orgfileName, String fileName, String filePath) {
		this.id = id;
		this.boardNo = boardNo;
		this.orgfileName = orgfileName;
		this.fileName = fileName;
		this.filePath = filePath;
	}
	
}
