package entity;

public class OrderDetail {

	private int id;
	private int orderId; // -- ���ö������������˵�����ĸ�����
	private int foodId;// -- ���ò˵���Ϣ�������
	private int foodCount;// -- �˵�����

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getOrderId() {
		return orderId;
	}

	public void setOrderId(int orderId) {
		this.orderId = orderId;
	}

	public int getFoodId() {
		return foodId;
	}

	public void setFoodId(int foodId) {
		this.foodId = foodId;
	}

	public int getFoodCount() {
		return foodCount;
	}

	public void setFoodCount(int foodCount) {
		this.foodCount = foodCount;
	}

}
