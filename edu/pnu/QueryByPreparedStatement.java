package edu.pnu;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.text.DecimalFormat;

public class QueryByPreparedStatement {

		public static void main(String[] args) {
			Connection con = null;
			try {
				String url = "jdbc:mysql://localhost:3306/world";
				String username = "scott";
				String password = "tiger";

				con = DriverManager.getConnection(url, username, password);

				PreparedStatement pt = con.prepareStatement("select id, name, countrycode, population from city where countrycode=?");
				pt.setString(1, "KOR");
				ResultSet rs = pt.executeQuery();

				DecimalFormat df = new DecimalFormat("#,###");
				
				while (rs.next()) {
					System.out.print("[" + rs.getString("countrycode") + "] ");
					System.out.print(rs.getString("name"));
					System.out.println("(" + rs.getString("id") + ")");
					System.out.println("->인구수: " + df.format(rs.getInt("population")) + "명\n");
				}

				rs.close();
				pt.close();
				con.close();

			} catch (Exception e) {
				System.out.println("연결 실패" + e.getMessage());
			}
		}
	
}
