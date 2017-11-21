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
	
	
	public List<Row> getRows() {
		return rows;
	}
	
	
	public List<Row> getRowsWhere() {
		return rows;
	}
	
	
	public void insert(Object... objects) {
		if (!tableColumnNamesInitialized) {
			System.out.println("TABLE COLUMN NAMES ARE NOT INITIALIZED");
		} else {
			rows.add(new Row(columnNames, objects));
		}
	}
	
	
	
}
