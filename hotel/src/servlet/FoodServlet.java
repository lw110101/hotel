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
 * foodģ���servlet���
 * 
 * @author hasee ����8:32:57
 */
@MultipartConfig
@WebServlet("/FoodServlet")
public class FoodServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	// foodģ���foodService
	private IFoodService foodService = BeanFactory.getInstance("foodService", IFoodService.class);
	// foodTypeģ���foodService
	private IFoodTypeService foodTypeService = BeanFactory.getInstance("foodTypeService",IFoodTypeService.class);
	// ��ת��Դ
	private Object uri;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// ���ñ���
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset=UTF-8");
		// ��ȡ����ʽ
		String method = request.getParameter("method");
		// �ж�
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
			// ��ȡ���е�ʳ��Ʒ��
			List<FoodType> typeList = foodTypeService.getAllFoodTypes();
			// 1.��ȡ�������ݷ�װ
			String id = request.getParameter("id");
			// 2.����id��ѯ��Ʒ
			Food food = foodService.queryFoodById(Integer.parseInt(id));
			// 3.��food���浽�������
			request.setAttribute("typeList", typeList);
			request.setAttribute("food", food);
			// 4.��ת
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
			// 1.��ȡ�������ݷ�װ
			String keyword = request.getParameter("keyword");
			// ����ҵ���߼�����
			List<Food> foodList = foodService.queryFood(keyword);
			// ��ȡ���е�ʳ��Ʒ��
			List<FoodType> typeList = foodTypeService.getAllFoodTypes();
			// 2.���浽�������
			request.setAttribute("foodList", foodList);
			request.setAttribute("typeList", typeList);
			// 3.��ת
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
			// �����ϴ��ļ�����·��
			String savePath = "D:\\apache-tomcat-9.0.6\\webapps\\fileUpload";
			File file = new File(savePath);
			// �ж��ļ��Ƿ����
			if (!file.exists()) {
				file.mkdir();
			}
			// �ļ��ϴ�����
			Part part = request.getPart("imageUrl");
			// ��ȡ�ϴ��ļ���
			String fileName = part.getSubmittedFileName();
			// ���ļ�д��ָ��·��
			part.write(savePath + File.separator + fileName);
			part.delete();
			// ��ͨ�ı����͵Ĵ���
			Enumeration<String> parameterNames = request.getParameterNames();
			// ��Ҫ��װ�Ķ���
			Food food = new Food();
			food.setImg(fileName);
			while (parameterNames.hasMoreElements()) {
				String name = parameterNames.nextElement();
				String value = request.getParameter(name);
				BeanUtils.setProperty(food, name, value);
			}

			// 2.�����߼�
			foodService.updateFood(food);
			// ��ת
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
			// 1.����ҵ���߼���ȡ���е�ʳ��
			List<Food> foodList = foodService.getAllFoods();
			// ��ȡ���е�ʳ��Ʒ��
			List<FoodType> typeList = foodTypeService.getAllFoodTypes();
			// 2.���浽�������
			request.setAttribute("foodList", foodList);
			request.setAttribute("typeList", typeList);
			// 3.��ת
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
			// 1.��ȡ�������ݷ�װ
			String id = request.getParameter("id");
			// 2����ҵ���߼�
			foodService.deleteFood(Integer.parseInt(id));
			// 3.��ת
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
			// �����ϴ��ļ�����·��
			String savePath = "D:\\apache-tomcat-9.0.6\\webapps\\fileUpload";
			File file = new File(savePath);
			// �ж��ļ��Ƿ����
			if (!file.exists()) {
				file.mkdir();
			}
			// �ļ��ϴ�����
			Part part = request.getPart("imageUrl");
			// ��ȡ�ϴ��ļ���
			String fileName = part.getSubmittedFileName();
			// ���ļ�д��ָ��·��
			part.write(savePath + File.separator + fileName);
			part.delete();
			// ��ͨ�ı����͵Ĵ���
			Enumeration<String> parameterNames = request.getParameterNames();
			// ��Ҫ��װ�Ķ���
			Food food = new Food();
			food.setImg(fileName);
			while (parameterNames.hasMoreElements()) {
				String name = parameterNames.nextElement();
				String value = request.getParameter(name);

				BeanUtils.setProperty(food, name, value);
			}
			// 2.����ҵ���߼�
			foodService.addFood(food);

			// ��ת
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
			// ��ת
			uri = request.getRequestDispatcher("/sys/food/food_add.jsp");
		} catch (Exception e) {
			e.printStackTrace();
			uri = "/error/error.jsp";
		}
		goTo(request, response, uri);

	}

	/**
	 * ��תͨ�÷���
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
