package service;

import java.util.List;

import entity.Food;

public interface IFoodService {

	/**
	 * 添加菜品
	 */
	void addFood(Food food);

	/**
	 * 更新菜品
	 */
	void updateFood(Food food);

	/**
	 * 删除菜品
	 */
	void deleteFood(int id);

	/**
	 * 根据id查询菜品
	 */
	Food queryFoodById(int id);

	/**
	 * 查询全部菜品
	 */
	List<Food> getAllFoods();

	/**
	 * 根据菜名查询菜品
	 */
	List<Food> queryFood(String keyword);
}
