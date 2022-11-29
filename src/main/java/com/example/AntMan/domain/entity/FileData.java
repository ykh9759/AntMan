package com.example.AntMan.domain.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.NonNull;

@Getter
@NoArgsConstructor
@Table(name = "file")
@Entity
public class FileData extends Time {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer no;

    @Column(name = "board_no")
    private Integer boardNo;

    @Column(name = "org_file_name")
    private String orgfileName;

    @Column(name = "file_name")
    private String fileName;

    @Column(name = "file_path")
    private String filePath;

    @Builder
    public FileData(Integer no, Integer boardNo, String orgfileName, String fileName, String filePath) {
        this.no = no;
        this.boardNo = boardNo;
        this.orgfileName = orgfileName;
        this.fileName = fileName;
        this.filePath = filePath;
    }
}
