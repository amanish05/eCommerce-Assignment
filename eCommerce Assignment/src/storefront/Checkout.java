package storefront;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Collection;
import java.util.concurrent.atomic.AtomicLong;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dbConnection.DBConnection;

@WebServlet("/Checkout")
public class Checkout extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static final AtomicLong sequence = new AtomicLong(
			System.currentTimeMillis() / 1000);

	public Checkout() {
		super();
	}

	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {

		request.getRequestDispatcher("/WEB-INF/Storefront/Checkout.jsp")
				.forward(request, response);
	}

	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		boolean hasError = false;
		String inputName = request.getParameter("inputName");
		String inputEmail = request.getParameter("inputEmail");

		request.setAttribute("inputName", inputName);
		request.setAttribute("inputEmail", inputEmail);

		if (inputName == null || inputName.trim().length() == 0
				|| !inputName.trim().contains(" ")) {
			hasError = true;
			request.setAttribute("nameError", true);
		}
		if (inputEmail == null || inputEmail.trim().length() == 0) {
			hasError = true;
			request.setAttribute("emailError", true);
		}
		if (hasError) {
			doGet(request, response);
			return;
		} else {
			String orderNumber = String.valueOf(uniqueNumber());
			ShoppingCart shoppingCart = (ShoppingCart) request.getSession()
					.getAttribute("cart");
			Collection<ShoppingCartItem> shoppingCartItems = shoppingCart
					.getItemList();
			Connection connection = null;

			try {
				connection = DBConnection.getConnection();

				if (isValidOrder(shoppingCartItems, connection)) {
					updateInventory(shoppingCartItems, connection);
					recordTransaction(shoppingCartItems, connection, inputName,
							inputEmail, orderNumber);
					request.getSession().removeAttribute("cart");
					request.setAttribute("orderCompleted", true);
					request.setAttribute("orderNumber", orderNumber);
				} else {
					request.setAttribute("invalidOrder", true);
				}

			} catch (Exception e) {
				System.out.println("Exception while Archiving state. Error: "
						+ e.getMessage());
			} finally {
				DBConnection.closeConnection(connection);
			}
			doGet(request, response);
			return;

		}
	}

	private boolean isValidOrder(Collection<ShoppingCartItem> shoppingCartItems,
			Connection connection) throws SQLException {
		String sql = "SELECT AVAILABLE_QUANTITY FROM inventory WHERE INVENTORY_PGUID = ?";
		PreparedStatement pstmt = connection.prepareStatement(sql);
		for (ShoppingCartItem shoppingCartItem : shoppingCartItems) {
			pstmt.setString(1, shoppingCartItem.getItem().getId());
			ResultSet rs = pstmt.executeQuery();
			rs.next();
			if (rs.getInt("AVAILABLE_QUANTITY") < shoppingCartItem
					.getQuantityOrdered())
				return false;
		}

		return true;
	}

	private void updateInventory(Collection<ShoppingCartItem> shoppingCartItems,
			Connection connection) throws SQLException {
		String sql = "UPDATE inventory SET AVAILABLE_QUANTITY = AVAILABLE_QUANTITY - ? WHERE INVENTORY_PGUID = ?";
		PreparedStatement pstmt = connection.prepareStatement(sql);
		for (ShoppingCartItem shoppingCartItem : shoppingCartItems) {
			pstmt.setString(1,
					String.valueOf(shoppingCartItem.getQuantityOrdered()));
			pstmt.setString(2, shoppingCartItem.getItem().getId());
			pstmt.addBatch();
		}
		pstmt.executeBatch();
	}

	private void recordTransaction(
			Collection<ShoppingCartItem> shoppingCartItems,
			Connection connection, String inputName, String inputEmail,
			String orderNumber) throws SQLException {

		String sql = "INSERT INTO inventory_transactions (ORDER_NUMBER, CUSTOMER_NAME, CUSTOMER_EMAIL, INVENTORY_PGUID, INVENTORY_NAME, INVENTORY_DESC, INVENTORY_PRICE_PER_UNIT, ORDERED_QUANTITY, AMOUNT) VALUES(?, ?, ?, ?, ?, ?, ?, ?, ?)";
		PreparedStatement pstmt = connection.prepareStatement(sql);
		for (ShoppingCartItem shoppingCartItem : shoppingCartItems) {
			pstmt.setString(1, orderNumber);
			pstmt.setString(2, inputName);
			pstmt.setString(3, inputEmail);
			pstmt.setString(4, shoppingCartItem.getItem().getId());
			pstmt.setString(5, shoppingCartItem.getItem().getName());
			pstmt.setString(6, shoppingCartItem.getItem().getDescription());
			pstmt.setString(7,
					String.valueOf(shoppingCartItem.getItem().getPrice()));
			pstmt.setString(8,
					String.valueOf(shoppingCartItem.getQuantityOrdered()));
			pstmt.setString(9,
					String.valueOf(shoppingCartItem.getTotalPrice()));
			pstmt.addBatch();
		}
		pstmt.executeBatch();
	}

	private static long uniqueNumber() {
		return sequence.incrementAndGet();
	}
}
