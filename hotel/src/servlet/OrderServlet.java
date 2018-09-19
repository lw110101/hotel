package servlet;

/**
 * ����ģ�飺servlet��ʵ��
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
	// ����ģ���service��
	private IOrdersService orderService = BeanFactory.getInstance("orderService", IOrdersService.class);
	//������service
	private IDiningTableService diningTbaleService=BeanFactory.getInstance("diningTbaleService", IDiningTableService.class);
	//��Ʒģ���service
	private IFoodService foodService=BeanFactory.getInstance("foodService", IFoodService.class);
	// ��ת��Դ
	private Object uri;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// ���ñ���
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;charset=UTF-8");

		// ��ȡ����ʽ
		String method = request.getParameter("method");
		// �ж�
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
			//��ȡ�������ݷ�װ
			String id = request.getParameter("id");
			//����ҵ���߼�
			List<OrderDetail> orderDetailsList = orderService.getOrderDetails(Integer.parseInt(id));
			//��ȡʳ��
			List<Food> foodList = foodService.getAllFoods();
			//���浽����
			request.setAttribute("orderDetailsList", orderDetailsList);
			request.setAttribute("foodList", foodList);
			
			//ת��
			uri=request.getRequestDispatcher("/sys/order/order_detail.jsp");
		} catch (Exception e) {
			e.printStackTrace();
			uri="/error/error.jsp";
		}
		goTo(request, response, uri);
		
	}

	private void listOrder(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			//����ҵ���߼�
			List<OrderDetail> orderList = orderService.getAllOrderDetails();
			//��ȡ���еĲ���
			List<DiningTable> tableList = diningTbaleService.getAllTables();
			//���浽����
			request.setAttribute("orderList", orderList);
			request.setAttribute("tableList", tableList);
			//��ת
			uri=request.getRequestDispatcher("/sys/order/order_list.jsp");
		} catch (Exception e) {
			e.printStackTrace();
			uri="/error/error.jsp";
		}
		goTo(request, response, uri);
	}
	/*
	 * ��ת��ͨ�÷���
	 */

	private void goTo(HttpServletRequest request, HttpServletResponse response, Object uri)
			throws ServletException, IOException {
		// �ж�
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
