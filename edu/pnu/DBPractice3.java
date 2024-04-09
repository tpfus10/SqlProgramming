//city 테이블에서 국가코드가 'KOR', 'CHN', 'JPN'인 도시를 찾으세요.
package edu.pnu;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class DBPractice3 {

	public static void main(String[] args) {
		Statement st = null;
		ResultSet rs = null;
		
		//데이터베이스 연결 객체 가져오기
		try(Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/world", "scott", "tiger")) {

			//질의 객체 생성
			st = con.createStatement();
			rs = st.executeQuery("select name, countrycode from city where countrycode in ('KOR', 'CHN', 'JPN')");

			//rs 출력
			while (rs.next()) {
				System.out.print("[" + rs.getString("countrycode") + "] ");
				System.out.println(rs.getString("name"));
			}

			rs.close();
			st.close();
			//con.close();

		} catch (Exception e) {
			System.out.println("연결 실패" + e.getMessage());
		}
	}
}
