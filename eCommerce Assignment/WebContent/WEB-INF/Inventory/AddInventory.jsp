<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<link rel="stylesheet"	href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css"integrity="sha384-BVYiiSIFeK1dGmJRAkycuHAHRg32OmUcww7on3RYdg4Va+PmSTsz/K68vbdEjh4u" crossorigin="anonymous">
<title>Add Inventory</title>
</head>
<body>	
	<c:if test="${empty inventory}">
		<form class="form-inline" action="Inventory" method="post">	
			<div class="form-group">
				<c:out value="Item Name: " escapeXml="true" /><input type="text" class="form-control" name="name" placeholder="Item name Please" /></br>
				<c:out value="Description: " escapeXml="true" /><textarea class="form-control" name="description" placeholder="Description" ></textarea></br>
				<c:out value="Quantity: " escapeXml="true" /><input type="text" class="form-control" name="quantity" placeholder="Quantity" /></br>
				<div class="input-group"> <div class="input-group-addon">$</div>
				<c:out value="Price: " escapeXml="true" /><input type="text" class="form-control" name="price" placeholder="Price per Unit" /></div></br>				
			</div>
			<button type="submit" class="btn btn-default">Add New Item</button>		
		</form>					
	</c:if>
	<c:if test="${not empty inventory}">
		<form class="form-inline" action="Update" method="post">	
			<div class="form-group">
				<c:out value="Item Name: " escapeXml="true" /><input type="text" class="form-control" name="name" placeholder="Item name Please" value= "${inventory.getName()}"/></br>
				<c:out value="Description: " escapeXml="true" /><input type="text" class="form-control" name="description" placeholder="Description" value= "${inventory.getDescription()}"></input></br>
				<c:out value="Quantity: " escapeXml="true" /><input type="text" class="form-control" name="quantity" placeholder="Quantity" value= "${inventory.getQuantity()}"/></br>
				<div class="input-group">
				<div class="input-group-addon">$</div>
					<c:out value="Price: " escapeXml="true" /><input type="text" class="form-control" name="price" placeholder="price" value= "${inventory.getPrice()}"/>
				</div></br>
				<input type="hidden" name="id" value= "${inventory.id}"/>
			</div>
			<button type="submit" class="btn btn-default">Update Item</button>		
		</form>					
	</c:if>
</body>
</html>