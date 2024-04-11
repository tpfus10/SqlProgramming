//데이터를 입력받아 Member 테이블에서 삭제하기
package edu.pnu;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

public class H2Practice5 {

	public static void main(String[] args) {
		PreparedStatement psmt = null;
		Scanner sc = new Scanner(System.in);
		System.out.println("삭제할 id를 입력하세요");

		try (Connection con = DriverManager.getConnection("jdbc:h2:tcp://localhost/~/.h2/sqlprg", "sa", "abcd")) {
			//삭제할 id를 입력받아 idToDelete에 저장
			System.out.println("id:");
			int idToDelete = sc.nextInt();
			System.out.println();
			
			//delete 쿼리문 실행
			psmt = con.prepareStatement("delete from Member where id = ?");
			psmt.setInt(1, idToDelete);
			
			//delete 결과를 출력
			int rowsDeleted = psmt.executeUpdate();
			System.out.println("Member 테이블에 id" + idToDelete + "의 정보가 삭제되었습니다.");
			System.out.println("Member 테이블에서" + rowsDeleted + "개의 레코드가 삭제되었습니다.");

		} catch (Exception e) {
			e.printStackTrace();
		}
		System.out.println("Done");
	}
}
