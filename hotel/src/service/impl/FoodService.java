package service.impl;

import java.util.List;

import dao.IFoodDao;
import entity.Food;
import factory.BeanFactory;
import service.IFoodService;

public class FoodService implements IFoodService {
//	private IFoodDao dao = new FoodDao();
	private IFoodDao foodDao=BeanFactory.getInstance("foodDao", IFoodDao.class);
	@Override
	public void addFood(Food food) {
		try {
			foodDao.addFood(food);

		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	@Override
	public void updateFood(Food food) {
		try {
			foodDao.updateFood(food);

		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	@Override
	public void deleteFood(int id) {
		try {
			foodDao.deleteFood(id);

		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	@Override
	public Food queryFoodById(int id) {
		try {
			return foodDao.queryFoodById(id);

		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	@Override
	public List<Food> getAllFoods() {
		try {
			return foodDao.getAllFoods();

		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	@Override
	public List<Food> queryFood(String keyword) {
		try {
			return foodDao.queryFood(keyword);

		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}


}
