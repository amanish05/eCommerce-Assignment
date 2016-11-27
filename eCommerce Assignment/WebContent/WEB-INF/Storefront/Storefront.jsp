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

		<!-- Page Header -->
		<div class="page-header">
			<div class="row">
				<div class="col-md-1">
					<h2>Storefront</h2>
				</div>
				<div class="col-md-2 col-md-offset-9"">
					<a href="#"><img src="Images/cart.png"
						style="vertical-align: middle" /></a> <span style="">${shoppingCartItems}
						Item(s)</span>
				</div>
			</div>
		</div>

		<c:if test="${empty inventoryList}">
			<div class="jumbotron">
				<h3>The inventory is empty!</h3>
			</div>
		</c:if>

		<c:if test="${not empty inventoryList}">
			<table class="table table-hover table-striped table-bordered">
				<thead>
					<tr>
						<th>Item Name</th>
						<th>Item Description</th>
						<th>Price</th>
						<th></th>
					</tr>
				</thead>
				<tbody>
					<c:forEach begin="${inventoryBeginIndex}"
						end="${inventoryEndIndex-1}" var="index">
						<tr>
							<td>${inventoryList[index].name}</td>
							<td>${inventoryList[index].description}</td>
							<td>${inventoryList[index].price}</td>
							<td><c:url value="Store" var="url">
									<c:param name="id" value="${inventoryList[index].id}" />
									<c:param name="action" value="add" />
									<c:param name="page" value="${page}" />
								</c:url> <a class="btn btn-primary btn-xs" href="${url}">Add to cart</a></td>
						</tr>
					</c:forEach>
				</tbody>
			</table>
			<div class="row">
				<div class="col-md-1 col-md-offset-10">
					<c:choose>
						<c:when test="${page gt 1}">
							<c:url value="Store" var="url">
								<c:param name="action" value="prev" />
								<c:param name="page" value="${page}" />
							</c:url>
							<a class="btn btn-primary" role="button" href="${url}">Prev</a>
						</c:when>
						<c:otherwise>
							<a class="btn btn-primary" role="button" href="#">Prev</a>
						</c:otherwise>
					</c:choose>
				</div>
				<div class="col-md-1">
					<c:choose>
						<c:when test="${page lt totalPages}">
							<c:url value="Store" var="url">
								<c:param name="action" value="next" />
								<c:param name="page" value="${page}" />
							</c:url>
							<a class="btn btn-primary" role="button" href="${url}">Next</a>
						</c:when>
						<c:otherwise>
							<a class="btn btn-primary" role="button" href="#">Next</a>
						</c:otherwise>
					</c:choose>
				</div>
			</div>
		</c:if>
	</div>
</body>
</html>