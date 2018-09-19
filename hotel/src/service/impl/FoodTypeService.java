package service.impl;

import java.util.List;

import dao.IFoodTypeDao;
import entity.FoodType;
import factory.BeanFactory;
import service.IFoodTypeService;

public class FoodTypeService implements IFoodTypeService {
//	private IFoodTypeDao dao=new FoodTypeDao();
	private IFoodTypeDao foodTypeDao=BeanFactory.getInstance("foodTypeDao", IFoodTypeDao.class);
	@Override
	public void addFoodType(FoodType foodType) {
		try {
			foodTypeDao.addFoodType(foodType);
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	@Override
	public void updateFoodType(FoodType foodType) {
		try {
			foodTypeDao.updateFoodType(foodType);
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	@Override
	public void deleteFoodType(int id) {
		try {
			foodTypeDao.deleteFoodType(id);
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	@Override
	public FoodType queryFoodType(int id) {
		try {
			return foodTypeDao.queryFoodType(id);
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	@Override
	public List<FoodType> getAllFoodTypes() {
		try {
			return foodTypeDao.getAllFoodTypes();
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	@Override
	public List<FoodType> getAllFoodTypes(String foodTypeName) {
		try {
			return foodTypeDao.getAllFoodTypes(foodTypeName);
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

}
