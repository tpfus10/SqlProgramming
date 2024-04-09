//국가 코드가 'KOR'인 도시를 찾아 인구수를 역순으로 표시하세요.
package edu.pnu;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.text.DecimalFormat;
import java.util.Scanner;

public class DBPractice1 {
	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		System.out.println("Code:");
		String code = sc.next();
		
		Connection con = null;
		try {
			String driver = "com.mysql.cj.jdbc.Driver";
			String url = "jdbc:mysql://localhost:3306/world";
			String username = "scott";
			String password = "tiger";
			
			Class.forName(driver);
			con = DriverManager.getConnection(url, username,password);
			
			Statement st = con.createStatement();
			//code가 문자로 인식될 수 있도록 ~'" + code + "'~ 이렇게 ''를 넣어줘야 함 (ex.'KOR")
			ResultSet rs = st.executeQuery("select name, population from city where countrycode ='" + code + "'order by population desc");
			
			DecimalFormat df = new DecimalFormat("#,###");
			
			while(rs.next()) {
				System.out.print("[" + rs.getString("name") + "] ");
				System.out.println(df.format(rs.getInt("population"))+ "명");
			}
			
			rs.close();
			st.close();
			con.close();
			sc.close();
			
		} catch(Exception e) {
			System.out.println("연결 실패" + e.getMessage());
		}
	}

}
