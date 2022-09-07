package exam06_select;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import domain.Board;

public class SelectListMain {

	public static void main(String[] args) {
		
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		
		try {
			Class.forName("oracle.jdbc.OracleDriver");
			String url = "jdbc:oracle:thin:@localhost:1521:xe";
			String user = "SCOTT";
			String password = "TIGER";
			con = DriverManager.getConnection(url, user, password);
			
			String sql = "SELECT BOARD_NO, TITLE, CONTENT, HIT, CREATE_DATE FROM BOARD";
			ps = con.prepareStatement(sql);
			rs = ps.executeQuery();
			
			List<Board> boards = new ArrayList<>();
			
			while(rs.next()) {
				Board board = new Board();
				board.setBoard_no(rs.getInt("BOARD_NO"));
				board.setTitle(rs.getString("TITLE"));
				board.setContent(rs.getString("CONTENT"));
				board.setHit(rs.getInt("HIT"));
				board.setCreate_date(rs.getDate("CREATE_DATE"));
				
				boards.add(board);
			}
			
			for(int i = 0; i < boards.size(); i++) {
				System.out.println(boards.get(i));
			}
			
			/*for(Board b : boards) {
				System.out.println(b);
			}*/
			
		} catch(Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if(rs != null) rs.close();
				if(ps != null) ps.close();
				if(con != null) con.close();
			} catch(Exception e) {
				e.printStackTrace();
			}
		}
	}

}
