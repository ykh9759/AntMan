package com.example.AntMan.service;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.example.AntMan.domain.dto.FileList;
import com.example.AntMan.domain.entity.FileData;
import com.example.AntMan.repository.FileRepository;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class FileService {

    @Value("${board.dir}")
    private String fileDir;

    @Autowired
    private final FileRepository fileRepository;

    public Integer saveFile(MultipartFile files, Integer boardNo) throws IOException {
        if (files.isEmpty()) {
            return null;
        }

        // 원래 파일 이름 추출
        String orgfileName = files.getOriginalFilename();

        String fileName = UUID.randomUUID().toString() + orgfileName.substring(orgfileName.lastIndexOf("."));

        // 파일을 불러올 때 사용할 파일 경로
        String filePath = fileDir;

        // 파일 엔티티 생성
        FileData file = FileData.builder()
                .boardNo(boardNo)
                .orgfileName(orgfileName)
                .fileName(fileName)
                .filePath(filePath)
                .build();

        files.transferTo(new File(filePath));

        // 데이터베이스에 파일 정보 저장
        FileData savedFile = fileRepository.save(file);

        return savedFile.getNo();
    }
    
    // 게시물의 파일 리스트
 	public List<FileList> getFileList(Integer id) {  	
 		List<FileData> fileList = fileRepository.findByboardNo(id);
 	
		List<FileList> fileDtoList = new ArrayList<>();
		
		for (FileData FileEntity : fileList) {
			
			FileList file = FileList.builder()
					.id(FileEntity.getNo())
					.boardNo(FileEntity.getBoardNo())
					.orgfileName(FileEntity.getOrgfileName())
					.fileName(FileEntity.getFileName())
					.filePath(FileEntity.getFilePath())
					.build();			
				
			fileDtoList.add(file);
		}

 		return fileDtoList;
 	}
    
}
