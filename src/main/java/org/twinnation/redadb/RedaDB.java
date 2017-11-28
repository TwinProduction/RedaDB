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
	
	
	public void commit() {
		RedaDBBuilder builder = new RedaDBBuilder(hostname, db);
		try {
			builder.createRedaDBTableInMySQL();
			builder.saveRedaDB();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	
	public void loadFromDatabase() {
		RedaDBBuilder builder = new RedaDBBuilder(hostname, db);
	}
	
	
	public Table createTable(String tableName) {
		if (!db.containsKey(tableName)) {
			db.put(tableName, new Table());
		}
		return getTable(tableName);
	}
	
	
	public Table getTable(String tableName) {
		return db.get(tableName);
	}
	
}
