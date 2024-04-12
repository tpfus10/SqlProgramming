//데이터 조회1: 등록된 사용자 목록 출력
package edu.pnu;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class H2Practice7 {

	public static void main(String[] args) {
		

		try (Connection con = DriverManager.getConnection("jdbc:h2:tcp://localhost/~/.h2/sqlprg", "sa", "abcd")) {
			
			// 조회 쿼리문 실행
			Statement st = con.createStatement();
			ResultSet rs = st.executeQuery("select id, username, password, birthyear, regidate from member");
			
			System.out.println("[등록된 사용자 목록]");
			while(rs.next()) {
				System.out.println("ID:" + rs.getInt("id"));
				System.out.println("USERNAME:" + rs.getString("username"));
				System.out.println("PASSWORD:" + rs.getString("password"));
				System.out.println("BIRTHYEAR:" + rs.getInt("birthyear"));
				System.out.println("REGIDATE:" + rs.getDate("regidate"));
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
