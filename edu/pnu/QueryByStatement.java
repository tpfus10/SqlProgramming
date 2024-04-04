package edu.pnu;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class QueryByStatement {
	
	public static void main(String[] args) {
		Connection con = null;
		try {
			//DBConnect
			String driver = "com.mysql.cj.jdbc.Driver";
			String url = "jdbc:mysql://localhost:3306/world";
			String username = "scott";
			String password = "tiger";
			
			Class.forName(driver); //DriverLoading
			con = DriverManager.getConnection(url, username, password);
			
			//Statement: Connectoin 상에서 SQL문을 처리하는 객체
			//Connection의 createStatement()를 이용해 질의 객체 생성 
			Statement st = con.createStatement(); 
			ResultSet rs = st.executeQuery("select id, name, countrycode, district, population from city limit 10"); //executeQuery(): sql의 select
			
			while(rs.next()) { //rs.next(): rs 객체에서 한 행씩 이동하면서 데이터를 읽음(cursor processing)
				System.out.println(rs.getString("id") + ","); //rs.getString("필드명"): rs 객체에서 커서가 위치한 레코드의 값을 반환
				System.out.println(rs.getString("name") + ",");
				System.out.println(rs.getString("countrycode") + ",");
				System.out.println(rs.getString("district") + ",");
				System.out.println(rs.getString("population") + "\n");
			}
			rs.close();
			st.close();
			con.close();
		} catch(Exception e) {
			System.out.println("연결 실패" + e.getMessage());
		}
		
	}

}
