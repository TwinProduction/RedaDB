import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class RedaDB {
	
	private Map<String, Table> db;
	
	
	public RedaDB() {
		db = new HashMap<>();
	}
	
	/*
	public boolean insert(String table, Row row) {
		if (getTable(table) == null) {
			createTable(table);
		}
		getTable(table).insert(row);
		return true;
	}
	*/
	
	public void commit() {
		// send db to mysql
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
	
	
	
	
	public static void main(String... args) {
		RedaDB reda = new RedaDB();
		Table table = reda.createTable("user");
		table.setColumnNames("id", "lastName", "firstName", "gender", "age");
		
		table.insert(1, "DOE", "John", 'M', 17);
		table.insert(2, "DOE", "Jane");
		table.insert(3, "?", "Reda");
		
		System.out.println("SELECT id FROM user");
		for (Row row : table.getRows()) {
			System.out.println(row.get("id"));
		}
		
		
		System.out.println("\nSELECT id, lastName FROM user");
		table = reda.getTable("user");
		for (Row row : table.getRows()) {
			System.out.println(row.get("id", "lastname"));
		}
		
		System.out.println("\nSELECT id, lastName, age FROM user");
		for (Row row : table.getRows()) {
			System.out.println(row.get("id", "lastname", "age"));
		}
		
	}
	
}
