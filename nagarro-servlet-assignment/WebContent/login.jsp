<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">

<link rel="stylesheet" href="css/style.css" />
<link rel="stylesheet" href="css/bootstrap.css" />

<title>Login</title>
</head>
<body>

	<form action="login" method="POST">
		<div class="container p-5 maxx-width">

			<div class="row mb-4">
				<div class="col error">
					<h2>${error}</h2>
				</div>
			</div>

			<div class="row login font-bold p-1 login-head">
				<div class="col ">Login</div>
			</div>

			<div class="row login-body ">
				<div class="row mt-2 form-group">
					<div class="col-sm-3">
						<label for="Name" class="col-form-label">Username: </label>
					</div>
					<div class="col-sm-6 offset-sm-1 col-12">
						<input type="text" name="username" class="form-control" required />
					</div>
				</div>
				<div class="row mt-2 mb-1 form-group">
					<div class="col-sm-3">
						<label for="Name" class="col-form-label">Password: </label>
					</div>
					<div class="col-sm-6 offset-sm-1 col-12">
						<input type="password" name="password" class="form-control"
							required />
					</div>
				</div>
				<div class="row mt-2 mb-3 form-group">
					<div class="col offset-sm-4 ">
						<a href="#">Forgot your password?</a>
					</div>
				</div>
			</div>

			<div class="row login footer">
				<div class="col">
					<button type="submit" class="offset-9 btn btn-light-blue">Login
						>></button>
				</div>
			</div>

		</div>
	</form>
	<c:remove var="error" />
</body>
</html>