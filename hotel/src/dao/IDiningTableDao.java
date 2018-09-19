package dao;

import java.util.List;

import entity.DiningTable;

public interface IDiningTableDao {
	/**
	 * 显示所有餐桌
	 */
	List<DiningTable> getAllTables();

	/**
	 * 根据ID删除餐桌
	 */
	void deleteTable(int id);
	
	/**
	 * 添加餐桌
	 */
	void addTable(DiningTable table);
	
	/**
	 * 根据餐桌名条件查询餐桌
	 */
	List<DiningTable> queryTable(String tableName);    
	/**
	  * 根据餐桌ID预定餐桌
	  */
	 void predeterTable(int id);
	 
	 /**
	  * 根据ID退桌
	  */
	 void debook(int id);

}
