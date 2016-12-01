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
					<h2>Storefront:&nbsp;Item&nbsp;Details</h2>
				</div>
				<div class="col-md-2 col-md-offset-6"">
					<c:url value="ShoppingCart" var="url">
						<c:param name="page" value="${page}" />
					</c:url>
					<a href="${url}"><img src="Images/cart.png"
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

		<c:if test="${not empty item}">
			<div class="panel panel-primary">
				<div class="panel-heading">Item details for ${item.name}</div>
				<div class="panel-body">
					<table class="table table-hover table-striped table-bordered">
						<tbody>
							<tr>
								<td>Item Id</td>
								<td>${item.id}</td>
							</tr>
							<tr>
								<td>Item Name</td>
								<td>${item.name}</td>
							</tr>
							<tr>
								<td>Item Description</td>
								<td>${item.description}</td>
							</tr>
							<tr>
								<td>Item Quantity In Hand</td>
								<td>${item.quantity}</td>
							</tr>
							<tr>
								<td>Item Price (in $)</td>
								<td>${item.price}</td>
							</tr>
						</tbody>
					</table>
				</div>
			</div>
			<div class="row">

				<div class="col-md-2 col-md-offset-8">
					<c:url value="Store" var="url">
						<c:param name="action" value="itemList" />
						<c:param name="page" value="${page}" />
					</c:url>
					<a class="btn btn-primary" role="button" href="${url}">Back to item list</a>
				</div>
				<div class="col-md-2">
					<c:url value="Details" var="url">
						<c:param name="action" value="addToCart" />
						<c:param name="id">${item.id}</c:param>
						<c:param name="page" value="${page}" />
					</c:url>
					<a class="btn btn-primary" role="button" href="${url}">Add to
						cart</a>
				</div>
			</div>
		</c:if>
	</div>
</body>
</html>