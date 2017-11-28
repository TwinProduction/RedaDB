package org.twinnation.redadb;

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
	
	
	/**
	 * <code>
	 *     table.setColumnNames("id", "lastName", "firstName", "gender", "age");
	 * </code>
	 */
	public void setColumnNames(String... columnNames) {
		for (String name : columnNames) {
			this.columnNames.add(name);
		}
		tableColumnNamesInitialized = true;
	}
	
	
	/**
	 * <code>
	 *     table.setColumnNamesAutomatically(org.twinnation.redadb.example.User.class);
	 * </code>
	 */
	public void setColumnNamesAutomatically(Class c) {
		String[] fieldNames = new String[c.getDeclaredFields().length];
		Field[] fields = c.getDeclaredFields();
		for (int i = 0; i < fields.length; i++) {
			fieldNames[i] = fields[i].getName();
		}
		setColumnNames(fieldNames);
	}
	
	
	/**
	 * Gets a list of row WHERE columnName = expectedValue
	 * @param columnName  Name of the column
	 * @param expectedValue  Expected value
	 * @return List of rows
	 */
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
	
	
	/**
	 * Deletes all rows WHERE columnName = expectedValue
	 * @param columnName  Name of the column
	 * @param expectedValue  Expected value
	 * @return Number of rows deleted
	 */
	public int deleteRowsWhere(String columnName, Object expectedValue) {
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
	
	
	/**
	 * Update columnNameToUpdate to newValue in all rows WHERE whereColumnName = expectedValue
	 * @param whereColumnName  Name of the column to search through
	 * @param expectedValue  Expected value (whereColumnName)
	 * @param columnNameToUpdate  Name of the column to update
	 * @param newValue  New value (columnNameToUpdate)
	 * @return Number of rows updated
	 */
	public int updateRowsWhere(String whereColumnName, Object expectedValue, String columnNameToUpdate, Object newValue) {
		int updates = 0;
		JSONObject condition = new JSONObject();
		condition.put(whereColumnName.toLowerCase(), expectedValue);
		for (Row row : rows) {
			if ((row.get(whereColumnName)).equals(condition.toString())) {
				row.set(columnNameToUpdate, newValue);
				updates++;
			}
		}
		return updates;
	}
	
	
	public void insert(Object... objects) {
		if (!tableColumnNamesInitialized) {
			System.out.println("TABLE COLUMN NAMES ARE NOT INITIALIZED");
		} else {
			rows.add(new Row(columnNames, objects));
		}
	}
	
	
	/**
	 * Gets all column names
	 * @return
	 */
	public List<String> getColumnNames() {
		return columnNames;
	}
	
	
	/**
	 * Sets column names
	 * @param columnNames  Column names
	 */
	public void setColumnNames(List<String> columnNames) {
		this.columnNames = columnNames;
	}
	
	
	public List<Row> getRows() {
		return rows;
	}
	
	
	public void setRows(List<Row> rows) {
		this.rows = rows;
	}
	
	
	public boolean isTableColumnNamesInitialized() {
		return tableColumnNamesInitialized;
	}
	
	
	public void setTableColumnNamesInitialized(boolean tableColumnNamesInitialized) {
		this.tableColumnNamesInitialized = tableColumnNamesInitialized;
	}
	
}
