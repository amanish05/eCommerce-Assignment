<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<head>

<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css"
	integrity="sha384-BVYiiSIFeK1dGmJRAkycuHAHRg32OmUcww7on3RYdg4Va+PmSTsz/K68vbdEjh4u"
	crossorigin="anonymous">

<title>Inventory Management</title>
</head>
<body>
	<div class="container">
		<div class="page-header">
			<h3>Inventory Manager</h3>
		</div>
		<c:if test="${empty inventories}">
			<div class="jumbotron">
				<h1>
					Uh-Oh! <small>There are no items to display.</small>
				</h1>
			</div>
		</c:if>

		<c:if test="${not empty inventories}">
			<div class="panel panel-primary">
				<div class="panel-heading">
					<h2>Inventory Management</h2>
				</div>
				<div class="panel-body">
					<table class="table table-hover table-striped table-bordered">
						<thead>
							<tr>
								<th style="text-align: center;">Item Name</th>
								<th style="text-align: center;">Item Description</th>
								<th style="text-align: center;">Quantity</th>
								<th style="text-align: center;">Unit Price</th>
								<th style="text-align: center;">Action</th>
							</tr>
						</thead>
						<tbody>
							<c:forEach items="${inventories}" var="inventory">
								<tr>
									<td>${inventory.name}</td>
									<td>${inventory.description}</td>
									<td>${inventory.price}</td>
									<td>${inventory.quantity}</td>
									<td><a class="btn btn-primary btn-sm"
										href="Update?id=${inventory.id}">Edit</a> <a
										class="btn btn-danger" href="Remove?id=${inventory.id}">Erase</a></td>
								</tr>
							</c:forEach>
						</tbody>
					</table>
				</div>
			</div>
		</c:if>
		<div class="col-md-20">
			<a class="btn btn-primary btn-sm" href="AddItem">Add New Item</a> <a
				class="btn btn-primary btn-sm" href="Store">Back to store Front</a>
		</div>
	</div>
</body>
</html>