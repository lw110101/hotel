package dao;

import java.util.List;

import entity.FoodType;

/**
 * ��ϵģ�飺   dao���
 * @author hasee ����9:07:40
 */
public interface IFoodTypeDao {
	/**
	 * ���
	 */
	void addFoodType(FoodType foodType);

	/**
	 * ����
	 */
	void updateFoodType(FoodType foodType);
	
	/**
	 * ����IDɾ��
	 */
	void deleteFoodType(int id);
	
	/**
	 * ����������ѯ
	 */
	FoodType queryFoodType(int id);
	
	/**
	 * ��ѯȫ��
	 */
	List<FoodType> getAllFoodTypes();
	
	/**
	 * ���ݲ�ϵ����ѯ
	 */
	List<FoodType> getAllFoodTypes(String foodTypeName);
 }
