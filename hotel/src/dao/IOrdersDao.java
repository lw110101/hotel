package dao;

import java.util.List;

import entity.OrderDetail;

public interface IOrdersDao {
	//订单详细
	List<OrderDetail> getOrderDetails(int id);
	
	//结账
	void getAccountById(int id);
	
	/**
	 * 获取所有的订单信息
	 */
	List<OrderDetail> getAllOrderDetails();

}
