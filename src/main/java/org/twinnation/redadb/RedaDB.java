package org.twinnation.redadb;

import java.util.HashMap;
import java.util.Map;


public class RedaDB {
	
	private Map<String, Table> db;
	private String hostname;
	
	
	public RedaDB(String hostname) {
		this();
		this.hostname = hostname;
	}
	
	
	public RedaDB() {
		db = new HashMap<>();
	}
	
	
	/**
	 * Saves RedaDB in a table named "RedaDB".
	 */
	public void commit() {
		RedaDBBuilder builder = new RedaDBBuilder(hostname, db);
		try {
			builder.createRedaDBTableInMySQL();
			builder.saveRedaDB();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	
	/**
	 * Loads data from the database.
	 */
	public void loadFromDatabase() {
		RedaDBBuilder builder = new RedaDBBuilder(hostname, db);
		try {
			this.db = builder.loadFromDatabase();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	
	/**
	 * Creates a table
	 * @param tableName  Name of the table to create
	 * @return the table created
	 */
	public Table createTable(String tableName) {
		if (!db.containsKey(tableName)) {
			db.put(tableName, new Table());
		}
		return getTable(tableName);
	}
	
	
	/**
	 * Gets a table
	 * @param tableName  Name of the table
	 * @return The table
	 */
	public Table getTable(String tableName) {
		return db.get(tableName);
	}
	
}
