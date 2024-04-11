//데이터를 입력받아 Board 테이블 수정하기
package edu.pnu;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

public class H2Practice4 {

	public static void main(String[] args) {
		PreparedStatement psmt = null;
		Scanner sc = new Scanner(System.in);
		System.out.println("수정할 데이터를 입력하세요");

		try (Connection con = DriverManager.getConnection("jdbc:h2:tcp://localhost/~/.h2/sqlprg", "sa", "abcd")) {
			//수정할 num을 입력받아 numToAlter에 저장
			System.out.println("num:");
			int numToUpdate = sc.nextInt();
			System.out.println();
			
			//수정 내역을 입력받아 저장
			System.out.println("변경할 정보를 입력하세요");
			System.out.println("title:");
			String updateTitle = sc.next();
			System.out.println();
			System.out.println("content:");
			String updateContent = sc.next();
			System.out.println();
			
			//Update 쿼리문 실행
			psmt = con.prepareStatement("update Board set title = ?, content = ? where num = ?");
			psmt.setString(1, updateTitle);
			psmt.setString(2, updateContent);
			psmt.setInt(3, numToUpdate);
			
			//Update 결과를 출력
			int rowsAltered = psmt.executeUpdate();
			System.out.println("Board 테이블에서 num" + numToUpdate + "의 정보가 수정되었습니다.");
			System.out.println("Board 테이블에서" + rowsAltered + "개의 레코드가 수정되었습니다.");

		} catch (Exception e) {
			e.printStackTrace();
		}
		System.out.println("Done");
	}
}
