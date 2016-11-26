<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<head>

<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css"
	integrity="sha384-BVYiiSIFeK1dGmJRAkycuHAHRg32OmUcww7on3RYdg4Va+PmSTsz/K68vbdEjh4u"
	crossorigin="anonymous">

<title>Add Item</title>
</head>
<body>

	<h2>Add New Inventory</h2>

	<form action="AddItem" method="post" class="form-group">
		<input type="text" name="name" class="form-control"
			placeholder="Item name Please" /> <br />

		<textarea rows="5" name="desc" class="form-control"
			placeholder="Item Description here..."> </textarea>
		<br /> <input type="text" name="quantity" class="form-control"
			placeholder="Available Quantity" /> <br />
		<div class="input-group">
			<div class="input-group-addon">$</div>
			<input type="text" name="price" class="form-control"
				placeholder="Price per Unit" />
		</div>
		<br />
		<br /> <input type="submit" class="btn btn-primary"
			value="Submit My Item" />
	</form>
</body>
</html>