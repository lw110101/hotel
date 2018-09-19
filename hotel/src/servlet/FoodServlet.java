package servlet;

import java.io.File;
import java.io.IOException;
import java.util.Enumeration;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

import org.apache.commons.beanutils.BeanUtils;

import entity.Food;
import entity.FoodType;
import factory.BeanFactory;
import service.IFoodService;
import service.IFoodTypeService;

/**
 * food模块的servlet设计
 * 
 * @author hasee 下午8:32:57
 */
@MultipartConfig
@WebServlet("/FoodServlet")
public class FoodServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	// food模块的foodService
	private IFoodService foodService = BeanFactory.getInstance("foodService", IFoodService.class);
	// foodType模块的foodService
	private IFoodTypeService foodTypeService = BeanFactory.getInstance("foodTypeService",IFoodTypeService.class);
	// 跳转资源
	private Object uri;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// 设置编码
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset=UTF-8");
		// 获取请求方式
		String method = request.getParameter("method");
		// 判断
		if ("addFood".equals(method)) {
			addFood(request, response);
		} else if ("deleteFood".equals(method)) {
			deleteFood(request, response);
		} else if ("listFood".equals(method)) {
			listFood(request, response);
		} else if ("updateFood".equals(method)) {
			updateFood(request, response);
		} else if ("searchFood".equals(method)) {
			searchFood(request, response);
		} else if ("updateView".equals(method)) {
			updateView(request, response);
		} else if ("getAllFoodTypes".equals(method)) {
			getAllFoodTypes(request, response);
		}
	}

	private void updateView(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		try {
			// 获取所有的食物品类
			List<FoodType> typeList = foodTypeService.getAllFoodTypes();
			// 1.获取请求数据封装
			String id = request.getParameter("id");
			// 2.根据id查询菜品
			Food food = foodService.queryFoodById(Integer.parseInt(id));
			// 3.将food保存到域对象中
			request.setAttribute("typeList", typeList);
			request.setAttribute("food", food);
			// 4.跳转
			uri = request.getRequestDispatcher("/sys/food/food_update.jsp");
		} catch (Exception e) {
			e.printStackTrace();
			uri = "/error/error.jsp";
		}
		goTo(request, response, uri);
	}

	private void searchFood(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		try {
			// 1.获取请求数据封装
			String keyword = request.getParameter("keyword");
			// 调用业务逻辑方法
			List<Food> foodList = foodService.queryFood(keyword);
			// 获取所有的食物品类
			List<FoodType> typeList = foodTypeService.getAllFoodTypes();
			// 2.保存到域对象中
			request.setAttribute("foodList", foodList);
			request.setAttribute("typeList", typeList);
			// 3.跳转
			uri = request.getRequestDispatcher("/sys/food/food_list.jsp");
		} catch (Exception e) {
			e.printStackTrace();
			uri = "/error/error.jsp";
		}
		goTo(request, response, uri);
	}

	private void updateFood(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		try {
			// 构建上传文件保存路径
			String savePath = "D:\\apache-tomcat-9.0.6\\webapps\\fileUpload";
			File file = new File(savePath);
			// 判断文件是否存在
			if (!file.exists()) {
				file.mkdir();
			}
			// 文件上传类型
			Part part = request.getPart("imageUrl");
			// 获取上传文件名
			String fileName = part.getSubmittedFileName();
			// 把文件写到指定路径
			part.write(savePath + File.separator + fileName);
			part.delete();
			// 普通文本类型的处理
			Enumeration<String> parameterNames = request.getParameterNames();
			// 需要封装的对象
			Food food = new Food();
			food.setImg(fileName);
			while (parameterNames.hasMoreElements()) {
				String name = parameterNames.nextElement();
				String value = request.getParameter(name);
				BeanUtils.setProperty(food, name, value);
			}

			// 2.调用逻辑
			foodService.updateFood(food);
			// 跳转
			uri = request.getRequestDispatcher("/FoodServlet?method=listFood");
		} catch (Exception e) {
			e.printStackTrace();
			uri = "/error/error.jsp";
		}
		goTo(request, response, uri);
	}

	private void listFood(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		try {
			// 1.调用业务逻辑获取所有的食物
			List<Food> foodList = foodService.getAllFoods();
			// 获取所有的食物品类
			List<FoodType> typeList = foodTypeService.getAllFoodTypes();
			// 2.保存到域对象中
			request.setAttribute("foodList", foodList);
			request.setAttribute("typeList", typeList);
			// 3.跳转
			uri = request.getRequestDispatcher("/sys/food/food_list.jsp");

		} catch (Exception e) {
			e.printStackTrace();
			uri = "/error/error.jsp";
		}
		goTo(request, response, uri);

	}

	private void deleteFood(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		try {
			// 1.获取请求数据封装
			String id = request.getParameter("id");
			// 2调用业务逻辑
			foodService.deleteFood(Integer.parseInt(id));
			// 3.跳转
			uri = request.getRequestDispatcher("/FoodServlet?method=listFood");
		} catch (Exception e) {
			e.printStackTrace();
			uri = "/error/error.jsp";
		}
		goTo(request, response, uri);
	}

	private void addFood(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		try {
			// 构建上传文件保存路径
			String savePath = "D:\\apache-tomcat-9.0.6\\webapps\\fileUpload";
			File file = new File(savePath);
			// 判断文件是否存在
			if (!file.exists()) {
				file.mkdir();
			}
			// 文件上传类型
			Part part = request.getPart("imageUrl");
			// 获取上传文件名
			String fileName = part.getSubmittedFileName();
			// 把文件写到指定路径
			part.write(savePath + File.separator + fileName);
			part.delete();
			// 普通文本类型的处理
			Enumeration<String> parameterNames = request.getParameterNames();
			// 需要封装的对象
			Food food = new Food();
			food.setImg(fileName);
			while (parameterNames.hasMoreElements()) {
				String name = parameterNames.nextElement();
				String value = request.getParameter(name);

				BeanUtils.setProperty(food, name, value);
			}
			// 2.调用业务逻辑
			foodService.addFood(food);

			// 跳转
			uri = request.getRequestDispatcher("/FoodServlet?method=listFood");
		} catch (Exception e) {
			e.printStackTrace();
			uri = "/error/error.jsp";
		}
		goTo(request, response, uri);
	}

	private void getAllFoodTypes(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		try {
			List<FoodType> typeList = foodTypeService.getAllFoodTypes();
			request.setAttribute("typeList", typeList);
			// 跳转
			uri = request.getRequestDispatcher("/sys/food/food_add.jsp");
		} catch (Exception e) {
			e.printStackTrace();
			uri = "/error/error.jsp";
		}
		goTo(request, response, uri);

	}

	/**
	 * 跳转通用方法
	 */
	private void goTo(HttpServletRequest request, HttpServletResponse response, Object uri)
			throws ServletException, IOException {
		// 判断
		if (uri instanceof RequestDispatcher) {
			((RequestDispatcher) uri).forward(request, response);

		} else if (uri instanceof String) {
			response.sendRedirect(request.getContextPath() + uri);
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

}
