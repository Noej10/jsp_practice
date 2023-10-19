package com.kh.member.model.dao;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Properties;

import com.kh.common.JDBCTemplate;
import com.kh.member.model.vo.Member;

public class MemberDao {
	
	private Properties prop = new Properties();
	
	public MemberDao() {
		String filePath = JDBCTemplate.class.getResource("/db/sql/member-mapper.xml").getPath();
		
		try {
			prop.loadFromXML(new FileInputStream(filePath));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public Member loginMember(Connection conn, String userId, String userPwd) {
		// SELECT문으로 => Member객체(한 행) 조회
		
		Member m = null;
		PreparedStatement pstmt = null;
		ResultSet rSet = null;
		String sql = prop.getProperty("loginMember");
		
		
		
		try {
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setString(1, userId);
			pstmt.setString(2, userPwd);
			
			rSet = pstmt.executeQuery();
			
			if(rSet.next()) {
				m = new Member(
						rSet.getInt("USER_NO"),	
						rSet.getString("USER_ID"),
						rSet.getString("USER_PWD"),
						rSet.getString("USER_NAME"),
						rSet.getString("PHONE"),
						rSet.getString("EMAIL"),
						rSet.getString("ADDRESS"),
						rSet.getString("INTEREST"),
						rSet.getDate("ENROLL_DATE"),
						rSet.getDate("MODIFY_DATE"),
						rSet.getString("STATUS")
						);
				

			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JDBCTemplate.close(rSet);
			JDBCTemplate.close(pstmt);
		}
		
		
		
		
		return m;
		
	}
	
	
}
