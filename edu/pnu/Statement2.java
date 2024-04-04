package edu.pnu;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

public class Statement2 {

	public static void main(String[] args) {
		try (Scanner sc = new Scanner(System.in)) { //Scanner 객체 생성
			System.out.println("Table Name:");
			String tblname = sc.next(); //Scanner 객체로 콘솔에서 테이블명을 입력받
			test(tblname); //test 메소드 호출
		}
	}

	public static void test(String tblname) {
		Connection con = null;
		Statement st = null;
		ResultSet rs = null;

		try {

			Class.forName("com.mysql.cj.jdbc.Driver");
			con = DriverManager.getConnection("jdbc:mysql://localhost:3306/world", "scott", "tiger");
			st = con.createStatement();
			rs = st.executeQuery("SELECT * FROM" + tblname + " LIMIT 10"); 
			ResultSetMetaData meta = rs.getMetaData();
			int count = meta.getColumnCount();

			//데이터를 가져오는 게 아니라 서버에 저장된 걸 읽어오는 것임
			//next()는 서버에서 레코드를 한 줄씩 읽어와서 rs에 넣어줌
			//다음 줄을 읽을 때는 기존에 있는 데이터에 덮어씀
			while (rs.next()) { 
				for (int i = 1; i <= count; i++) {
					System.out.println(rs.getString(i) + ((i == count) ? "" : ","));
				}
				System.out.println();
			}

		} catch (ClassNotFoundException | SQLException e) {

			System.out.println("연결 실패" + e.getMessage());

		} finally {

			try {
				if (rs != null) {
					rs.close();
				}
				if (st != null) {
					st.close();
				}
				if (con != null) {
					con.close();
				}
			} catch (SQLException e) {

				e.printStackTrace();

			}
		}
	}
}
