package dao;

import java.util.List;

import entity.OrderDetail;

public interface IOrdersDao {
	//������ϸ
	List<OrderDetail> getOrderDetails(int id);
	
	//����
	void getAccountById(int id);
	
	/**
	 * ��ȡ���еĶ�����Ϣ
	 */
	List<OrderDetail> getAllOrderDetails();

}
