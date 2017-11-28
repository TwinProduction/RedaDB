public class Example {
	
	public static void main(String... args) {
		RedaDB reda = new RedaDB("jdbc:mysql://127.0.0.1:3306/redadb?user=root&password=root");
		Table table = reda.createTable("user");
		
		// Easy method:
		table.setColumnNamesAutomatically(User.class);
		// Manual method:
		//table.setColumnNames("id", "lastName", "firstName", "gender", "age");
		
		
		table.insert(1, "John", "DOE", 'M', 17);
		table.insert(2, "Jane", "DOE", 'F', 55);
		table.insert(3, "Reda", "Hamza", 'M', 22);
		table.insert(4, "Mathias", "Jolicoeur", 'M', 35);
		table.insert(5, "Samuel", "Filion", 'F', 53);
		table.insert(6, "Goerge", "Bush", 'M', 19);
		table.insert(7, "Chris", "Wow", 'F', 20);
		table.insert(8, "Roger", "Bambam", 'M', 30);
		
		System.out.println("SELECT * FROM user");
		for (Row row : table.getRows()) {
			System.out.println(row.get("*"));
		}
		
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
		
		System.out.println("\nDELETE FROM user WHERE lastName = 'DOE'");
		int rowsDeleted = table.deleteRowsWhere("lastName", "DOE");
		System.out.println("Rows deleted: "+rowsDeleted);
		
		System.out.println("\nUPDATE FROM user WHERE firstName = 'Chris' SET age = 50");
		int rowsUpdated = table.updateRowsWhere("firstName", "Chris", "age", 50);
		System.out.println("Rows updated: "+rowsUpdated);
		
		System.out.println("\nSELECT * FROM user WHERE firstName = 'Chris'");
		for (Row row : table.getRowsWhere("firstName", "Chris")) {
			System.out.println(row.get("*"));
		}
		
		reda.commit();
	}
	
}
