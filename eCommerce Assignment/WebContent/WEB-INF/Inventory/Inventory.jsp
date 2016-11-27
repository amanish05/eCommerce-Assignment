<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<head>

<link rel="stylesheet"	href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css"integrity="sha384-BVYiiSIFeK1dGmJRAkycuHAHRg32OmUcww7on3RYdg4Va+PmSTsz/K68vbdEjh4u" crossorigin="anonymous">

<title>Inventory Management</title>
</head>
<body>

	<h2>Inventory Management</h2>
	
	<table class="table table-bordered table-striped table-hover">
		<c:if test="${empty inventories}">
			<div class="jumbotron">
				<h1>
					Uh-Oh! <small>There are no items to display.</small>
				</h1>
			</div>
		</c:if>
		<c:if test="${not empty inventories}">
			<tr>
				<th>Item Name</th>
				<th>Description</th>
				<th>Price per Unit</th>
				<th>Available Quantity</th>
				<th>Action</th>
			</tr>
			<c:forEach items="${inventories}" var="inventory">
				<tr>
					<td><c:out value="${inventory.name}" escapeXml="true" /></td>
					<td><c:out value="${inventory.description}" escapeXml="true" /></td>
					<td><c:out value="${inventory.price}" escapeXml="true" /></td>
					<td><c:out value="${inventory.quantity}" escapeXml="true" /></td>
					<td><a class="btn btn-primary btn-sm" href="Update?id=${inventory.id}">Edit</a></td>
					<td><a class="btn btn-primary btn-sm" href="Remove?id=${inventory.id}">Erase</a></td>							
				</tr>
			</c:forEach>
		</c:if>
	</table>	
	<a class="btn btn-primary btn-sm" href="AddItem">Add New Item</a>		
</body>
</html>