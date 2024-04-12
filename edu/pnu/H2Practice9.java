//데이터 조회3: 사용자명“user1”이 작성한 게시판 목록 출력(id 대신 username을 출력)
package edu.pnu;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class H2Practice9 {

	public static void main(String[] args) {
		

		try (Connection con = DriverManager.getConnection("jdbc:h2:tcp://localhost/~/.h2/sqlprg", "sa", "abcd")) {
			
			// 조회 쿼리문 실행
			Statement st = con.createStatement();
			ResultSet rs = st.executeQuery("SELECT board.num, board.title, board.content, member.username, board.postdate, board.visitcount "
                    + "FROM member FULL JOIN board ON member.id = board.id "
                    + "WHERE member.username = 'user1'");
			
			System.out.println("[등록된 사용자 목록]");
			while (rs.next()) {
				System.out.println("NUM:" + rs.getInt("board.num"));
				System.out.println("TITLE:" + rs.getString("board.title"));
				System.out.println("CONTENT:" + rs.getString("board.content"));
				System.out.println("USERNAME:" + rs.getInt("member.username"));
				System.out.println("POSTDATE:" + rs.getDate("board.postdate"));
				System.out.println("VISITCOUNT:" + rs.getInt("board.visitcount"));
				System.out.println();
			}
			
			rs.close();
			st.close();
			con.close();
			
			// 조회 결과를 출력
			System.out.println("Member 테이블의 데이터를 조회했습니다.");

		} catch (Exception e) {
			e.printStackTrace();
		}
		System.out.println("Done");
	}
}
