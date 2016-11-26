<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<head>
<link rel='stylesheet'
	href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css"
	integrity="sha384-BVYiiSIFeK1dGmJRAkycuHAHRg32OmUcww7on3RYdg4Va+PmSTsz/K68vbdEjh4u"
	crossorigin="anonymous">
<title>Login</title>
</head>
</head>
<body>
<body>
	<form action='Login' method='post' class="form-group">
		User Id: <input type='text' name='email' class="form-control"	placeholder='Email' />
		Passcode: <input type='password' name='password' class="form-control" placeholder='Passcode' />
		<div class="checkbox">
			<label><input type='checkbox' name='rememberMe' value="true" />Stay	signed in</label>
		</div>
		<input type='submit' value='Sign In' class="btn btn-default" />
	</form>
</body>
</html>
</body>
</html>