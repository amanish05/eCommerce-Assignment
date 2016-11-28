<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<link rel="stylesheet"	href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css" integrity="sha384-BVYiiSIFeK1dGmJRAkycuHAHRg32OmUcww7on3RYdg4Va+PmSTsz/K68vbdEjh4u" crossorigin="anonymous">
<title>Add Inventory</title>
</head>
<body>
	
	<div class="col-md-10">
		<h2>Add Inventory</h2>
	</div>
	<c:if test="${empty inventory}">		
		
		<form action="Inventory" method="post" class="form-horizontal">	
			<div class="form-group">
				
				<label for="name" class="col-sm-2 control-label">Item Name</label>
				<div class="col-sm-10">
					<input type="text" class="form-control" name="name" placeholder="Item name Please" />
				</div>
				
				
				<label for="description"  class="col-sm-2 control-label">Description</label>
				<div class="col-sm-10">
					<textarea class="form-control" name="description" placeholder="Description" rows="3"></textarea>
				</div>
				
				
				<label for="quantity" class="col-sm-2 control-label">Quantity</label>
				<div class="col-sm-10">
					<input type="text" class="form-control" name="quantity" placeholder="Quantity" />
				</div>
				
				
				<label for="price" class="col-sm-2 control-label">Price</label>
				<div class="input-group">										
					<div class="input-group-addon" >$</div>
					<div class="col-sm-0">	
						<input type="text" class="form-control" name="price" placeholder="Price per Unit" >
					</div>			
				</div></br>				
			</div>
			<div class="col-sm-10">
				<button type="submit" class="btn btn-primary">Add New Item</button>
			</div>
		</form>					
	</c:if>
	
	<c:if test="${not empty inventory}">
		<form action="Update" method="post" class="form-horizontal">	
			<div class="form-group">
				
				<label for="name" class="col-sm-2 control-label">Item Name</label>
				<div class="col-sm-10">
					<input type="text" class="form-control" name="name" placeholder="Item name Please" value= "${inventory.getName()}"/></br>
				</div>
				
				
				<label for="description" class="col-sm-2 control-label">Description</label>
				<div class="col-sm-10">
					<input type="text" class="form-control" name="description" placeholder="Description" value= "${inventory.getDescription()}"></input></br>
				</div>
				
				
				<label for="quantity" class="col-sm-2 control-label">Quantity</label>
				<div class="col-sm-10">
					<input type="text" class="form-control" name="quantity" placeholder="Quantity" value= "${inventory.getQuantity()}"/></br>
				</div>
				
				
				<label for="price" class="col-sm-2 control-label">Price</label>
				<div class="input-group">					
					<div class="input-group-addon">$</div>
					<div class="col-sm-0">
						<input type="text" class="form-control" name="price" placeholder="price" value= "${inventory.getPrice()}"/>
					</div>					
				</div></br>
				<input type="hidden" name="id" value= "${inventory.id}"/>
			</div>
			<div class="col-sm-10">
				<button type="submit" class="btn btn-primary btn-lg">Update Item</button>
			</div>	
		</form>					
	</c:if>
</body>
</html>