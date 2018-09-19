package entity;

public class OrderDetail {

	private int id;
	private int orderId; // -- 引用订单表的主键，说明是哪个订单
	private int foodId;// -- 引用菜的信息表的主键
	private int foodCount;// -- 菜的数量

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
