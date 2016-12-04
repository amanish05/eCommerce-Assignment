<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css"
	integrity="sha384-BVYiiSIFeK1dGmJRAkycuHAHRg32OmUcww7on3RYdg4Va+PmSTsz/K68vbdEjh4u"
	crossorigin="anonymous">
<title>Add Inventory</title>
</head>
<body>
	<div class="container">
		<div class="page-header">
			<h3>Add Inventory</h3>
		</div>
		<c:if test="${empty inventory}">
			<form action="Inventory" method="post" class="form-horizontal">
				<div class="form-group">

					<label for="name" class="col-sm-2 control-label">Item Name</label>
					<div class="col-sm-10">
						<c:if test="${!NameError}">
							<input type="text" class="form-control" name="name"
								placeholder="Item name Please" />
						</c:if>
						<c:if test="${NameError}">
							<div class="form-group has-error">
								<input type="text" class="form-control" name="name"> <label
									class="control-label" for="name">Error with name</label>
							</div>
						</c:if>
					</div>


					<label for="description" class="col-sm-2 control-label">Description</label>
					<div class="col-sm-10">
						<textarea class="form-control" name="description"
							placeholder="Description" rows="3"></textarea>
					</div>


					<label for="quantity" class="col-sm-2 control-label">Quantity</label>
					<div class="col-sm-10">
						<c:if test="${!QuantityError}">
							<input type="text" class="form-control" name="quantity"
								placeholder="Quantity" />
						</c:if>
						<c:if test="${QuantityError}">
							<div class="form-group has-error">
								<input type="text" class="form-control" name="quantity">
								<label class="control-label" for="quantity">Error with
									Quantity</label>
							</div>
						</c:if>
					</div>


					<label for="price" class="col-sm-2 control-label">Price</label>
					<div class="input-group">
						<div class="input-group-addon">$</div>
						<div class="col-sm-0">
							<c:if test="${!PriceError}">
								<input type="text" class="form-control" name="price"
									placeholder="Price per Unit">
							</c:if>
							<c:if test="${PriceError}">
								<div class="form-group has-error">
									<input type="text" class="form-control" name="price"> <label
										class="control-label" for="price">Error with Price</label>
								</div>
							</c:if>
						</div>
					</div>
					</br>
				</div>

				<button type="submit" class="btn btn-primary">Add New Item</button>

			</form>
		</c:if>

		<c:if test="${not empty inventory}">
			<h2>Update Inventory</h2>
			</br>
			</br>
			<form action="Update" method="post" class="form-horizontal">
				<div class="form-group">

					<label for="name" class="col-sm-2 control-label">Item Name</label>
					<div class="col-sm-10">
						<c:if test="${!NameError}">
							<input type="text" class="form-control" name="name"
								placeholder="Item name Please" value="${inventory.getName()}" />
							</br>
						</c:if>
						<c:if test="${NameError}">
							<div class="form-group has-error">
								<input type="text" class="form-control" name="name"
									placeholder="Item name Please" value="${inventory.getName()}" /></br>
								<label class="control-label" for="name">Error with name</label>
							</div>
						</c:if>

					</div>


					<label for="description" class="col-sm-2 control-label">Description</label>
					<div class="col-sm-10">
						<input type="text" class="form-control" name="description"
							placeholder="Description" value="${inventory.getDescription()}"></input></br>
					</div>


					<label for="quantity" class="col-sm-2 control-label">Quantity</label>
					<div class="col-sm-10">
						<c:if test="${!QuantityError}">
							<input type="text" class="form-control" name="quantity"
								placeholder="Quantity" value="${inventory.getQuantity()}" />
							</br>
						</c:if>
						<c:if test="${QuantityError}">
							<div class="form-group has-error">
								<input type="text" class="form-control" name="quantity"
									placeholder="Quantity" value="${inventory.getQuantity()}" /></br>
								<label class="control-label" for="quantity">Error with
									Quantity</label>
							</div>
						</c:if>

					</div>


					<label for="price" class="col-sm-2 control-label">Price</label>
					<div class="input-group">
						<div class="input-group-addon">$</div>
						<div class="col-sm-0">
							<c:if test="${!PriceError}">
								<input type="text" class="form-control" name="price"
									placeholder="price" value="${inventory.getPrice()}" />
							</c:if>
							<c:if test="${PriceError}">
								<div class="form-group has-error">
									<input type="text" class="form-control" name="price"
										placeholder="price" value="${inventory.getPrice()}" /><label
										class="control-label" for="price">Error with Price</label>
								</div>
							</c:if>

						</div>
					</div>
					</br> <input type="hidden" name="id" value="${inventory.id}" />
				</div>

				<button type="submit" class="btn btn-primary btn-lg">Update
					Item</button>

			</form>
		</c:if>
		<div class="col-md-20">
			<a class="btn btn-primary btn-sm" href="Inventory">Back to
				Inventory Manager</a>
		</div>
	</div>
</body>
</html>