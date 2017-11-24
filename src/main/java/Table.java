import org.json.JSONObject;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;


public class Table {
	
	private List<String> columnNames;
	private List<Row> rows;
	
	private boolean tableColumnNamesInitialized;
	
	
	public Table(String... columnNames) {
		this();
		setColumnNames(columnNames);
	}
	
	
	public Table() {
		this.columnNames = new ArrayList<>();
		this.rows = new ArrayList<>();
	}
	
	
	public void setColumnNames(String... columnNames) {
		for (String name : columnNames) {
			this.columnNames.add(name);
		}
		tableColumnNamesInitialized = true;
	}
	
	
	public void setColumnNamesAutomatically(Class c) {
		String[] fieldNames = new String[c.getFields().length];
		Field[] fields = c.getFields();
		for (int i = 0; i < c.getFields().length; i++) {
			fieldNames[i] = fields[i].getName();
		}
		setColumnNames(fieldNames);
	}
	
	
	public List<Row> getRows() {
		return rows;
	}
	
	
	public List<Row> getRowsWhere(String columnName, Object expectedValue) {
		List<Row> result = new ArrayList<>();
		JSONObject condition = new JSONObject();
		condition.put(columnName.toLowerCase(), expectedValue);
		for (Row row : rows) {
			if ((row.get(columnName)).equals(condition.toString())) {
				result.add(row);
			}
		}
		return result;
	}
	
	
	public int deleteRowsWhere(String columnName, String expectedValue) {
		int numberOfRowsDeleted = 0;
		List<Row> rowsToDelete = new ArrayList<>();
		JSONObject condition = new JSONObject();
		condition.put(columnName.toLowerCase(), expectedValue);
		for (Row row : rows) {
			if ((row.get(columnName)).equals(condition.toString())) {
				rowsToDelete.add(row);
			}
		}
		for (Row row : rowsToDelete) {
			rows.remove(row);
		}
		return rowsToDelete.size();
	}
	
	
	public void insert(Object... objects) {
		if (!tableColumnNamesInitialized) {
			System.out.println("TABLE COLUMN NAMES ARE NOT INITIALIZED");
		} else {
			rows.add(new Row(columnNames, objects));
		}
	}
	
	
	
}
