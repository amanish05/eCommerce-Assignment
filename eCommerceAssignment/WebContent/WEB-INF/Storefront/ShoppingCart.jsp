<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<!doctype html>
<html lang="en">
<head>
<title>Object Transformer's eCommerce App</title>
<!-- Latest compiled and minified CSS -->
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
</head>
<body>
	<div class="container">
		<c:if test="${not empty error}">
			<div class="alert alert-danger alert-dismissible" role="alert">
				<button type="button" class="close" data-dismiss="alert"
					aria-label="Close">
					<span aria-hidden="true">&times;</span>
				</button>
				<strong>${error}</strong>
			</div>
		</c:if>

		<!-- Page Header -->
		<div class="page-header">
			<div class="row">
				<div class="col-md-4">
					<h2>Storefront:&nbsp;Shopping&nbsp;Cart</h2>
				</div>
				<div class="col-md-2 col-md-offset-6"">
					<a href="#"><img src="Images/cart.png"
						style="vertical-align: middle" /></a>
					<c:choose>
						<c:when test="${not empty sessionScope.cart}">
							<span style="">${sessionScope.cart.itemCount} Item(s)</span>
						</c:when>
						<c:otherwise>
							<span style="">0 Item(s)</span>
						</c:otherwise>
					</c:choose>
				</div>
			</div>
		</div>

		<c:if
			test="${empty sessionScope.cart or fn:length(sessionScope.cart.itemList) eq 0}">
			<div class="jumbotron">
				<h3>Shopping cart is empty!</h3>
			</div>
		</c:if>

		<c:if
			test="${not empty sessionScope.cart and fn:length(sessionScope.cart.itemList) gt 0}">
			<div class="panel panel-primary">
				<div class="panel-heading">Items In Cart</div>
				<div class="panel-body">
					<table class="table table-hover table-striped table-bordered">
						<thead>
							<tr>
								<th style="text-align: center;">Item Id</th>
								<th style="text-align: center;">Item Name</th>
								<th style="text-align: center;">Item Description</th>
								<th style="text-align: center;">Quantity Ordered</th>
								<th style="text-align: center;">Unit Price</th>
								<th style="text-align: center;">Total Price</th>
								<th style="text-align: center;">Reduce Quantity</th>
								<th style="text-align: center;">Delete Item</th>
							</tr>
						</thead>
						<tbody>
							<c:forEach items="${sessionScope.cart.itemList}" var="cartItem">
								<tr>
									<td>${cartItem.item.id}</td>
									<td>${cartItem.item.name}</td>
									<td>${cartItem.item.description}</td>
									<td>${cartItem.quantityOrdered}</td>
									<td>${cartItem.item.price}</td>
									<td>${cartItem.totalPrice}</td>
									<td style="text-align: center;"><c:url
											value="ShoppingCart" var="url">
											<c:param name="id" value="${cartItem.item.id}" />
											<c:param name="action" value="remove" />
											<c:param name="page" value="${page}" />
										</c:url> <a href="${url}"><img src="Images/remove.png"
											style="vertical-align: middle" /></a></td>
									<td style="text-align: center;"><c:url
											value="ShoppingCart" var="url">
											<c:param name="id" value="${cartItem.item.id}" />
											<c:param name="action" value="delete" />
											<c:param name="page" value="${page}" />
										</c:url> <a href="${url}"><img src="Images/deleteAll.png"
											style="vertical-align: middle" /></a></td>
								</tr>
							</c:forEach>
							<tr>
								<td colspan="5" style="text-align: right;"><strong>Total
										price</strong></td>
								<td><strong>${sessionScope.cart.totalPrice}</strong></td>
								<td></td>
								<td></td>
							</tr>
						</tbody>
					</table>
				</div>
			</div>
		</c:if>
		<c:choose>
			<c:when
				test="${empty sessionScope.cart or fn:length(sessionScope.cart.itemList) eq 0}">
				<div class="row">
					<div class="col-md-2 col-md-offset-10">
						<c:url value="Store" var="url">
							<c:param name="action" value="itemList" />
							<c:param name="page" value="${page}" />
						</c:url>
						<a class="btn btn-primary" role="button" href="${url}">Continue
							Shopping</a>
					</div>
				</div>
			</c:when>
			<c:otherwise>
				<div class="row">
					<div class="col-md-2 col-md-offset-8">
						<c:url value="Store" var="url">
							<c:param name="action" value="itemList" />
							<c:param name="page" value="${page}" />
						</c:url>
						<a class="btn btn-primary" role="button" href="${url}">Continue
							Shopping</a>
					</div>
					<div class="col-md-2">
						<c:url value="Checkout" var="url" />
						<a class="btn btn-primary" role="button" href="${url}">Checkout</a>
					</div>
				</div>
			</c:otherwise>
		</c:choose>
	</div>
</body>
</html>