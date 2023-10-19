package com.kh.board.model.dao;

import static com.kh.common.JDBCTemplate.close;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Properties;

import com.kh.board.model.vo.Board;
import com.kh.common.medel.vo.PageInfo;



public class BoardDao {
	
	private Properties prop = new Properties();
	
	
	public BoardDao(){
		String filePath = BoardDao.class.getResource("/db/sql/board-mapper.xml").getPath();
		
		try {
			prop.loadFromXML(new FileInputStream(filePath));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	
	
	public int selectListCount(Connection conn) {
		// select = > ResultSet => int
		int listCount = 0;
		PreparedStatement pstmt = null;
		ResultSet rSet = null;
		String sql = prop.getProperty("selectListCount");
		
		try {
			pstmt = conn.prepareStatement(sql);
			rSet = pstmt.executeQuery();
			
			if(rSet.next()) {
				listCount = rSet.getInt("COUNT");
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(rSet);
			close(pstmt);
		}
		
		
		return listCount;
	}
	
	public ArrayList<Board> selectList(Connection conn,PageInfo pi){
		
		ArrayList<Board> list = new ArrayList<>();
		PreparedStatement pstmt = null;
		ResultSet rSet = null;
		String sql = prop.getProperty("selectList");
		
		try {
			pstmt = conn.prepareStatement(sql);
			
			// 시작값 : (currentPage - 1) / * boardLimit + 1
			// 끝값 : 시작값 + boardLimit - 1
			
			int startRow = (pi.getCurrentPage() - 1) * pi.getBoardLimit() + 1;
			int endRow = startRow + pi.getBoardLimit() - 1;
			
			pstmt.setInt(1, startRow);
			pstmt.setInt(2, endRow);
			
			rSet = pstmt.executeQuery();
			
			while(rSet.next()) {
				list.add(new Board(
									rSet.getInt("BOARD_NO"),
									rSet.getString("CATEGORY_NAME"),
									rSet.getString("BOARD_TITLE"),
									rSet.getString("USER_ID"),
									rSet.getInt("COUNT"),
									rSet.getString("CREATE_DATE")
									));
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(rSet);
			close(pstmt);
		}
		

		return list;
	}
	
	
	
}
