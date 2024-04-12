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
			
			//동적 쿼리 작성하기 
			String str = "";
			if(updateUsername != null && !updateUsername.isEmpty()) {
				str = "username='" + updateUsername + "'";
			}
			if(updatePassword != null && !updatePassword.isEmpty()) {
				if(!str.isEmpty()) str += ", ";
				str = str + "password='" + updatePassword + "'";
			}
			if(updateBirthyear != 0) {
				if(!str.isEmpty()) str += ", ";
				str = str + "birthyear=" + updateBirthyear;
			}
			
			// Update 쿼리문 실행
			String sql = "update Member set " + str + " where id=" + idToUpdate;
			Statement st = con.createStatement();
			st.executeUpdate(sql);
	
			//System.out.println(sql);
			
			// Update 결과를 출력
			System.out.println("Member 테이블에서 id" + idToUpdate + "의 정보가 수정되었습니다.");
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		System.out.println("Done");
	}
}
