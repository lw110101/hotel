package service;

import java.util.List;

import entity.DiningTable;

public interface IDiningTableService {
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
	  * 预定餐桌
	  */
	 void predeterTable(int id);
	 
	 /**
	  * 退桌
	  */
	 void debook(int id);

}
