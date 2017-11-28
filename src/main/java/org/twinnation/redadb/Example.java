package org.twinnation.redadb;

import org.twinnation.redadb.example.Fruit;
import org.twinnation.redadb.example.User;


public class Example {
	
	public static RedaDB redaDB = new RedaDB("jdbc:mysql://127.0.0.1:3306/redadb?user=root&password=root");
	
	
	public static void main(String... args) {
		//redaDB.loadFromDatabase();
		createFruitTableExample();
		createUserTableExample();
		queryExamples();
	}
	
	
	private static void createFruitTableExample() {
		Table table = redaDB.createTable("fruit");
		
		table.setColumnNamesAutomatically(Fruit.class);
		//table.setColumnNames("id", "name", "color"); // Manual method
		
		table.insert(1, "Banana", "yellow");
		table.insert(2, "Orange", "orange");
		table.insert(3, "Lemon", "yellow");
		table.insert(4, "Apple", "red");
		
		redaDB.commit();
	}
	
	
	public static void createUserTableExample() {
		Table table = redaDB.createTable("user");
		
		table.setColumnNamesAutomatically(User.class); // Easy method
		//table.setColumnNames("id", "lastName", "firstName", "gender", "age"); // Manual method
		
		table.insert(1, "John", "DOE", 'M', 17);
		table.insert(2, "Jane", "DOE", 'F', 55);
		table.insert(3, "Reda", "Hamza", 'M', 22);
		table.insert(4, "Mathias", "Jolicoeur", 'M', 35);
		table.insert(5, "Samuel", "Filion", 'F', 53);
		table.insert(6, "Goerge", "Bush", 'M', 19);
		table.insert(7, "Chris", "Wow", 'F', 20);
		table.insert(8, "Roger", "Bambam", 'M', 30);
		
		redaDB.commit();
	}
	
	
	
	
	public static void queryExamples() {
		Table userTable = redaDB.getTable("user");
		
		System.out.println("SELECT * FROM user");
		for (Row row : userTable.getRows()) {
			System.out.println(row.get("*"));
		}
		
		System.out.println("\nSELECT id FROM user");
		for (Row row : userTable.getRows()) {
			System.out.println(row.get("id"));
		}
		
		System.out.println("\nSELECT id, lastName FROM user");
		for (Row row : userTable.getRows()) {
			System.out.println(row.get("id", "lastname"));
		}
		
		System.out.println("\nSELECT id, lastName, age FROM user");
		for (Row row : userTable.getRows()) {
			System.out.println(row.get("id", "lastname", "age"));
		}
		
		System.out.println("\nSELECT * FROM user WHERE firstName = 'John'");
		for (Row row : userTable.getRowsWhere("firstName", "John")) {
			System.out.println(row.get("*"));
		}
		
		System.out.println("\nDELETE FROM user WHERE lastName = 'DOE'");
		int rowsDeleted = userTable.deleteRowsWhere("lastName", "DOE");
		System.out.println("Rows deleted: "+rowsDeleted);
		
		System.out.println("\nUPDATE FROM user WHERE firstName = 'Chris' SET age = 50");
		int rowsUpdated = userTable.updateRowsWhere("firstName", "Chris", "age", 50);
		System.out.println("Rows updated: "+rowsUpdated);
		
		System.out.println("\nSELECT * FROM user WHERE firstName = 'Chris'");
		for (Row row : userTable.getRowsWhere("firstName", "Chris")) {
			System.out.println(row.get("*"));
		}
		
		///////////
		// FRUIT //
		///////////
		
		Table fruitTable = redaDB.getTable("fruit");
		
		System.out.println("\nSELECT * FROM fruit WHERE color = 'yellow'");
		for (Row row : fruitTable.getRowsWhere("color", "yellow")) {
			System.out.println(row.get("*"));
		}
	}
	
}
