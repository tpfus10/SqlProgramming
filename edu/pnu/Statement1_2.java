package edu.pnu;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.sql.ResultSetMetaData;

public class Statement1_2 {
	public static void main(String[] args) {

		Connection con = null;
		try {
			String driver = "com.mysql.cj.jdbc.Driver";
			String url = "jdbc:mysql://localhost:3306/world";
			String username = "scott";
			String password = "tiger";

			Class.forName(driver);
			con = DriverManager.getConnection(url, username, password);

			Statement st = con.createStatement();
			ResultSet rs = st.executeQuery("select * from country");
			ResultSetMetaData meta = rs.getMetaData();
			int count = meta.getColumnCount();
			

			while (rs.next()) {
				for (int i = 1; i <= count; i++) {
					System.out.println(rs.getString(i) + ((i == count) ? "" : ","));
				}
				System.out.println();
			}

			rs.close();
			st.close();
			con.close();

		} catch (Exception e) {
			System.out.println("연결실패" + e.getMessage());
		}

	}
}
