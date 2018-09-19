package dao;

import java.util.List;

import entity.DiningTable;

public interface IDiningTableDao {
	/**
	 * ��ʾ���в���
	 */
	List<DiningTable> getAllTables();

	/**
	 * ����IDɾ������
	 */
	void deleteTable(int id);
	
	/**
	 * ��Ӳ���
	 */
	void addTable(DiningTable table);
	
	/**
	 * ���ݲ�����������ѯ����
	 */
	List<DiningTable> queryTable(String tableName);    
	/**
	  * ���ݲ���IDԤ������
	  */
	 void predeterTable(int id);
	 
	 /**
	  * ����ID����
	  */
	 void debook(int id);

}
