package org.twinnation.redadb;

import com.google.gson.Gson;

import java.sql.*;
import java.util.Map;


public class RedaDBBuilder {
	
	private Map<String, Table> db;
	private String hostname;
	
	
	public RedaDBBuilder(String hostname, Map<String, Table> db) {
		this.hostname = hostname;
		this.db = db;
	}
	
	
	/**
	 * For now, this just creates a single table.
	 * That single table contains two columns: `table_name` and `table_content`.
	 * Later on, if we were to expend this project, we'd make one real table for every `table_name`,
	 * but since this is just for the sake of testing and we don't have enough time, we'll leave it
	 * at that.
	 */
	public void createRedaDBTableInMySQL() throws SQLException {
		Connection connection = null;
		Statement statement = null;
		ResultSet rs = null;
		
		try {
			connection = DriverManager.getConnection(hostname);
			statement = connection.createStatement();
			statement.executeUpdate("CREATE TABLE IF NOT EXISTS RedaDB ("
				  + "  table_name VARCHAR(255) PRIMARY KEY, "
				  + "  table_content LONGTEXT"
				  + ")");
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (rs != null) { rs.close(); }
			if (statement != null) { statement.close(); }
			if (connection != null) { connection.close(); }
		}
	}
	
	
	
	public void saveRedaDB() {
		for (String tableName : db.keySet()) {
			Table table = db.get(tableName);
			try {
				insertTable(tableName, table);
			} catch (SQLException e) {
				System.out.println("Failed to insert table \"" + tableName + "\": " + e.getMessage());
			}
		}
	}
	
	
	public void insertTable(String tableName, Table table) throws SQLException{
		Connection connection = null;
		PreparedStatement statement = null;
		ResultSet rs = null;
		
		try {
			connection = DriverManager.getConnection(hostname);
			statement = connection.prepareStatement("REPLACE INTO RedaDB VALUES (?, ?)");
			
			statement.setString(1, tableName);
			statement.setString(2, new Gson().toJson(table));
			
			statement.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (rs != null) { rs.close(); }
			if (statement != null) { statement.close(); }
			if (connection != null) { connection.close(); }
		}
	}
	
	
	public void loadVirtualDB() {
	
	}
	
	
	/*

	public static void insertUsingPreparedStatement() throws SQLException {
		Connection connection = null;
		PreparedStatement statement = null;
		ResultSet rs = null;
		
		try {
			connection = DriverManager.getConnection(DB_URL, DB_USER, DB_PASS);
			statement = connection.prepareStatement("INSERT INTO employees (last_name, first_name, email, department, salary) VALUES (?, ?, ?, ?, ?)");
			
			statement.setString(1, "Elvis");
			statement.setString(2, "Presley");
			statement.setString(3, "elvis@rock.com");
			statement.setString(4, "Musique");
			statement.setFloat(5, 88888.88f);
			
			statement.executeUpdate();
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (rs != null) { rs.close(); }
			if (statement != null) { statement.close(); }
			if (connection != null) { connection.close(); }
		}
	}
	 */
	
	
	
	
	
	
}
