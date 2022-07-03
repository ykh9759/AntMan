package com.example.AntMan.repository;

import javax.slq.DataSource;
import java.sql.Connection;
import java.util.List;
import java.util.Optional;

public class JdbcTestRepository {

    private final DataSource data;

    public JdbcTestRepository(DataSource data) {
        this.data = data;
    }

    @Override
    public Test selectTest(Test test) {
        String sql = "select * from test";

        Connection conn = data.getConnection();

        PreparedStatement pstmt = conn.preparedStatement(sql);
        pstmt.setString(1, test.getNo());
        pstmt.setString(2, test.getName());

        pstmt.executeUpdate();

        return null;
    }    
}