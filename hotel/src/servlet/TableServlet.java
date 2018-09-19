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
	//// ���õĲ�����service
	private IDiningTableService diningTbaleService =BeanFactory.getInstance("diningTbaleService", IDiningTableService.class);
	// ��ת ��Դ
	private String uri;
	//��ʼ��tableServlet---->��ȡ������
	@Override
	public void init(ServletConfig config) throws ServletException {
		super.init(config);
		//��ȡ����xinxi
		List<DiningTable> tableList = diningTbaleService.getAllTables();
		//���浽����
		config.getServletContext().setAttribute("tableList", tableList);
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// ���ñ���
		response.setContentType("text/html;charset=UTF-8");
		request.setCharacterEncoding("UTF-8");

		// ��ȡ����ʽ
		String method = request.getParameter("method");
		// �ж�

		if ("addTable".equals(method)) {
			// ��Ӳ���
			addTable(request, response);
		} else if ("searchTable".equals(method)) {
			// ��������
			searchTable(request, response);
		} else if ("debookTable".equals(method)) {
			// �˶�����
			debookTable(request, response);
		} else if ("deleteTable".equals(method)) {
			// ɾ������
			deleteTable(request, response);
		} else if ("listTable".equals(method)) {
			// ��ʾ���в����б�
			listTable(request, response);
		} else if ("predeterTable".equals(method)) {
			// Ԥ������
			predeterTable(request, response);
		}
	}

	private void predeterTable(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		try {
			// ��ȡ�������ݷ�װ
			String id = request.getParameter("id");
			// ����ҵ���߼�
			diningTbaleService.predeterTable(Integer.parseInt(id));
			// ��ӳɹ��ص���ҳ
			listTable(request, response);
		} catch (Exception e) {
			e.printStackTrace();
			uri = "/error/error.jsp";
			request.getRequestDispatcher(uri).forward(request, response);
		}

	}

	private void listTable(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// ����service����
		try {
			List<DiningTable> tableList = diningTbaleService.getAllTables();
			// ��tablist���浽session��
			request.setAttribute("tableList", tableList);
			// �ɹ�ת����������ҳ
			uri = "/sys/table/table_list.jsp";
		} catch (Exception e) {
			e.printStackTrace();
			// ʧ��ȥ��ʧ��ҳ��
			uri = "/error/error.jsp";
		}
		request.getRequestDispatcher(uri).forward(request, response);
	}

	private void deleteTable(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		try {
			// ��ȡ�����ɾ������ID
			String id = request.getParameter("id");
			// ����service��ɾ������
			diningTbaleService.deleteTable(Integer.parseInt(id));
			// ת��
			// ɾ���ɹ�������ʾ����
			listTable(request, response);

		} catch (Exception e) {
			e.printStackTrace();
			// ɾ��ʧ��ȥ��ʧ��ҳ��
			uri = "/error/error.jsp";
			request.getRequestDispatcher(uri).forward(request, response);
		}
	}

	private void debookTable(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		try {
			// ��ȡ������˶�ID
			String id = request.getParameter("id");
			// ����service���˶�����
			diningTbaleService.debook(Integer.parseInt(id));
			// �˶��ɹ����ص�������ҳ
			listTable(request, response);
		} catch (Exception e) {
			e.printStackTrace();
			// �˶�ʧ��ȥ��ʧ��ҳ��
			uri = "/error/error.jsp";
			request.getRequestDispatcher(uri).forward(request, response);
		}
	}

	private void searchTable(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		try {
			// ��ȡ��װ����
			String keyword = request.getParameter("keyword");
			// ����service�ķ���
			List<DiningTable> tableList = diningTbaleService.queryTable(keyword);
			// ���浽�������
			request.setAttribute("tableList", tableList);
			// �ɹ�ת����������ҳ
			uri = "/sys/table/table_list.jsp";
		} catch (Exception e) {
			e.printStackTrace();
			// ʧ��ȥ��ʧ��ҳ��
			uri = "/error/error.jsp";
		}
		request.getRequestDispatcher(uri).forward(request, response);
	}

	private void addTable(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		try {
			// ��ȡ�������
			String newTableName = request.getParameter("newTableName");
			// ��װ����
			DiningTable table = new DiningTable();
			table.setTableName(newTableName);
			table.setTableStatus(TableStatus.free.ordinal());
			table.setScheduleDate(null);
			// ������ӵķ���
			diningTbaleService.addTable(table);
			// ��ӳɹ��ص���ҳ
			listTable(request, response);
		} catch (Exception e) {
			e.printStackTrace();
			// ʧ��ȥ��ʧ��ҳ��
			uri = "/error/error.jsp";
			request.getRequestDispatcher(uri).forward(request, response);
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		doGet(request, response);
	}

}
