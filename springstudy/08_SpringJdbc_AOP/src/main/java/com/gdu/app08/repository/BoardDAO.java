package com.gdu.app08.repository;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementSetter;
import org.springframework.stereotype.Repository;

import com.gdu.app08.domain.BoardDTO;

@Repository
public class BoardDAO {

	// JdbcTemplate
	// Connection, PreparedStatement, ResultSet을 내부에서 사용하는 Spring Class
	// DriverManagerDataSource에 의해서 Connection pool 방식으로 동작

	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	public List<BoardDTO> selectAllBoards() {
		String sql = "SELECT BOARD_NO, TITLE, CONTENT, WRITER, CREATE_DATE, MODIFY_DATE FROM BOARD ORDER BY BOARD_NO DESC";
	
		return jdbcTemplate.query(sql, new BeanPropertyRowMapper<>(BoardDTO.class));
	}
	
	public BoardDTO selectBoardByNo(int board_no) {
		String sql = "SELECT BOARD_NO, TITLE, CONTENT, WRITER, CREATE_DATE, MODIFY_DATE FROM BOARD WHERE BOARD_NO = ?";
		return jdbcTemplate.queryForObject(sql, new BeanPropertyRowMapper<>(BoardDTO.class), board_no);
	}
	
	public int insertBoard(final BoardDTO board) {
		String sql = "INSERT INTO BOARD(BOARD_NO, TITLE, CONTENT, WRITER, CREATE_DATE, MODIFY_DATE)"
				+ " VALUES(BOARD_SEQ.NEXTVAL, ?, ?, ?, TO_CHAR(SYSDATE, 'YYYY-MM-DD'), TO_CHAR(SYSDATE, 'YYYY-MM-DD'))";
		
		return jdbcTemplate.update(sql, new PreparedStatementSetter() {
			
			@Override
			public void setValues(PreparedStatement ps) throws SQLException {
				
				ps.setString(1, board.getTitle());
				ps.setString(2, board.getContent());
				ps.setString(3, board.getWriter());
				
			}
		});
	}
	
	public int updateBoard(final BoardDTO board) {
		String sql = "UPDATE BOARD SET TITLE = ?, CONTENT = ?, MODIFY_DATE = TO_CHAR(SYSDATE, 'YYYY-MM-DD') WHERE BOARD_NO = ?";

		return jdbcTemplate.update(sql, new PreparedStatementSetter() {
			
			@Override
			public void setValues(PreparedStatement ps) throws SQLException {
				
				ps.setString(1, board.getTitle());
				ps.setString(2, board.getContent());
				ps.setInt(3, board.getBoard_no());
				
			}
		});
	}
	
	public int deleteBoard(final int board_no) {
		String sql = "DELETE FROM BOARD WHERE BOARD_NO = ?";

		return jdbcTemplate.update(sql, new PreparedStatementSetter() {
			
			@Override
			public void setValues(PreparedStatement ps) throws SQLException {
				ps.setInt(1, board_no);
			}
		});
	}
	
}