package exam05_delete;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.util.Scanner;

public class DeleteMain {

	public static void main(String[] args) {
		
		Connection con = null;
		PreparedStatement ps = null;
		
		try {
			// 삭제할 게시글 번호 입력 받기
				Scanner sc = new Scanner(System.in);
				System.out.print("삭제할 게시글 번호를 입력해주세요 >>> ");
				int board_no = sc.nextInt();
				sc.nextLine();
				
			// Connection 생성
				Class.forName("oracle.jdbc.OracleDriver");
				String url = "jdbc:oracle:thin:@localhost:1521:xe";
				String user = "SCOTT";
				String password = "TIGER";
				con = DriverManager.getConnection(url, user, password);
				
			// 쿼리문 생성
				String sql = "DELETE FROM BOARD WHERE BOARD_NO = ?";
				
			// PreparedStatement 객체 생성
				ps = con.prepareStatement(sql);
			
			// 쿼리문의 ?에 변수 전달하기
				ps.setInt(1, board_no);
				
			// 쿼리문 실행
				int result = ps.executeUpdate();
				
			// 실행결과
			// 삭제 성공, 삭제 실패
				if(result > 0) {
					System.out.println("성공");
				} else {
					System.out.println("실패");
				}
		} catch(Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if(con != null) con.close();
				if(ps != null) ps.close();
			} catch(Exception e) {
				e.printStackTrace();
			}
		}

	}

}
