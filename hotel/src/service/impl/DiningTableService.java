package service.impl;

import java.util.List;

import dao.IDiningTableDao;
import entity.DiningTable;
import factory.BeanFactory;
import service.IDiningTableService;

public class DiningTableService implements IDiningTableService {
//	private IDiningTableDao dao = new DiningTableDao();
	private IDiningTableDao diningTabledao =BeanFactory.getInstance("diningTabledao", IDiningTableDao.class);

	@Override
	public void deleteTable(int id) {
		try {
			diningTabledao.deleteTable(id);
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	@Override
	public void addTable(DiningTable table) {
		try {
			diningTabledao.addTable(table);
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	@Override
	public void predeterTable(int id) {
		try {
			diningTabledao.predeterTable(id);
			
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	@Override
	public void debook(int id) {
		try {
			diningTabledao.debook(id);
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	@Override
	public List<DiningTable> getAllTables() {
		try {
			return diningTabledao.getAllTables();
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
		
	}

	@Override
	public List<DiningTable> queryTable(String tableName) {
		try {
			return diningTabledao.queryTable(tableName);
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
		
	}
}
