package com.example.AntMan.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.ui.Model;

import org.springframework.jdbc.datasource.DataSourceUtils;
import javax.sql.DataSource;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


@Controller
public class TestController {

    @RequestMapping("/test")
    public String test(Model model) {

        String sql = "select * from test";

        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;

        conn = getConnection();
        pstmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);

        rs  = pstmt.executeQuery();

        int no = rs.getInt(1);
        String name = rs.getString(2);

        model.addAttribute("no",no);
        model.addAttribute("name",name);

        return "test";
    }
}