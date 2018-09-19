package servlet;

/**
 * 订单模块：servlet的实现
 */
import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import entity.DiningTable;
import entity.Food;
import entity.OrderDetail;
import factory.BeanFactory;
import service.IDiningTableService;
import service.IFoodService;
import service.IOrdersService;

@WebServlet("/OrderServlet")
public class OrderServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	// 订单模块的service的
	private IOrdersService orderService = BeanFactory.getInstance("orderService", IOrdersService.class);
	//餐桌的service
	private IDiningTableService diningTbaleService=BeanFactory.getInstance("diningTbaleService", IDiningTableService.class);
	//菜品模块的service
	private IFoodService foodService=BeanFactory.getInstance("foodService", IFoodService.class);
	// 跳转资源
	private Object uri;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// 设置编码
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;charset=UTF-8");

		// 获取请求方式
		String method = request.getParameter("method");
		// 判断
		if("listOrder".equals(method)) {
			listOrder(request,response);
		}else if("detailOrder".equals(method)) {
			detailOrder(request,response);
		}else if("payOrder".equals(method)){
			payOrder(request,response);
		}
	}

	private void payOrder(HttpServletRequest request, HttpServletResponse response) {
	}

	private void detailOrder(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			//获取请求数据封装
			String id = request.getParameter("id");
			//调用业务逻辑
			List<OrderDetail> orderDetailsList = orderService.getOrderDetails(Integer.parseInt(id));
			//获取食物
			List<Food> foodList = foodService.getAllFoods();
			//保存到域中
			request.setAttribute("orderDetailsList", orderDetailsList);
			request.setAttribute("foodList", foodList);
			
			//转发
			uri=request.getRequestDispatcher("/sys/order/order_detail.jsp");
		} catch (Exception e) {
			e.printStackTrace();
			uri="/error/error.jsp";
		}
		goTo(request, response, uri);
		
	}

	private void listOrder(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			//调用业务逻辑
			List<OrderDetail> orderList = orderService.getAllOrderDetails();
			//获取所有的餐桌
			List<DiningTable> tableList = diningTbaleService.getAllTables();
			//保存到域中
			request.setAttribute("orderList", orderList);
			request.setAttribute("tableList", tableList);
			//跳转
			uri=request.getRequestDispatcher("/sys/order/order_list.jsp");
		} catch (Exception e) {
			e.printStackTrace();
			uri="/error/error.jsp";
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
