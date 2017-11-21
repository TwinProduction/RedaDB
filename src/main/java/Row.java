import jdk.nashorn.internal.runtime.JSONFunctions;
import org.json.JSONObject;

import java.util.ArrayList;
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
	
	
	public Object get(String columnName) {
		JSONObject result = new JSONObject();
		result.put(columnName.toLowerCase(), col(columnName));
		return result.toString();
	}
	
	
	public String get(String... columnNames) {
		JSONObject result = new JSONObject();
		for (String columnName : columnNames) {
			result.put(columnName, col(columnName));
		}
		return result.toString();
	}
	
	
	/**
	 * Gets the value matched to the column name. If there is no value set, returns an empty string
	 */
	private String col(String columnName) {
		Object o = columns.get(columnName.toLowerCase());
		return (o == null) ? "" : o.toString();
	}
	
}
