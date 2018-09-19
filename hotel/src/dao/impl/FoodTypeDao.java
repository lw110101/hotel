package dao.impl;

import java.sql.SQLException;
/**
 * 菜系模块：  菜系的dao实现设计
 */
import java.util.List;

import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;

import dao.IFoodTypeDao;
import entity.FoodType;
import util.JdbcUtils;

public class FoodTypeDao implements IFoodTypeDao {

	@Override
	public void addFoodType(FoodType foodType) {
		try {
			String sql = "insert into FoodType(foodTypeName) value(?)";

			JdbcUtils.getQueryRunner().update(sql, foodType.getFoodTypeName());
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}

	@Override
	public void updateFoodType(FoodType foodType) {
		try {
			String sql = "update foodType set foodTypeName=? where id=?";

			JdbcUtils.getQueryRunner().update(sql, foodType.getFoodTypeName(),foodType.getId());
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}

	@Override
	public void deleteFoodType(int id) {
		try {
			String sql = "delete from foodType where id=?";

			JdbcUtils.getQueryRunner().update(sql,id);
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}

	@Override
	public FoodType queryFoodType(int id) {
		try {
			String sql = "select * from foodType where id=?";

			return JdbcUtils.getQueryRunner().query(sql, new BeanHandler<FoodType>(FoodType.class), id);
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}

	@Override
	public List<FoodType> getAllFoodTypes() {
		try {
			String sql = "select * from foodType";

			return JdbcUtils.getQueryRunner().query(sql, new BeanListHandler<FoodType>(FoodType.class));
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}

	@Override
	public List<FoodType> getAllFoodTypes(String foodTypeName) {
		try {
			String sql = "select * from foodType where foodTypeName like ?";

			return JdbcUtils.getQueryRunner().query(sql, new BeanListHandler<FoodType>(FoodType.class), "%"+foodTypeName+"%");
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}

}
