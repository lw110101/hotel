package dao.impl;

import java.util.List;

import org.apache.commons.dbutils.handlers.BeanListHandler;

import dao.IOrdersDao;
import entity.OrderDetail;
import entity.OrderStatus;
import util.JdbcUtils;

public class OrdersDao implements IOrdersDao {

	@Override
	public List<OrderDetail> getOrderDetails(int id) {
		try {
			String sql = "select * from orders where id=?";
			
			return JdbcUtils.getQueryRunner().query(sql, new BeanListHandler<OrderDetail>(OrderDetail.class), id);
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	@Override
	public void getAccountById(int id) {
		try {
			String sql = "update from orders set orderStatus = ? where id=?";
			
			JdbcUtils.getQueryRunner().update(sql, OrderStatus.payed.ordinal(), id);
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	@Override
	public List<OrderDetail> getAllOrderDetails() {
		try {
			String sql = "select *  from orders ";
			
			return JdbcUtils.getQueryRunner().query(sql, new BeanListHandler<OrderDetail>(OrderDetail.class));
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

}
