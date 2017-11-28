package org.twinnation.redadb;

import org.json.JSONObject;

import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class Row {
	
	private Map<String, Object> columns;
	
	
	public Row(List<String> columnNames, Object... objects) {
		columns = new HashMap<>();
		for (int i = 0; i < columnNames.size(); i++) {
			if (objects.length <= i) break;
			this.columns.put(columnNames.get(i).toLowerCase(), objects[i]);
		}
	}
	
	
	/**
	 * This is used for 'SELECT col' OR 'SELECT *'
	 * <code>
	 *     row.get("col1");
	 *     row.get("*");
	 * </code>
	 */
	public Object get(String columnName) {
		JSONObject result = new JSONObject();
		if (columnName.equals(("*"))) {
			result = getAll();
		} else {
			result.put(columnName.toLowerCase(), col(columnName));
		}
		return result.toString();
	}
	
	
	/**
	 * This is used for 'SELECT col' OR 'SELECT *'
	 * <code>
	 *     row.get("col1");
	 *     row.get("*");
	 * </code>
	 */
	public void set(String columnName, Object value) {
		columns.put(columnName.toLowerCase(), value);
	}
	
	
	/**
	 * This is used for 'SELECT col1, col2, ...'
	 * @param columnNames
	 * @return
	 */
	public String get(String... columnNames) {
		JSONObject result = new JSONObject();
		for (String columnName : columnNames) {
			result.put(columnName, col(columnName));
		}
		return result.toString();
	}
	
	
	/**
	 * This is used for 'SELECT *'
	 */
	private JSONObject getAll() {
		JSONObject result = new JSONObject();
		for (String columnName : columns.keySet()) {
			result.put(columnName, col(columnName));
		}
		return result;
	}
	
	
	/**
	 * Gets the value matched to the column name. If there is no value set, returns an empty string
	 */
	private String col(String columnName) {
		Object o = columns.get(columnName.toLowerCase());
		return (o == null) ? "" : o.toString();
	}
	
	
	@Override public String toString() {
		return "org.twinnation.redadb.Row{" +
			  "columns=" + columns +
			  '}';
	}
}
