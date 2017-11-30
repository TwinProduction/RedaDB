package org.twinnation.redadb;

import com.google.gson.Gson;

import java.sql.*;
import java.util.HashMap;
import java.util.Map;


public class RedaDBBuilder {
	
	private Map<String, Table> db;
	private String hostname;
	
	
	/**
	 * This constructor should be used for saving a database
	 * @param hostname JDBC url
	 * @param db
	 */
	public RedaDBBuilder(String hostname, Map<String, Table> db) {
		this.hostname = hostname;
		this.db = db;
	}
	
	
	/**
	 * This constructor should be used for loading a database.
	 * @param hostname JDBC url
	 */
	public RedaDBBuilder(String hostname) {
		this.hostname = hostname;
		this.db = new HashMap<>();
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
	
	
	/**
	 * Saves all tables in a SQL table called "redadb".
	 * Each table in RedaDB is saved as a row in MySQL
	 */
	public void saveRedaDB() {
		for (String tableName : db.keySet()) {
			Table table = db.get(tableName);
			try {
				insertTable(tableName, table);
			} catch (SQLException e) {
				System.out.println("Failed to insert table \"" + tableName + "\": " + e.getMessage());
			}
		}
		// XXX: should probably empty db map in this class after it has been saved
	}
	
	
	/**
	 * Inserts one table in MySQL
	 * @param tableName
	 * @param table
	 * @throws SQLException
	 */
	public void insertTable(String tableName, Table table) throws SQLException{
		Connection connection = null;
		PreparedStatement statement = null;
		
		try {
			connection = DriverManager.getConnection(hostname);
			statement = connection.prepareStatement("REPLACE INTO RedaDB VALUES (?, ?)");
			
			statement.setString(1, tableName);
			statement.setString(2, new Gson().toJson(table));
			
			statement.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (statement != null) { statement.close(); }
			if (connection != null) { connection.close(); }
		}
	}
	
	
	/**
	 * Loads all tables from the database
	 */
	public Map<String, Table> loadFromDatabase() throws SQLException {
		this.db = new HashMap<>();
		Connection connection = null;
		Statement statement = null;
		ResultSet rs = null;
		
		try {
			connection = DriverManager.getConnection(hostname);
			statement = connection.createStatement();
			rs = statement.executeQuery("SELECT * FROM RedaDB");
			
			while (rs.next()) {
				Table table = new Gson().fromJson(rs.getString("table_content"), Table.class);
				String tableName = new Gson().fromJson(rs.getString("table_name"), String.class);
				this.db.put(tableName, table);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (rs != null) { rs.close(); }
			if (statement != null) { statement.close(); }
			if (connection != null) { connection.close(); }
		}
		return db;
	}
	
}
