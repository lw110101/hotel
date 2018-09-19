package dao.impl;
/**
 * ¶þ£º²Í×ÀÄ£¿é
 */
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.Date;
import java.util.List;

import org.apache.commons.dbutils.handlers.BeanListHandler;

import dao.IDiningTableDao;
import entity.DiningTable;
import entity.TableStatus;
import util.JdbcUtils;

public class DiningTableDao implements IDiningTableDao {

	@Override
	public void deleteTable(int id) {
		try {
			// sqlÓï¾ä
			String sql = "delete from DiningTable where id=?";
			// Ö´ÐÐsqlÓï¾ä
			JdbcUtils.getQueryRunner().update(sql, id);
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	@Override
	public void addTable(DiningTable table) {
		try {
			// sqlÓï¾ä
			String sql = "insert into DiningTable(tableName,tableStatus,scheduleDate) values(?,?,?)";
			// Ö´ÐÐsql
			JdbcUtils.getQueryRunner().update(sql, table.getTableName(), table.getTableStatus(),
					table.getScheduleDate());
		} catch (Exception e) {
			throw new RuntimeException(e);
		}

	}

	@Override
	public List<DiningTable> queryTable(String tableName) {
		try {
			String sql = "select * from DiningTable where tableName like ?";

			return JdbcUtils.getQueryRunner().query(sql, new BeanListHandler<DiningTable>(DiningTable.class),
					"%" + tableName + "%");
		} catch (Exception e) {
			throw new RuntimeException(e);
		}

	}


	@Override
	public void predeterTable(int id) {
		try {
			String sql = "update DiningTable set tableStatus=? ,scheduleDate=? where id=?";

			JdbcUtils.getQueryRunner().update(sql, TableStatus.predetermine.ordinal(),new Timestamp(new Date().getTime()), id);
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	@Override
	public void debook(int id) {
		try {
			String sql = "update DiningTable set tableStatus=? ,scheduleDate=? where id=?";
			JdbcUtils.getQueryRunner().update(sql, TableStatus.free.ordinal(), null, id);
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	@Override
	public List<DiningTable> getAllTables() {
		try {
			String sql = "select * from DiningTable ";

			return JdbcUtils.getQueryRunner().query(sql, new BeanListHandler<DiningTable>(DiningTable.class));
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}
}
