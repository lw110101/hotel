package dao.impl;

import java.sql.SQLException;
import java.util.List;

import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;

import dao.IFoodDao;
import entity.Food;
import util.JdbcUtils;

public class FoodDao implements IFoodDao {

	@Override
	public void addFood(Food food) {
		try {
			String sql = "insert into food(foodName,foodTypeId,price,mprice,intro,img) values(?,?,?,?,?,?)";
			// 将参数封装到数组中
			Object[] param = { food.getFoodName(), food.getFoodTypeId(), food.getPrice(), food.getMprice(),
					food.getIntro(), food.getImg() };

			JdbcUtils.getQueryRunner().update(sql, param);
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}

	@Override
	public void updateFood(Food food) {
		try {
			String sql = "update food set foodName=?,foodTypeId=?,price=?,mprice=?,intro=?,img=? where id=?";
			// 将参数封装到数组中
			Object[] param = { food.getFoodName(), food.getFoodTypeId(), food.getPrice(), food.getMprice(),
					food.getIntro(), food.getImg(), food.getId() };

			JdbcUtils.getQueryRunner().update(sql, param);
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}

	@Override
	public void deleteFood(int id) {
		try {
			String sql = "delete from food where id=?";

			JdbcUtils.getQueryRunner().update(sql, id);
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}

	@Override
	public List<Food> getAllFoods() {
		try {
			String sql = "select * from food ";

			return JdbcUtils.getQueryRunner().query(sql, new BeanListHandler<Food>(Food.class));
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}


	@Override
	public List<Food> queryFood(String keyword) {
		try {
			String sql = "select * from food where foodName like ?";

			return JdbcUtils.getQueryRunner().query(sql, new BeanListHandler<Food>(Food.class), "%"+keyword+"%");
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}


	@Override
	public Food queryFoodById(int id) {
		try {
			String sql = "select * from food where id=?";

			return JdbcUtils.getQueryRunner().query(sql, new BeanHandler<Food>(Food.class), id);
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}

}
