<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<head>
<link rel='stylesheet'
	href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css"
	integrity="sha384-BVYiiSIFeK1dGmJRAkycuHAHRg32OmUcww7on3RYdg4Va+PmSTsz/K68vbdEjh4u"
	crossorigin="anonymous">
<title>History</title>
</head>
</head>
<body>
<body>
	<div class="container">
		<div class="page-header">
			<h3>Shopping History</h3>
		</div>
		<form action='History' method='post' class="form-inline">
			<div class="form-group">
				<label for="email"><strong>Email </strong></label> <input
					type='text' name="email" class="form-control" placeholder='Email' />
			</div>
			<input type='submit' value='Fetch Records' class="btn btn-default" />
			<a class="btn btn-primary btn-sm" href="Store">Back to store
				Front</a>
		</form>

		<c:if test="${empty orderItems}">
			<div class="jumbotron">
				<h1>
					Uh-Oh! <small>There are no items to display.</small>
				</h1>
			</div>
		</c:if>

		<c:if test="${not empty orderItems}">
			<c:forEach items="${orderItems }" var="item">
				<div class="panel-heading">
					<strong>Order: ${item.id} </strong> Date: ${item.createdDate}
				</div>
				<table class="table table-hover table-striped table-bordered">
					<thead>
						<tr>
							<th style="text-align: center;">Item Name</th>
							<th style="text-align: center;">Item Price</th>
							<th style="text-align: center;">Quantity</th>
							<th style="text-align: center;">Total Price</th>
						</tr>
					</thead>
					<tbody>
						<c:forEach items="${item.list}" var="order">
							<tr>
								<td>${order.name}</td>
								<td>${order.price}</td>
								<td>${order.quantity}</td>
								<td>${order.total}</td>
							</tr>
						</c:forEach>
						<tr>
							<td colspan="3" style="text-align: right;"><strong>Total
									Order price</strong></td>
							<td><strong> ${item.orderTotal } </strong></td>
						</tr>
					</tbody>
				</table>
			</c:forEach>
		</c:if>
	</div>
</body>
</html>