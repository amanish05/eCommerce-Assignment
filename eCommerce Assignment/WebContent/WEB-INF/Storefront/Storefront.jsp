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
				<div class="col-md-1">
					<h2>Storefront</h2>
				</div>				
				<div class="col-md-2 col-md-offset-9"">
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
			

		<c:if test="${empty inventoryList}">
			<div class="jumbotron">
				<h3>The inventory is empty!</h3>
			</div>
		</c:if>

		<c:if test="${not empty inventoryList}">
			<table class="table table-hover table-striped table-bordered">
				<thead>
					<tr>
						<th style="text-align: center;">Item Name</th>
						<th style="text-align: center;">Item Description</th>
						<th style="text-align: center;">Price</th>
						<th style="text-align: center;">Item Detail</th>
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
							<td style="text-align: center;"><c:url value="Details"
									var="url">
									<c:param name="id" value="${inventoryList[index].id}" />
									<c:param name="page" value="${page}" />
								</c:url> <a href="${url}"><img src="Images/viewDetail2.png"
									style="vertical-align: middle" /></a></td>
							<td style="text-align: center;"><c:url value="Store"
									var="url">
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
		<div class="col-md-20">
			<a class="btn btn-primary btn-lg active" href="Inventory" >Inventory Manager</a>
		</div>
	</div>
	
	
	
</body>
</html>