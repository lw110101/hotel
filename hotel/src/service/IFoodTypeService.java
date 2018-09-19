package service;

import java.util.List;

import entity.FoodType;

/**
 * ��ϵģ�飺    service�����
 * @author hasee ����9:27:24
 */
public interface IFoodTypeService {
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
