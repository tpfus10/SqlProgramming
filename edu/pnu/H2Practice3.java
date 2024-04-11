//데이터를 입력받아 Board 테이블에서 삭제하기
package edu.pnu;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

public class H2Practice3 {

	public static void main(String[] args) {
		PreparedStatement psmt = null;
		Scanner sc = new Scanner(System.in);
		System.out.println("삭제할 num을 입력하세요");

		try (Connection con = DriverManager.getConnection("jdbc:h2:tcp://localhost/~/.h2/sqlprg", "sa", "abcd")) {
			//삭제할 num을 입력받아 numToDelete에 저장
			System.out.println("num:");
			int numToDelete = sc.nextInt();
			System.out.println();
			
			//delete 쿼리문 실행
			psmt = con.prepareStatement("delete from Board where num = ?");
			psmt.setInt(1, numToDelete);
			
			//delete 결과를 출력
			int rowsDeleted = psmt.executeUpdate();
			System.out.println("Board 테이블에서 num" + numToDelete + "의 정보가 삭제되었습니다.");
			System.out.println("Board 테이블에서" + rowsDeleted + "개의 레코드가 삭제되었습니다.");

		} catch (Exception e) {
			e.printStackTrace();
		}
		System.out.println("Done");
	}
}
