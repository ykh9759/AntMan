package com.example.AntMan.domain.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Builder;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Table(name = "file")
@Entity
public class File extends Time {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer no;

    @Column(name = "board_no")
    private Integer boardNo;
    
    private String orgNm;
    
    private String savedNm;
    
    private String savedPath;

    @Builder
    public File(Integer no, String orgNm, String savedNm, String savedPath) {
        this.no = no;
        this.orgNm = orgNm;
        this.savedNm = savedNm;
        this.savedPath = savedPath;
    }
}

