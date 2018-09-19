package entity;

import java.sql.Timestamp;

public class Orders {
	private int id; // -- 订单编号
	private int diningTableId; // -- 餐桌名（外键）
	private Timestamp orderDate;
	private double totalPrice;
	private int orderStatus; // -- 订单状态： 0：未结账 1:结账了

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
