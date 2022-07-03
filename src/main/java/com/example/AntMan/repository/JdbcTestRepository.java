// package com.example.AntMan.repository;

// public class JdbcTestRepository implements TestRepository {

//     private final DataSource data;

//     public JdbcTestRepository(DataSource data) {
//         this.data = data;
//     }

//     @Override
//     public Test save(Test test) {
//         String sql = "insert into test(id,name) values(?,?)";

//         Connection conn = data.getConnection();

//         PreparedStatement pstmt = conn.preparedStatement(sql);
//         pstmt.setString(1, test.getNo());
//         pstmt.setString(2, test.getName());

//         pstmt.executeUpdate();

//         return null;
//     }

//     @Override
//     public Test save(Test test) {
        
//     }

//     @Override
//     public Test save(Test test) {
        
//     }

//     @Override
//     public Test save(Test test) {
        
//     }
    
// }