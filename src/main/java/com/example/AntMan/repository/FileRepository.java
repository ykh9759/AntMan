package com.example.AntMan.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.AntMan.domain.entity.FileData;

public interface FileRepository extends JpaRepository<FileData, Integer> {

}
