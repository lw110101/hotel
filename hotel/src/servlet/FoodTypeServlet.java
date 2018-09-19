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
 * 4. ��ϵ����Servlet����
 * 
 * a. ��Ӳ�ϵ b. ��ϵ�б�չʾ c. �������ҳ�� d. ɾ�� e. ����
 */
@WebServlet("/FoodTypeServlet")
public class FoodTypeServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	// ���õĲ�ϵServic
	private IFoodTypeService foodTypeService = BeanFactory.getInstance("foodTypeService", IFoodTypeService.class);
	// ��ת����Դ
	private Object uri;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// ���ñ���
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;charset=UTF-8");
		// ��ȡ������ʽ
		String method = request.getParameter("method");
		// �ж�
		if ("addFoodType".equals(method)) {
			// ��Ӳ�ϵ
			addFoodtype(request, response);

		} else if ("listType".equals(method)) {
			// ҳ��չʾ
			listType(request, response);

		} else if ("viewUpdate".equals(method)) {
			// �������ҳ��
			viewUpdate(request, response);
		} else if ("delete".equals(method)) {
			// ɾ��
			delete(request, response);
		} else if ("update".equals(method)) {
			// ����
			update(request, response);
		} else if ("search".equals(method)) {
			// ����
			search(request, response);
		}

	}

	private void search(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			// 1.��ȡ�������ݷ�װ
			String keyword = request.getParameter("keyword");
			// 2.����service��ҵ���߼���ѯָ���ĵ����
			List<FoodType> foodTypeList = foodTypeService.getAllFoodTypes(keyword);
			// 3.���浽�������
			request.setAttribute("foodTypeList", foodTypeList);
			// 4.��ת
			uri = request.getRequestDispatcher("/sys/type/foodType_list.jsp");
		} catch (Exception e) {
			e.printStackTrace();
			uri = "/error/error.jsp";
		}
		goTo(request, response, uri);
	}

	private void update(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			// 1����ȡ�������ݷ�װ
			String foodTypeName = request.getParameter("foodTypeName");
			String id = request.getParameter("id");
			// ��װ�Ķ���
			FoodType foodType = new FoodType();
			foodType.setFoodTypeName(foodTypeName);
			foodType.setId(Integer.parseInt(id));

			// 2.����service��ҵ���߼�
			foodTypeService.updateFoodType(foodType);
			// 3.��ת
			uri="/FoodTypeServlet?method=listType";
		} catch (Exception e) {
			e.printStackTrace();
			uri = "/error/error.jsp";
		}
		goTo(request, response, uri);
	}

	private void delete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			// 1.��ȡ�������ݷ�װ
			String id = request.getParameter("id");
			// 2.����servcie��ҵ���߼�
			foodTypeService.deleteFoodType(Integer.parseInt(id));
			// 3.��ת
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
			// 1.��ȡ�������ݷ�װ
			String id = request.getParameter("id");
			// 2.����ID��ѯ����
			FoodType foodType = foodTypeService.queryFoodType(Integer.parseInt(id));
			// �������װ���������
			request.setAttribute("foodType", foodType);
			// 3.��ת
			uri = request.getRequestDispatcher("/sys/type/foodType_update.jsp");
		} catch (Exception e) {
			e.printStackTrace();
			uri = "/error/error.jsp";
		}
		goTo(request, response, uri);
	}

	private void listType(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			// 1.����service��ҵ���߼���ѯ���е����
			List<FoodType> foodTypeList = foodTypeService.getAllFoodTypes();
			// 2.���浽�������
			request.setAttribute("foodTypeList", foodTypeList);
			// 3.��ת
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
			// 1.��ȡ�������ݷ�װ
			String foodTypeName = request.getParameter("foodTypeName");
			// ��װ�Ķ���
			FoodType foodType = new FoodType();
			// ��װ����
			foodType.setFoodTypeName(foodTypeName);

			// 2.����service��ҵ���߼�
			foodTypeService.addFoodType(foodType);
			// 3.ת����Դ
			uri = request.getRequestDispatcher("FoodTypeServlet?method=listType");
		} catch (Exception e) {
			e.printStackTrace();
			uri = "/error/error.jsp";
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
