package member.service;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import member.dto.Member;

public class MemberServiceImpl implements MemberService{
	public Connection getConnection() {
		final String DB_URL= "jdbc:oracle:thin:@orcl_high?TNS_ADMIN=C:/Users/lancelot/instantclient_19_13/network/admin";
		final String DB_USER = "admin";
		final String DB_PASSWORD = "Ehdfbf8749**";
		final String CLASS_NAME = "oracle.jdbc.OracleDriver";
		Connection conn = null;
		try {
            Class.forName(CLASS_NAME);
            System.out.println("driver load 성공!");
            
            conn = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
		}catch(ClassNotFoundException e) {
            System.out.println("driver load 실패!");
            e.printStackTrace();
		}catch (SQLException se) {
            System.out.println("SQLException 실패!");
            se.printStackTrace();
        }
		return conn;
	}
	@Override
	public Member findByMemberId(String memberId) {
		Connection conn = getConnection();
		ResultSet rs = null; //결과 받아서 처리할때
		PreparedStatement pstmt = null; //DB와 소통하는 통로
		Member member = new Member();
		
		try {
			String sql = "select * from member where memberid=?";
	        pstmt = conn.prepareStatement(sql);
	        pstmt.setString(1, memberId);
	        rs = pstmt.executeQuery();
	        
        	
            while(rs.next()) {
            	String id = rs.getString("memberId");
            	String password = rs.getString("memberId");
            	
            	member.setMemberId(id);
            	member.setPassword(password);
            }
		}catch(SQLException e) {
			e.printStackTrace();
		}
		

		return member;
	}

}
