package service;

import java.util.List;

import entity.OrderDetail;

public interface IOrdersService {

	//������ϸ
		List<OrderDetail> getOrderDetails(int id);
		
		//����
		void getAccountById(int id);
		
		/**
		 * ��ȡ���еĶ�����Ϣ
		 */
		List<OrderDetail> getAllOrderDetails();
}
