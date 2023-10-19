package com.kh.member.model.dao;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Properties;

import static com.kh.common.JDBCTemplate.*;
import com.kh.member.model.vo.Member;

public class MemberDao {
	
	private Properties prop = new Properties();
	
	public MemberDao() {
		String filePath = MemberDao.class.getResource("/db/sql/member-mapper.xml").getPath();
		
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
			close(rSet);
			close(pstmt);
		}
		
		
		
		
		return m;
		
	}
	
	
	public int insertMember(Connection conn,Member m) {
	
		PreparedStatement pstmt = null;
		int result = 0;
		String sql = prop.getProperty("insertMember");
		
		try {
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setString(1, m.getUserId());
			pstmt.setString(2, m.getUserPwd());
			pstmt.setString(3, m.getUserName());
			pstmt.setString(4, m.getPhone());
			pstmt.setString(5, m.getEmail());
			pstmt.setString(6, m.getAddress());
			pstmt.setString(7, m.getInterest());
			
			result = pstmt.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(pstmt);
		}
		
		
		
		return result;
		
	}
	
	// update문실행 -> 처리된 행수 반환 -> 반환된 행수로 트랜잭션 처리
	public int updateMember(Connection conn, Member m) {
		
		int result = 0;
		PreparedStatement pstmt = null;
		String sql = prop.getProperty("updateMember");
		
		try {
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setString(1, m.getUserName());
			pstmt.setString(2, m.getPhone());
			pstmt.setString(3, m.getEmail());
			pstmt.setString(4, m.getAddress());
			pstmt.setString(5, m.getInterest());
			pstmt.setString(6, m.getUserId());
			
			result = pstmt.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(pstmt);
		}
		
		
		return result;
	}
	
		//select문 실행 => ResultSet(한행) => Member에 담아서 전달
	public Member selectMember(Connection conn, String userId) {
		Member updateMem = null;
		PreparedStatement pstmt = null;
		ResultSet rSet = null;
		String sql = prop.getProperty("selectMember");
		
		try {
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setString(1, userId);
			
			rSet = pstmt.executeQuery();
			
			if(rSet.next()) {
			updateMem = new Member(
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
			close(rSet);
			close(pstmt);
		}
		
		
		return updateMem;
		
	}
	
	public int updatePwd(Connection conn,String userId,String userPwd,String updatePwd) {
		
		int result = 0;
		PreparedStatement pstmt = null;
		String sql = prop.getProperty("updatePwd");
		
		try {
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setString(1, updatePwd);
			pstmt.setString(2, userId);
			pstmt.setString(3, userPwd);
			
			result = pstmt.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(pstmt);
		}
		
		
		return result;
	}
	
	
	public int deleteMember(Connection conn, Member m) {
		int result = 0;
		PreparedStatement pstmt = null;
		String sql = prop.getProperty("deleteMember");
		
		try {
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setString(1, m.getUserId());
			pstmt.setString(2, m.getUserPwd());
			
			result = pstmt.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(pstmt);
		}
		
		
		return result;
		
	}
	
}
