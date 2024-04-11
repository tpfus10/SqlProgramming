//테이블 Member를 생성하고 데이터 입력하기
package edu.pnu;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Random;

public class H2Practice1 {

	public static void main(String[] args) {
		PreparedStatement psmt = null; 
		Random random = new Random();

		try (Connection con = DriverManager.getConnection("jdbc:h2:tcp://localhost/~/.h2/sqlprg", "sa", "abcd")) {
			if(!createTable(con)) return;
			
			psmt = con.prepareStatement("insert into Member(username,password,birthyear) values(?,?,?)");
			for (int i = 1; i <= 5; i++) {
				 psmt.setString(1, "user" + i);
				 psmt.setString(2, "pass" + i);
				 psmt.setInt(3, random.nextInt(11) + 2000);
				int result = psmt.executeUpdate();
				System.out.println("Member 테이블에 " + result + "개가 입력되었습니다.");
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		System.out.println("Done");
	}

	public static boolean createTable(Connection con) {
		Statement st = null;
		try {
			st = con.createStatement();
			st.execute("DROP TABLE Member IF EXISTS");
			st.execute("CREATE TABLE Member (" 
						+ "id INT NOT NULL AUTO_INCREMENT PRIMARY KEY," 
						+ "username varchar(10),"
						+ "password varchar(10)," 
						+ "birthyear INT NOT NULL,"
						+ "regidate DATE NOT NULL DEFAULT(curdate()))");
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		} finally {
			try {
				if (st != null)
					st.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return true;
	}
}
