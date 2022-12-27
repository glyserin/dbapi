// C035384 정세린 
package com.example.demo;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;
import java.sql.ResultSet;


public class TableFunctions {

	public Connection get_connection() {
		Connection connection = null;

		try {
			Class.forName("org.postgresql.Driver");
			connection = DriverManager.getConnection("jdbc:postgresql://localhost:5432/hongik", "postgres", "1234");

			if (connection != null) {
				System.out.println("Connection Success");
			} else {
				System.out.println("Connection Failed");
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		return connection;
	}

	
	public void DropTable(Connection connection) {
		Statement st;

		try {
			String query = "DROP TABLE IF EXISTS STUDENTS";
			st = connection.createStatement();
			st.executeUpdate(query);
			System.out.println("Table Existing Dropped");
		} catch (Exception e) {
			System.out.println(e);
		}
	}

	public void CreateTable(Connection connection) {
		Statement st;

		try {
			String query = "CREATE TABLE STUDENTS " +
		            "(NAME           VARCHAR(100)    NOT NULL, " +
		            " EMAIL          VARCHAR(100)    PRIMARY KEY	NOT NULL, " +
		            " GRADUATION     INTEGER		 NOT NULL, " +
		            " DEGREE         VARCHAR(100)	 NOT NULL)";
			st = connection.createStatement();
			st.executeUpdate(query);
			System.out.println("Table Created");
		} catch (Exception e) {
			System.out.println(e);
		}
	}

	public void InsertRow(Connection connection, String n, String em, Integer g, String d) {
		Statement st;

		try {
			String query = String.format("INSERT INTO STUDENTS (NAME, EMAIL, GRADUATION, DEGREE) VALUES('%s', '%s', %d, '%s');", n, em, g, d);
			st = connection.createStatement();
			st.executeUpdate(query);
			System.out.println("Row Inserted");
		} catch (Exception e) {
			System.out.println(e);
		}
	}
	
	public int CountStudent(Connection connection, String n) {
		Statement st;
		int cnt = 0;
		
		try {
			String query = String.format("SELECT COUNT(*) FROM STUDENTS WHERE NAME = '%s' ;", n);
			st = connection.createStatement();
			ResultSet rs = st.executeQuery(query);
			if(rs.next()) cnt = rs.getInt(1);
		} catch (Exception e) {
			System.out.println(e);
		}
		return cnt;
	}
	
	
	
	public String SelectDegree(Connection connection, String n) {
		Statement st;
		String degree = null;

		try {
			String query = String.format("SELECT DEGREE FROM STUDENTS WHERE NAME = '%s' ;", n);
			st = connection.createStatement();
			ResultSet rs = st.executeQuery(query);
			System.out.println("Row Selected");
			if(rs.next()) degree = rs.getString(1);
		} catch (Exception e) {
			System.out.println(e);
		}
		return degree;
	}

	public String SelectEmail(Connection connection, String em) {
		Statement st;
		String email = null;

		try {
			String query = String.format("SELECT EMAIL FROM STUDENTS WHERE NAME = '%s' ;", em);
			st = connection.createStatement();
			ResultSet rs = st.executeQuery(query);
			System.out.println("Row Selected");
			if(rs.next()) email = rs.getString(1);
		} catch (Exception e) {
			System.out.println(e);
		}
		return email;
	}
	
	public int SelectStat(Connection connection, String d) {
		Statement st;
		int stat = 0;

		try {
			String query = String.format("SELECT COUNT(*) FROM STUDENTS WHERE DEGREE = '%s' ;", d);
			st = connection.createStatement();
			ResultSet rs = st.executeQuery(query);
			if(rs.next()) stat = rs.getInt(1);
		} catch (Exception e) {
			System.out.println(e);
		}
		return stat;
	}
	
}
