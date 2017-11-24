import java.util.HashMap;
import java.util.Map;


public class RedaDB {
	
	private Map<String, Table> db;
	
	private String hostname;
	//private String database; // OPT
	
	
	public RedaDB(String hostname) {
		this();
		this.hostname = hostname;
		
	}
	
	
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
		
		table.setColumnNamesAutomatically(User.class);
		//table.setColumnNames("id", "lastName", "firstName", "gender", "age");
		
		/*
		table.insert(1, "DOE", "John", 'M', 17);
		table.insert(2, "DOE", "Jane");
		table.insert(3, "?", "Reda");
		
		//table.insert(new User(1, "John", "Doe", 'M', 69));
		
		
		System.out.println("SELECT * FROM user");
		for (Row row : table.getRows()) {
			System.out.println(row.get("*"));
		}*/
		
		
		/*
		System.out.println("\nSELECT id FROM user");
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
		
		System.out.println("\nSELECT * FROM user WHERE firstName = 'John'");
		for (Row row : table.getRowsWhere("firstName", "John")) {
			System.out.println(row.get("*"));
		}
		
		
		System.out.println("\nDELETE FROM user WHERE firstName = 'John'");
		int rowsDeleted = table.deleteRowsWhere("firstName", "John");
		System.out.println("Rows deleted: "+rowsDeleted);
		
		*/
		
		
	}
	
}
