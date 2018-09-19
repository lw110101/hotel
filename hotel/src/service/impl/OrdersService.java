package service.impl;

import java.util.List;

import dao.IOrdersDao;
import entity.OrderDetail;
import factory.BeanFactory;
import service.IOrdersService;

public class OrdersService implements IOrdersService {

//	private IOrdersDao dao = new OrdersDao();
	private IOrdersDao orderDao=BeanFactory.getInstance("orderDao", IOrdersDao.class);

	@Override
	public List<OrderDetail> getOrderDetails(int id) {
		try {
			return orderDao.getOrderDetails(id);
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	@Override
	public void getAccountById(int id) {
		try {
			 orderDao.getAccountById(id);
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	@Override
	public List<OrderDetail> getAllOrderDetails() {
		try {
			return orderDao.getAllOrderDetails();
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

}
