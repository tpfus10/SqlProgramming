//city 테이블에서 국가코드와 인구수를 출력하세요.(국가코드별로 오름차순 정렬, 동일한 코드 안에서는 인구수의 역순으로 정렬)
package edu.pnu;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.text.DecimalFormat;

public class DBPractice2 {

	public static void main(String[] args) {
		Connection con = null;
		try {
			String driver = "com.mysql.cj.jdbc.Driver";
			String url = "jdbc:mysql://localhost:3306/world";
			String username = "scott";
			String password = "tiger";

			Class.forName(driver);
			con = DriverManager.getConnection(url, username, password);

			Statement st = con.createStatement();
			ResultSet rs = st.executeQuery("select countrycode, population from city order by countrycode, population desc");

			DecimalFormat df = new DecimalFormat("#,###");
			
			while (rs.next()) {
				System.out.print("[" + rs.getString("countrycode") + "] ");
				System.out.println(df.format(rs.getInt("population")) + "명");
			}

			rs.close();
			st.close();
			con.close();

		} catch (Exception e) {
			System.out.println("연결 실패" + e.getMessage());
		}
	}
}
