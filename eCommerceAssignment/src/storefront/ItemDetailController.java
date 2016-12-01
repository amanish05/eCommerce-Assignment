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
			String action = request.getParameter("action");
			if ("addToCart".equals(action)) {
				performAddToShoppingCart(request, response);
			}
			displayItemDetails(request, response);
		} catch (SQLException e) {
			throw new ServletException(e);
		}
	}

	private void performAddToShoppingCart(HttpServletRequest request, HttpServletResponse response)
			throws SQLException, ServletException, IOException {
		String id = request.getParameter("id");
		InventoryDAO inventoryDAO = new InventoryDAO();
		InventoryModel matchingItem = inventoryDAO.getInventoryItem(id);
		if (matchingItem == null) {
			request.setAttribute("error", "No matching item with id " + id + " found in inventory!");
		} else if (matchingItem.getQuantity() <= 0) {
			request.setAttribute("error", "Item " + matchingItem.getName() + " is no longer available!");
		} else {
			boolean added = addItemToCart(request, matchingItem);
			if (!added) {
				request.setAttribute("error", "Item " + matchingItem.getName() + " is no longer available!");
			}
		}
	}

	private boolean addItemToCart(HttpServletRequest request, InventoryModel matchingItem) {
		ShoppingCart shoppingCart = (ShoppingCart) request.getSession().getAttribute("cart");
		if (shoppingCart == null) {
			shoppingCart = new ShoppingCart();
			request.getSession().setAttribute("cart", shoppingCart);
		}
		return shoppingCart.add(matchingItem);
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
