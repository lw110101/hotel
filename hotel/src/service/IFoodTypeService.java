package service;

import java.util.List;

import entity.FoodType;

/**
 * 菜系模块：    service的设计
 * @author hasee 下午9:27:24
 */
public interface IFoodTypeService {
	/**
	 * 添加
	 */
	void addFoodType(FoodType foodType);

	/**
	 * 更新
	 */
	void updateFoodType(FoodType foodType);
	
	/**
	 * 根据ID删除
	 */
	void deleteFoodType(int id);
	
	/**
	 * 根据主键查询
	 */
	FoodType queryFoodType(int id);
	
	/**
	 * 查询全部
	 */
	List<FoodType> getAllFoodTypes();
	
	/**
	 * 根据菜系名查询
	 */
	List<FoodType> getAllFoodTypes(String foodTypeName);

}
