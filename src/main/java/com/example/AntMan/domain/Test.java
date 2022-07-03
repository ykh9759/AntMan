package com.example.AntMan.domain;

import javax.persistence.Entity;

@Entity
public class Test {

    private int no;
    private String name;

    public int getNo() {
        return no;
    }

    public int setNo(int no) {
        this.no = no;
    }

    public int getName() {
        return name;
    }

    public int setName(String name) {
        this.name = name;
    }
}