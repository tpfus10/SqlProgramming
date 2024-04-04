package edu.pnu;

import java.sql.DriverManager;
import java.sql.Connection;

public class DBConnect {

	public static void main(String[] args) {

		try {
			String driver = "com.mysql.cj.jdbc.Driver";
			String url = "jdbc:mysql://localhost:3306/world";
			String username = "scott";
			String password = "tiger";

			Class.forName(driver);
			Connection con = DriverManager.getConnection(url, username, password);

			System.out.println("연결성공");
			con.close();

		} catch (Exception e) {
			System.out.println("연결실패" + e.getMessage());
		}
	}

}
