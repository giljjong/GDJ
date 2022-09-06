package exam02_create;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class CreateSequenceMain {

	public static void main(String[] args) {
		
		Connection con = null;
		PreparedStatement ps = null;
		try {
			Class.forName("oracle.jdbc.OracleDriver");
			String url = "jdbc:oracle:thin:@localhost:1521:xe";
			String user = "scott";
			String password = "TIGER";
			con = DriverManager.getConnection(url, user, password);
			
			String sql = "CREATE SEQUENCE BOARD_SEQ NOCACHE";
			
			ps = con.prepareStatement(sql);
			ps.execute();
		} catch(Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if(con != null) con.close();
				if(ps != null) ps.close();
			} catch(SQLException e) {
				e.printStackTrace();
			}
		}
	}

}
