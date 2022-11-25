package com.example.AntMan.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.AntMan.domain.entity.File;

public interface FileRepository extends JpaRepository<File, Integer> {

}

