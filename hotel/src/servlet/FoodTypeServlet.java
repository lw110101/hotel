package servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import entity.FoodType;
import factory.BeanFactory;
import service.IFoodTypeService;

/**
 * 4. 菜系管理Servlet开发
 * 
 * a. 添加菜系 b. 菜系列表展示 c. 进入更新页面 d. 删除 e. 更新
 */
@WebServlet("/FoodTypeServlet")
public class FoodTypeServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	// 调用的菜系Servic
	private IFoodTypeService foodTypeService = BeanFactory.getInstance("foodTypeService", IFoodTypeService.class);
	// 跳转的资源
	private Object uri;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// 设置编码
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;charset=UTF-8");
		// 获取操作方式
		String method = request.getParameter("method");
		// 判断
		if ("addFoodType".equals(method)) {
			// 添加菜系
			addFoodtype(request, response);

		} else if ("listType".equals(method)) {
			// 页面展示
			listType(request, response);

		} else if ("viewUpdate".equals(method)) {
			// 进入更新页面
			viewUpdate(request, response);
		} else if ("delete".equals(method)) {
			// 删除
			delete(request, response);
		} else if ("update".equals(method)) {
			// 更新
			update(request, response);
		} else if ("search".equals(method)) {
			// 搜索
			search(request, response);
		}

	}

	private void search(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			// 1.获取请求数据封装
			String keyword = request.getParameter("keyword");
			// 2.调用service的业务逻辑查询指定的的类别
			List<FoodType> foodTypeList = foodTypeService.getAllFoodTypes(keyword);
			// 3.保存到域对象中
			request.setAttribute("foodTypeList", foodTypeList);
			// 4.跳转
			uri = request.getRequestDispatcher("/sys/type/foodType_list.jsp");
		} catch (Exception e) {
			e.printStackTrace();
			uri = "/error/error.jsp";
		}
		goTo(request, response, uri);
	}

	private void update(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			// 1、获取请求数据封装
			String foodTypeName = request.getParameter("foodTypeName");
			String id = request.getParameter("id");
			// 封装的对象
			FoodType foodType = new FoodType();
			foodType.setFoodTypeName(foodTypeName);
			foodType.setId(Integer.parseInt(id));

			// 2.调用service的业务逻辑
			foodTypeService.updateFoodType(foodType);
			// 3.跳转
			uri="/FoodTypeServlet?method=listType";
		} catch (Exception e) {
			e.printStackTrace();
			uri = "/error/error.jsp";
		}
		goTo(request, response, uri);
	}

	private void delete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			// 1.获取请求数据封装
			String id = request.getParameter("id");
			// 2.调用servcie的业务逻辑
			foodTypeService.deleteFoodType(Integer.parseInt(id));
			// 3.跳转
			uri="/FoodTypeServlet?method=listType";
		} catch (Exception e) {
			e.printStackTrace();
			uri = "/error/error.jsp";
		}
		goTo(request, response, uri);
	}

	private void viewUpdate(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		try {
			// 1.获取请求数据封装
			String id = request.getParameter("id");
			// 2.根据ID查询对象
			FoodType foodType = foodTypeService.queryFoodType(Integer.parseInt(id));
			// 将对象封装到域对象中
			request.setAttribute("foodType", foodType);
			// 3.跳转
			uri = request.getRequestDispatcher("/sys/type/foodType_update.jsp");
		} catch (Exception e) {
			e.printStackTrace();
			uri = "/error/error.jsp";
		}
		goTo(request, response, uri);
	}

	private void listType(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			// 1.调用service的业务逻辑查询所有的类别
			List<FoodType> foodTypeList = foodTypeService.getAllFoodTypes();
			// 2.保存到域对象中
			request.setAttribute("foodTypeList", foodTypeList);
			// 3.跳转
			uri = request.getRequestDispatcher("/sys/type/foodType_list.jsp");
		} catch (Exception e) {
			e.printStackTrace();
			uri = "/error/error.jsp";
		}
		goTo(request, response, uri);

	}

	private void addFoodtype(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		try {
			// 1.获取请求数据封装
			String foodTypeName = request.getParameter("foodTypeName");
			// 封装的对象
			FoodType foodType = new FoodType();
			// 封装对象
			foodType.setFoodTypeName(foodTypeName);

			// 2.调用service的业务逻辑
			foodTypeService.addFoodType(foodType);
			// 3.转发资源
			uri = request.getRequestDispatcher("FoodTypeServlet?method=listType");
		} catch (Exception e) {
			e.printStackTrace();
			uri = "/error/error.jsp";
		}

		goTo(request, response, uri);
	}

	/*
	 * 跳转的通用方法
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
