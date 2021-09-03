<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1" isELIgnored="false"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>

<!doctype html>
<html lang="en">
<head>
<!-- Required meta tags -->
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1">

<!-- Bootstrap CSS -->
<link
	href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css"
	rel="stylesheet"
	integrity="sha384-EVSTQN3/azprG1Anm3QDgpJLIm9Nao0Yz1ztcQTwFspd3yD65VohhpuuCOmLASjC"
	crossorigin="anonymous">

<title>Available Flights</title>
</head>
<body>

	<c:if test="${flights.size()==0}">
		<div class="alert alert-danger mt-5" role="alert">No Flights
			Available</div>
	</c:if>

	<c:if test="${flights.size() > 0}">

		<h1 class="bg-secondary bg-gradient text-white text-center mt-5 p-2">Available
			Flights ...</h1>

		<table class="table text-center table-bordered mt-5">
			<thead>
				<tr>
					<th scope="col">Flight Num</th>
					<th scope="col">Departure Location</th>
					<th scope="col">Arrival Location</th>
					<th scope="col">Date</th>
					<th scope="col">Fare</th>
					<th scope="col">Flight Duration</th>
					<th scope="col">Flight Time</th>
				</tr>
			</thead>
			<tbody>
				<c:forEach items="${flights}" var="flight">
					<fmt:formatNumber var="num" type="number" value="${flight.fare}"
						maxFractionDigits="0" />
					<c:set var="dateParts" value="${fn:split(flight.validTill,' ')}" />
					<tr>
						<td>${flight.flightNum}</td>
						<td>${flight.depLoc}</td>
						<td>${flight.arrLoc}</td>
						<td>${dateParts[0]}</td>
						<td>${num}</td>
						<td>${flight.flightDuration}</td>
						<td>${flight.flightTime}</td>
					</tr>
				</c:forEach>
			</tbody>
		</table>

	</c:if>

	<form action="/">
		<div class="d-grid col-6 mx-auto m-5">
			<button type="submit" class="btn btn-outline-primary">Search
				Another Flight</button>
		</div>
	</form>

	<script
		src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/js/bootstrap.bundle.min.js"
		integrity="sha384-MrcW6ZMFYlzcLA8Nl+NtUVF0sA7MsXsP1UyJoMp4YLEuNSfAP+JcXn/tWtIaxVXM"
		crossorigin="anonymous"></script>
</body>

</html>