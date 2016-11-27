package storefront;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import eCommerce.InventoryModel;

/**
 * Servlet implementation class StoreController
 */
@WebServlet("/Details")
public class ItemDetailController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public ItemDetailController() {
		super();
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		try {
			displayItemDetails(request, response);
		} catch (SQLException e) {
			throw new ServletException(e);
		}
	}

	private void displayItemDetails(HttpServletRequest request, HttpServletResponse response)
			throws SQLException, ServletException, IOException {
		String id = request.getParameter("id");
		if (id == null) {
			request.setAttribute("error", "No item id to display item details!");
			request.getRequestDispatcher("/Store").forward(request, response);
		} else {
			InventoryDAO inventoryDAO = new InventoryDAO();
			InventoryModel matchingItem = inventoryDAO.getInventoryItem(id);
			if (matchingItem == null) {
				request.setAttribute("error", "No matching item with id " + id + " found in inventory!");
				request.getRequestDispatcher("/Store").forward(request, response);
			} else {
				request.setAttribute("item", matchingItem);
				String currentPageString = request.getParameter("page");
				if (currentPageString != null) {
					int currentPage = Integer.parseInt(currentPageString);
					request.setAttribute("page", currentPage);
				}
				request.getRequestDispatcher("/WEB-INF/Storefront/ItemDetail.jsp").forward(request, response);
			}
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}
}
