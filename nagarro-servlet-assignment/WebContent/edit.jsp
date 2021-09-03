<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
	<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<link rel="stylesheet" href="css/bootstrap.css" />
<link rel="stylesheet" href="css/style.css" />
<title>Edit</title>
</head>
<body>
	
	<c:if test="${empty userID}">
		<c:redirect url="dashboard" ></c:redirect>
	</c:if>
	
	<div class="edit-main">
		<form action="edit" method="post" enctype="multipart/form-data"
			class="form-edit">
			<div class="row item">
				<div class="col">
					<input type="text" name="new-name" class="form-control"
						placeholder="New Name (Optional)" />
				</div>
			</div>
			<div class="row item">
				<div class="col">
					<input type="file" name="new-file" class="form-control"
						placeholder="Nw File (Optional)" />
				</div>
			</div>
			<div class="row item">
				<div class="col">
					<button type="submit" class="btn btn-primary col-12">Submit</button>
				</div>
			</div>
		</form>
	</div>
	
</body>
</html>