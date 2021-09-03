<%@page import="org.apache.jasper.tagplugins.jstl.core.ForEach"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"
	import="entities.Image,java.util.List,java.io.FileOutputStream,java.io.File"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">

<link rel="stylesheet" href="css/bootstrap.css" />
<link rel="stylesheet" href="css/style.css" />

<title>Dashboard</title>
</head>
<body>


	<c:if test="${empty userID}">
		<c:redirect url="login" ></c:redirect>
	</c:if>
	
	<div class="container mt-3">
		<div class="row mb-4">
			<div class="col error">
				<h2>${message}</h2>
			</div>
		</div>
	</div>

	<div class="container bdr mt-3">
		<div class="row">
			<div class="col text-center">
				<h2 class="mt-3">Image Management Utility</h2>
			</div>
		</div>
		<!-- <div class="row hr"></div> -->
		<div class="row hr">
			<div class="col mt-3">
				<p>Please select an image file to upload (Max Size 1 MB)</p>
			</div>
		</div>

		<!-- upload images -->
		<form action="upload" method="POST" enctype="multipart/form-data">
			<div class="row mt-3">
				<div class="col-sm-6">
					<div class="row">
						<div class="col-sm-6 mb-1">
							<input class="form-control" type="text" name="image-name"
								required />
						</div>
						<div class="col-sm-6 mb-1">
							<input class="form-control" type="file" name="image-file"
								required />
						</div>
					</div>
				</div>
				<div class="col">
					<button type="submit"
						class="col-sm-4 offset-sm-3 col-12 mb-1 btn btn-secondary">Submit</button>
					<button type="reset" class="col-sm-4 col-12 btn btn-secondary mb-1">Cancel</button>
				</div>
			</div>
		</form>

		<!-- display images -->

		<div class="row margin-t margin-lr">
			<div class="col mb-3 mt-2 font-bold">Uploaded Images</div>
		</div>

		<div class="row font-bold text-break margin-lr">
			<div class="col-1 bdrtd bl-black">S.No</div>
			<div class="col-3 bdrtd">Name</div>
			<div class="col-2 bdrtd">Size (KB)</div>
			<div class="col-3 bdrtd">Preview</div>
			<div class="col-3 bdrtd">Actions</div>
		</div>

		<c:forEach items="${images}" var="image">
			<c:set value="${totalSize+image.size}" var="totalSize" />
			<c:set value="${i+1}" var="i" />
			<div class="row font-bold text-break margin-lr">
				<div class="col-1 bdrtd bl-black">
					<div class="center-items">${i}</div>
				</div>
				<div class="col-3 bdrtd">
					<div class="center-items">${image.name}</div>
				</div>
				<div class="col-2 bdrtd">
					<fmt:formatNumber var="num" type="number" value="${image.size/1024}" maxFractionDigits="0"/>
					<div class="center-items">${num}</div>
				</div>
				<div class="col-3 bdrtd">
					<div class="center-items">
						<img src="imageload?id=${image.id}" class="img-thumbnail"
							width="60px" />
					</div>
				</div>
				<div class="col-3 bdrtd">
					<div class="row" style="height: 100%;">
						<div class="col">
							<div class="center-items">
								<a href="edit?id=${image.id}">edit</a>
							</div>
						</div>
						<div class="col">
							<div class="center-items">
								<a href="delete?id=${image.id}">delete</a>
							</div>
						</div>
					</div>
				</div>
			</div>

		</c:forEach>
		
		<fmt:formatNumber var="num" type="number" value="${totalSize/1024}" maxFractionDigits="0"/>
		<div class="row font-bold margin-b margin-lr">
			<div class="col-sm-3 col offset-sm-1 bdrtd bl-black">Total Size
				(KB):</div>
			<div class="col-sm-2 col bdrtd">${num}</div>
		</div>

	</div>
	
	<c:remove var="message" />
	
</body>
</html>