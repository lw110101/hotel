package entity;

public class Food {

	private int id;
	private String foodName; // 菜名
	private int foodTypeId; // 所属菜系 (fk)
	private double price;
	private double mprice;
	private String intro; // 菜的介绍
	private String img; // 图片的路径

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getFoodName() {
		return foodName;
	}

	public void setFoodName(String foodName) {
		this.foodName = foodName;
	}

	public int getFoodTypeId() {
		return foodTypeId;
	}

	public void setFoodTypeId(int foodTypeId) {
		this.foodTypeId = foodTypeId;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public double getMprice() {
		return mprice;
	}

	public void setMprice(double mprice) {
		this.mprice = mprice;
	}

	public String getIntro() {
		return intro;
	}

	public void setIntro(String intro) {
		this.intro = intro;
	}

	public String getImg() {
		return img;
	}

	public void setImg(String img) {
		this.img = img;
	}

}
