//테이블 Board를 생성하고 데이터 입력하기
package edu.pnu;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Random;

public class H2Practice2 {

	public static void main(String[] args) {
		PreparedStatement psmt = null;
		Random random = new Random();

		try (Connection con = DriverManager.getConnection("jdbc:h2:tcp://localhost/~/.h2/sqlprg", "sa", "abcd")) {
			if (!createTable(con))
				return;

			psmt = con.prepareStatement("insert into Board(title, content, id, visitcount) values(?,?,?,?)");
			for (int i = 1; i <= 5; i++) {
				for (int j = 1; j <= 10; j++) {
					psmt.setString(1, "title" + j);
					psmt.setString(2, "content" + j);
					psmt.setInt(3, i); //id는 1~5로 저장되어 있으며 중복 가능한 데이터
					psmt.setInt(4, random.nextInt(100));
					int result = psmt.executeUpdate();
					System.out.println("Board 테이블에 " + result + "개가 입력되었습니다.");
				}
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
			st.execute("DROP TABLE Board IF EXISTS");
			st.execute("CREATE TABLE Board (" 
						+ "num INT NOT NULL AUTO_INCREMENT PRIMARY KEY,"
						+ "title varchar(200) NOT NULL," 
						+ "content varchar(2000) NOT NULL,"
						+ "id int NOT NULL,"
						+ "postdate date NOT NULL DEFAULT(curdate())," 
						+ "visitcount int DEFAULT 0)");
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
