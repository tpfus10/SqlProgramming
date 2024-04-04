package edu.pnu;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class Statement2_1 {
	
	public static void main(String[] args) {
		Connection con;
		
		try {
			String driver = "com.mysql.cj.jdbc.Driver";
			String url = "jdbc:mysql://localhost:3306/world";
			String username = "scott";
			String password = "tiger";
			
			Class.forName(driver);
			con = DriverManager.getConnection(url, username, password);
			
			Statement st = con.createStatement();
			ResultSet rs = st.executeQuery("SELECT B.name, B.population " //끝에 공백이 있어야 함
										+ "FROM country A JOIN city B ON A.code = B.countrycode "
										+ "WHERE A.code = 'KOR' "
										+ "ORDER BY B.population DESC ");
			
			while(rs.next()) {
				System.out.println(rs.getString("B.name")+ ",");
				System.out.println(rs.getString("B.population")+ "\n");
			}
			
			rs.close();
			st.close();
			con.close();
			
		} catch (Exception e) {
			System.out.println("연결 실패" + e.getMessage());
		}
	}

}
