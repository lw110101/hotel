package entity;

import java.sql.Timestamp;

public class DiningTable {

	private int id;
	private String tableName;
	private int tableStatus;
	private Timestamp scheduleDate;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getTableName() {
		return tableName;
	}
	public void setTableName(String tableName) {
		this.tableName = tableName;
	}
	public int getTableStatus() {
		return tableStatus;
	}
	public void setTableStatus(int tableStatus) {
		this.tableStatus = tableStatus;
	}
	public Timestamp getScheduleDate() {
		return scheduleDate;
	}
	public void setScheduleDate(Timestamp scheduleDate) {
		this.scheduleDate = scheduleDate;
	}
	
}
