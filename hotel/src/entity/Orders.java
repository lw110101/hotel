package entity;

import java.sql.Timestamp;

public class Orders {
	private int id; // -- �������
	private int diningTableId; // -- �������������
	private Timestamp orderDate;
	private double totalPrice;
	private int orderStatus; // -- ����״̬�� 0��δ���� 1:������

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getDiningTableId() {
		return diningTableId;
	}

	public void setDiningTableId(int diningTableId) {
		this.diningTableId = diningTableId;
	}

	public Timestamp getOrderDate() {
		return orderDate;
	}

	public void setOrderDate(Timestamp orderDate) {
		this.orderDate = orderDate;
	}

	public double getTotalPrice() {
		return totalPrice;
	}

	public void setTotalPrice(double totalPrice) {
		this.totalPrice = totalPrice;
	}

	public int getOrderStatus() {
		return orderStatus;
	}

	public void setOrderStatus(int orderStatus) {
		this.orderStatus = orderStatus;
	}

}
