<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Object Transformer's</title>
<head>
<title>Store Inventory</title>
<link rel='stylesheet'
	href='http://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css'>
</head>
<body>
	<form action='Inventory' method='get' class='form-inline'>
		<input type='search' class='form-control' name='query' placeholder='Search' />
		<button type='submit' class='btn btn-info'>Go on</button>
		<a class='btn btn-warning' href='Logout'>Logout </a>
	</form>
	
	<table class="table table-bordered table-striped table-hover">
		<c:choose>
			<c:when test="${empty todos}">
				<div class="jumbotron">
					<h1>Uh-Oh! <small>There are no item to display.</small>	</h1>
				</div>
			</c:when>
			<c:when test="${not empty todos}">
				<tr>
					<th>Name</th>					
					<th>Price</th>					
					<th>Required</th>
		</tr>
				
			</c:when>			
		</c:choose>		
		
		<tr>
			<th>Name</th>
			<th>Description</th>
			<th>Quantity</th>
			<th>Price</th>
			<th>Action</th>
			<th>Required</th>
		</tr>
		
	</table>

</body>
</html>