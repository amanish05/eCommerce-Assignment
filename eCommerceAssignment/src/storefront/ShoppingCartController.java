package storefront;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class StoreController
 */
@WebServlet("/ShoppingCart")
public class ShoppingCartController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public ShoppingCartController() {
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
			if ("remove".equals(action)) {
				reduceItemQuantityInCart(request, response);
			} else if ("delete".equals(action)) {
				deleteItemInCart(request, response);
			}
			displayItemDetails(request, response);
		} catch (SQLException e) {
			throw new ServletException(e);
		}
	}

	private void reduceItemQuantityInCart(HttpServletRequest request, HttpServletResponse response) {
		ShoppingCart shoppingCart = (ShoppingCart) request.getSession().getAttribute("cart");
		if (shoppingCart != null) {
			String id = request.getParameter("id");
			ShoppingCartItem cartItem = shoppingCart.getShoppingCartItem(id);
			if (cartItem == null) {
				request.setAttribute("error", "No matching item found in cart for item id " + id);
			} else {
				shoppingCart.reduceItemQuantity(cartItem);
			}
		}
	}

	private void deleteItemInCart(HttpServletRequest request, HttpServletResponse response) {
		ShoppingCart shoppingCart = (ShoppingCart) request.getSession().getAttribute("cart");
		if (shoppingCart != null) {
			String id = request.getParameter("id");
			ShoppingCartItem cartItem = shoppingCart.getShoppingCartItem(id);
			if (cartItem == null) {
				request.setAttribute("error", "No matching item found in cart for item id " + id);
			} else {
				shoppingCart.deleteItem(cartItem);
			}
		}
	}

	private void displayItemDetails(HttpServletRequest request, HttpServletResponse response)
			throws SQLException, ServletException, IOException {
		String currentPageString = request.getParameter("page");
		if (currentPageString != null) {
			int currentPage = Integer.parseInt(currentPageString);
			request.setAttribute("page", currentPage);
		}
		request.getRequestDispatcher("/WEB-INF/Storefront/ShoppingCart.jsp").forward(request, response);
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
