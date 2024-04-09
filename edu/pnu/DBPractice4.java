package edu.pnu;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Scanner;

class Search {
	static final int FIELD = 1;
	static final int ORDER = 2;
	static final int VALUE = 3;

	private String field;
	private String order;
	private String value;
	
	

	public String getField() {
		return field;
	}

	public void setField(String field) {
		this.field = field;
	}

	public String getOrder() {
		return order;
	}

	public void setOrder(String order) {
		this.order = order;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

	public Search(String field, String order, String value) {
		this.field = field;
		this.order = order;
		this.value = value;
	}

	public Search() {
		// TODO Auto-generated constructor stub
	}

	void scanData(int sw) {
		Scanner sc = new Scanner(System.in);
		System.out.println("데이터를 입력하세요.");

		if ((sw & FIELD) == FIELD) { // & 는 bit 연산자임
			System.out.print("FIELD: ");
			setField(sc.next());
		}
		if ((sw & ORDER) == ORDER) {
			System.out.print("AND: ");
			setOrder(sc.next());
		}
		if ((sw & VALUE) == VALUE) {
			System.out.print("VALUE: ");
			setValue(sc.next());
			sc.close();
		}
	}
}

public class DBPractice4 {

	public static void main(String[] args) {
		Search data = new Search();
		data.scanData(3);

		Connection con = null;
		try {
			String driver = "com.mysql.cj.jdbc.Driver";
			String url = "jdbc:mysql://localhost:3306/world";
			String username = "scott";
			String password = "tiger";

			Class.forName(driver);
			con = DriverManager.getConnection(url, username, password);

			Statement st = con.createStatement();
			ResultSet rs = st.executeQuery("select id, name, countrycode, district, population from city where " + data.getField() + " " + data.getOrder() + " ('" + data.getValue()+ "')");

			while (rs.next()) {
				System.out.println("[" + rs.getString("countrycode") + "]");
				System.out.print(rs.getString("name"));
				System.out.print("(" + rs.getString("id") + ")"); 
				System.out.println("인구수" + rs.getString("population") + "명");
				System.out.println();
			}
			

			rs.close();
			st.close();
			con.close();

		} catch (Exception e) {
			System.out.println("연결 실패" + e.getMessage());
		}
	}
}
