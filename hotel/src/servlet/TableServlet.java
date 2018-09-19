package servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import entity.DiningTable;
import entity.TableStatus;
import factory.BeanFactory;
import service.IDiningTableService;

@WebServlet("/TableServlet")
public class TableServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	//// 调用的餐桌的service
	private IDiningTableService diningTbaleService =BeanFactory.getInstance("diningTbaleService", IDiningTableService.class);
	// 跳转 资源
	private String uri;
	//初始化tableServlet---->获取餐桌名
	@Override
	public void init(ServletConfig config) throws ServletException {
		super.init(config);
		//获取餐桌xinxi
		List<DiningTable> tableList = diningTbaleService.getAllTables();
		//保存到域中
		config.getServletContext().setAttribute("tableList", tableList);
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// 设置编码
		response.setContentType("text/html;charset=UTF-8");
		request.setCharacterEncoding("UTF-8");

		// 获取请求方式
		String method = request.getParameter("method");
		// 判断

		if ("addTable".equals(method)) {
			// 添加餐桌
			addTable(request, response);
		} else if ("searchTable".equals(method)) {
			// 搜索餐桌
			searchTable(request, response);
		} else if ("debookTable".equals(method)) {
			// 退订餐桌
			debookTable(request, response);
		} else if ("deleteTable".equals(method)) {
			// 删除餐桌
			deleteTable(request, response);
		} else if ("listTable".equals(method)) {
			// 显示所有餐桌列表
			listTable(request, response);
		} else if ("predeterTable".equals(method)) {
			// 预定餐桌
			predeterTable(request, response);
		}
	}

	private void predeterTable(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		try {
			// 获取请求数据封装
			String id = request.getParameter("id");
			// 调用业务逻辑
			diningTbaleService.predeterTable(Integer.parseInt(id));
			// 添加成功回到主页
			listTable(request, response);
		} catch (Exception e) {
			e.printStackTrace();
			uri = "/error/error.jsp";
			request.getRequestDispatcher(uri).forward(request, response);
		}

	}

	private void listTable(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// 调用service方法
		try {
			List<DiningTable> tableList = diningTbaleService.getAllTables();
			// 将tablist保存到session中
			request.setAttribute("tableList", tableList);
			// 成功转发到餐桌主页
			uri = "/sys/table/table_list.jsp";
		} catch (Exception e) {
			e.printStackTrace();
			// 失败去到失败页面
			uri = "/error/error.jsp";
		}
		request.getRequestDispatcher(uri).forward(request, response);
	}

	private void deleteTable(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		try {
			// 获取传入的删除餐桌ID
			String id = request.getParameter("id");
			// 调用service的删除方法
			diningTbaleService.deleteTable(Integer.parseInt(id));
			// 转发
			// 删除成功调用显示方法
			listTable(request, response);

		} catch (Exception e) {
			e.printStackTrace();
			// 删除失败去到失败页面
			uri = "/error/error.jsp";
			request.getRequestDispatcher(uri).forward(request, response);
		}
	}

	private void debookTable(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		try {
			// 获取传入的退订ID
			String id = request.getParameter("id");
			// 调用service的退订方法
			diningTbaleService.debook(Integer.parseInt(id));
			// 退订成功返回到餐桌主页
			listTable(request, response);
		} catch (Exception e) {
			e.printStackTrace();
			// 退订失败去到失败页面
			uri = "/error/error.jsp";
			request.getRequestDispatcher(uri).forward(request, response);
		}
	}

	private void searchTable(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		try {
			// 获取封装数据
			String keyword = request.getParameter("keyword");
			// 调用service的方法
			List<DiningTable> tableList = diningTbaleService.queryTable(keyword);
			// 保存到域对象中
			request.setAttribute("tableList", tableList);
			// 成功转发到餐桌主页
			uri = "/sys/table/table_list.jsp";
		} catch (Exception e) {
			e.printStackTrace();
			// 失败去到失败页面
			uri = "/error/error.jsp";
		}
		request.getRequestDispatcher(uri).forward(request, response);
	}

	private void addTable(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		try {
			// 获取请求参数
			String newTableName = request.getParameter("newTableName");
			// 封装对象
			DiningTable table = new DiningTable();
			table.setTableName(newTableName);
			table.setTableStatus(TableStatus.free.ordinal());
			table.setScheduleDate(null);
			// 调用添加的方法
			diningTbaleService.addTable(table);
			// 添加成功回到主页
			listTable(request, response);
		} catch (Exception e) {
			e.printStackTrace();
			// 失败去到失败页面
			uri = "/error/error.jsp";
			request.getRequestDispatcher(uri).forward(request, response);
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		doGet(request, response);
	}

}
