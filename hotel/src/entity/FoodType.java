package entity;

/**
 * 一：菜系模块    实体类设计
 * @author hasee 下午9:05:46
 */
public class FoodType {

	private int id;    //类别主键
	private String foodTypeName;    //类名
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getFoodTypeName() {
		return foodTypeName;
	}
	public void setFoodTypeName(String foodTypeName) {
		this.foodTypeName = foodTypeName;
	}
	
	
}
