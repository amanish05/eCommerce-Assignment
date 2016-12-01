package storefront;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import eCommerce.InventoryModel;

/**
 * Servlet implementation class StoreController
 */
@WebServlet("/Store")
public class StoreController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public StoreController() {
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
			if ("add".equals(action)) {
				performAddToShoppingCart(request, response);
			} else if ("next".equals(action)) {
				displayNextSetOfItems(request, response);
			} else if ("prev".equals(action)) {
				displayPrevSetOfItems(request, response);
			} else if ("itemList".equals(action)) {
				displaySameSetOfItems(request, response);
			} else {
				displayFirstSetOfItems(request, response);
			}
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
		displaySameSetOfItems(request, response);
	}

	private boolean addItemToCart(HttpServletRequest request, InventoryModel matchingItem) {
		ShoppingCart shoppingCart = (ShoppingCart) request.getSession().getAttribute("cart");
		if (shoppingCart == null) {
			shoppingCart = new ShoppingCart();
			request.getSession().setAttribute("cart", shoppingCart);
		}
		return shoppingCart.add(matchingItem);
	}

	private void displayFirstSetOfItems(HttpServletRequest request, HttpServletResponse response)
			throws SQLException, ServletException, IOException {
		InventoryDAO inventoryDAO = new InventoryDAO();
		List<InventoryModel> dbInventoryList = inventoryDAO.getAvailableInventoryList();
		ShoppingCart shoppingCart = (ShoppingCart) request.getSession().getAttribute("cart");
		List<InventoryModel> displayableInventoryList = StorefrontUtil.getDisplayableInventoryList(dbInventoryList,
				shoppingCart);
		int totalPages = getTotalPages(displayableInventoryList);
		setInventoryDisplayWindowForFirstPage(request, displayableInventoryList, totalPages);
		request.setAttribute("inventoryList", displayableInventoryList);
		request.getRequestDispatcher("/WEB-INF/Storefront/Storefront.jsp").forward(request, response);
	}

	private void displaySameSetOfItems(HttpServletRequest request, HttpServletResponse response)
			throws SQLException, ServletException, IOException {
		InventoryDAO inventoryDAO = new InventoryDAO();
		List<InventoryModel> dbInventoryList = inventoryDAO.getAvailableInventoryList();
		ShoppingCart shoppingCart = (ShoppingCart) request.getSession().getAttribute("cart");
		List<InventoryModel> displayableInventoryList = StorefrontUtil.getDisplayableInventoryList(dbInventoryList,
				shoppingCart);
		int totalPages = getTotalPages(displayableInventoryList);
		String currentPageString = request.getParameter("page");
		if (currentPageString != null && !currentPageString.isEmpty()) {
			int currentPage = Integer.parseInt(currentPageString);
			if (currentPage > 0 && currentPage < totalPages) {
				setInventorySameDisplayWindow(request, displayableInventoryList, currentPage, totalPages);
			} else {
				setInventorySameDisplayWindow(request, displayableInventoryList, totalPages, totalPages);
			}
		} else {
			setInventoryDisplayWindowForFirstPage(request, displayableInventoryList, totalPages);
		}
		request.setAttribute("inventoryList", displayableInventoryList);
		request.getRequestDispatcher("/WEB-INF/Storefront/Storefront.jsp").forward(request, response);
	}

	private int getTotalPages(List<InventoryModel> displayableInventoryList) {
		int totalPages = (int) Math.ceil(displayableInventoryList.size() / 10.0);
		return totalPages;
	}

	private void displayNextSetOfItems(HttpServletRequest request, HttpServletResponse response)
			throws SQLException, ServletException, IOException {
		InventoryDAO inventoryDAO = new InventoryDAO();
		List<InventoryModel> dbInventoryList = inventoryDAO.getAvailableInventoryList();
		ShoppingCart shoppingCart = (ShoppingCart) request.getSession().getAttribute("cart");
		List<InventoryModel> displayableInventoryList = StorefrontUtil.getDisplayableInventoryList(dbInventoryList,
				shoppingCart);
		int totalPages = getTotalPages(displayableInventoryList);
		String currentPageString = request.getParameter("page");
		if (currentPageString != null && !currentPageString.isEmpty()) {
			int currentPage = Integer.parseInt(currentPageString);
			if (currentPage > 0 && currentPage < totalPages) {
				setInventoryNextDisplayWindow(request, displayableInventoryList, currentPage, totalPages);
			} else {
				setInventoryNextDisplayWindow(request, displayableInventoryList, totalPages - 1, totalPages);
			}
		} else {
			setInventoryDisplayWindowForFirstPage(request, displayableInventoryList, totalPages);
		}
		request.setAttribute("inventoryList", displayableInventoryList);
		request.getRequestDispatcher("/WEB-INF/Storefront/Storefront.jsp").forward(request, response);
	}

	private void displayPrevSetOfItems(HttpServletRequest request, HttpServletResponse response)
			throws SQLException, ServletException, IOException {
		InventoryDAO inventoryDAO = new InventoryDAO();
		List<InventoryModel> dbInventoryList = inventoryDAO.getAvailableInventoryList();
		ShoppingCart shoppingCart = (ShoppingCart) request.getSession().getAttribute("cart");
		List<InventoryModel> displayableInventoryList = StorefrontUtil.getDisplayableInventoryList(dbInventoryList,
				shoppingCart);
		int totalPages = getTotalPages(displayableInventoryList);
		String currentPageString = request.getParameter("page");
		if (currentPageString != null && !currentPageString.isEmpty()) {
			int currentPage = Integer.parseInt(currentPageString);
			if (currentPage > 1 && currentPage <= totalPages) {
				setInventoryPrevDisplayWindow(request, displayableInventoryList, currentPage, totalPages);
			} else {
				setInventoryPrevDisplayWindow(request, displayableInventoryList, totalPages, totalPages);
			}
		} else {
			setInventoryDisplayWindowForFirstPage(request, displayableInventoryList, totalPages);
		}
		request.setAttribute("inventoryList", displayableInventoryList);
		request.getRequestDispatcher("/WEB-INF/Storefront/Storefront.jsp").forward(request, response);
	}

	private void setInventoryNextDisplayWindow(HttpServletRequest request,
			List<InventoryModel> displayableInventoryList, int currentPage, int totalPages) {
		int inventoryBeginIndex = currentPage * 10;
		int inventoryEndIndex = ((currentPage + 1) * 10 <= displayableInventoryList.size()) ? ((currentPage + 1) * 10)
				: displayableInventoryList.size();
		request.setAttribute("inventoryBeginIndex", inventoryBeginIndex);
		request.setAttribute("inventoryEndIndex", inventoryEndIndex);
		request.setAttribute("page", (currentPage + 1));
		request.setAttribute("totalPages", totalPages);
	}

	private void setInventorySameDisplayWindow(HttpServletRequest request,
			List<InventoryModel> displayableInventoryList, int currentPage, int totalPages) {
		int inventoryBeginIndex = (currentPage - 1) * 10;
		int inventoryEndIndex = (currentPage * 10 <= displayableInventoryList.size()) ? (currentPage * 10)
				: displayableInventoryList.size();
		request.setAttribute("inventoryBeginIndex", inventoryBeginIndex);
		request.setAttribute("inventoryEndIndex", inventoryEndIndex);
		request.setAttribute("page", currentPage);
		request.setAttribute("totalPages", totalPages);
	}

	private void setInventoryPrevDisplayWindow(HttpServletRequest request,
			List<InventoryModel> displayableInventoryList, int currentPage, int totalPages) {
		int inventoryBeginIndex = (currentPage - 2) * 10;
		int inventoryEndIndex = ((currentPage - 1) * 10 <= displayableInventoryList.size()) ? ((currentPage - 1) * 10)
				: displayableInventoryList.size();
		request.setAttribute("inventoryBeginIndex", inventoryBeginIndex);
		request.setAttribute("inventoryEndIndex", inventoryEndIndex);
		request.setAttribute("page", (currentPage - 1));
		request.setAttribute("totalPages", totalPages);
	}

	private void setInventoryDisplayWindowForFirstPage(HttpServletRequest request,
			List<InventoryModel> displayableInventoryList, int totalPages) {
		request.setAttribute("page", 1);
		request.setAttribute("inventoryBeginIndex", 0);
		request.setAttribute("inventoryEndIndex",
				(displayableInventoryList.size() > 10 ? 10 : displayableInventoryList.size()));
		request.setAttribute("totalPages", totalPages);
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
