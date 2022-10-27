package repository;

import java.io.InputStream;
import java.util.List;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import domain.Board;

public class BoardDao {
	private SqlSessionFactory factory;
	private String mapper = "mybatis.mapper.board.";
	
	private static BoardDao dao = new BoardDao();
	private BoardDao() {
		try {
			String resource = "mybatis/config/mybatis-config.xml";
			InputStream in = Resources.getResourceAsStream(resource);
			factory = new SqlSessionFactoryBuilder().build(in);
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	public static BoardDao getInstance() {
		return dao;
	}
	
	public List<Board> selectBoardList() {
		SqlSession ss = factory.openSession();
		List<Board> boards = ss.selectList(mapper + "selectBoardList");
		ss.close();
		return boards;
	}
	
	public int selectAllBoardsCount() {
		SqlSession ss = factory.openSession();
		int count = ss.selectOne(mapper + "selectAllBoardsCount");
		ss.close();
		return count;
	}
	
	public int insertBoard(Board board) {
		SqlSession ss = factory.openSession(false);
		int result = 0;
		result = ss.insert(mapper + "insertBoard", board);
		if(result > 0) {
			ss.commit();
		}
		ss.close();
		return result;
	}
	
	public Board selectBoardNo(int boardNo) {
		SqlSession ss = factory.openSession();
		Board board = ss.selectOne(mapper + "selectBoardNo", boardNo);
		ss.close();
		return board;
	}
	
	public int updateBoard(Board board) {
		SqlSession ss = factory.openSession(false);
		int result = ss.update(mapper + "updateBoard", board);
		if(result > 0) {
			ss.commit();
		}
		ss.close();
		return result;
	}
	
	public int removeBoard(int boardNo) {
		SqlSession ss = factory.openSession(false);
		int result = ss.delete(mapper + "removeBoard", boardNo);
		if(result > 0) {
			ss.commit();
		}
		ss.close();
		return result;
	}
}
