package service;

import java.util.List;

import entity.Food;

public interface IFoodService {

	/**
	 * ��Ӳ�Ʒ
	 */
	void addFood(Food food);

	/**
	 * ���²�Ʒ
	 */
	void updateFood(Food food);

	/**
	 * ɾ����Ʒ
	 */
	void deleteFood(int id);

	/**
	 * ����id��ѯ��Ʒ
	 */
	Food queryFoodById(int id);

	/**
	 * ��ѯȫ����Ʒ
	 */
	List<Food> getAllFoods();

	/**
	 * ���ݲ�����ѯ��Ʒ
	 */
	List<Food> queryFood(String keyword);
}
