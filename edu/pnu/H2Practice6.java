//데이터를 입력받아 Member 테이블 수정하기
package edu.pnu;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

public class H2Practice6 {

	public static void main(String[] args) {
		PreparedStatement psmt = null;
		Scanner sc = new Scanner(System.in);
		System.out.println("수정할 데이터를 입력하세요");

		try (Connection con = DriverManager.getConnection("jdbc:h2:tcp://localhost/~/.h2/sqlprg", "sa", "abcd")) {
			// 수정할 id를 입력받아 idToAlter에 저장
			System.out.println("id:");
			int idToUpdate = sc.nextInt();
			System.out.println();

			// 수정 내역을 입력받아 저장
			System.out.println("변경할 정보를 입력하세요");
			System.out.println("username:");
			String updateUsername = sc.next();
			System.out.println();

			System.out.println("password:");
			String updatePassword = sc.next();
			System.out.println();

			System.out.println("birthyear:");
			int updateBirthyear = sc.nextInt();
			System.out.println();

			// Update 쿼리문 실행
			psmt = con.prepareStatement("update Member set username = ?, password = ?, birthyear = ? where id = ?");
			if (updateUsername != null && updatePassword != null && updateBirthyear != 0) {
				psmt.setString(1, updateUsername);
				psmt.setString(2, updatePassword);
				psmt.setInt(3, updateBirthyear);
				psmt.setInt(4, idToUpdate);
			} else {
				System.out.println("입력되지 않은 데이터가 있습니다");
			}

			// Update 결과를 출력
			int rowsAltered = psmt.executeUpdate();
			System.out.println("Member 테이블에서 id" + idToUpdate + "의 정보가 수정되었습니다.");
			System.out.println("Member 테이블에서" + rowsAltered + "개의 레코드가 수정되었습니다.");

		} catch (Exception e) {
			e.printStackTrace();
		}
		System.out.println("Done");
	}
}
