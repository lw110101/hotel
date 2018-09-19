package service;

import java.util.List;

import entity.DiningTable;

public interface IDiningTableService {
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
	  * Ԥ������
	  */
	 void predeterTable(int id);
	 
	 /**
	  * ����
	  */
	 void debook(int id);

}
