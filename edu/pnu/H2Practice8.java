//데이터 조회2: 등록된 게시판 목록 출력(페이징 활용)
package edu.pnu;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class H2Practice8 {

	public static void main(String[] args) {
		PreparedStatement psmt = null;

		try (Connection con = DriverManager.getConnection("jdbc:h2:tcp://localhost/~/.h2/sqlprg", "sa", "abcd")) {
			int pageSize = 5;
			int index = 0;
			
			System.out.println("[등록된 게시판 목록]");

			while (true) {
				// 조회 쿼리문 실행(limit과 offset은 '=' 안 씀)
				psmt = con.prepareStatement("select num, title, content, id, postdate, visitcount from board limit ? offset ?");
				psmt.setInt(1, 5);
				psmt.setInt(2, index);
				
				ResultSet rs = psmt.executeQuery();
				
				boolean allDataRead = false;
				
				while (rs.next()) {
					allDataRead = true;
					System.out.println("NUM:" + rs.getInt("num"));
					System.out.println("TITLE:" + rs.getString("title"));
					System.out.println("CONTENT:" + rs.getString("content"));
					System.out.println("ID:" + rs.getInt("id"));
					System.out.println("POSTDATE:" + rs.getDate("postdate"));
					System.out.println("VISITCOUNT:" + rs.getInt("visitcount"));
					System.out.println();
				}
				System.out.println("----------------------------------");

				if (!allDataRead) {
					break;
				}
				
				index += 5;
				
				rs.close();
				psmt.close();
			}
			con.close();


			// 조회 결과를 출력
			System.out.println("Member 테이블의 데이터를 조회했습니다.");

		} catch (Exception e) {
			e.printStackTrace();
		}
		System.out.println("Done");
	}
}
