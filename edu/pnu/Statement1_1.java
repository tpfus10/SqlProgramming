package edu.pnu;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class Statement1_1 {
	
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
			ResultSet rs = st.executeQuery("select * from city");
			
			while(rs.next()) {
				System.out.println(rs.getString("id") + ",");
				System.out.println(rs.getString("name") + ",");
				System.out.println(rs.getString("countrycode") + ",");
				System.out.println(rs.getString("district") + ",");
				System.out.println(rs.getString("population") + "\n");
			}
			
			rs.close();
			st.close();
			con.close();
			
		}catch(Exception e) {
			System.out.println("연결 실패" + e.getMessage());
		}
	}

}
