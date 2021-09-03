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

<title>Home</title>
</head>
<body>

	<form action="/search" method="POST">

		<div class="container home">
			<h2 class="text-center mt-4">Search Flights...</h2>

			<div class="row mt-5 m-2">
				<div class="col ">
					<input name="depLoc" type="text" class="form-control"
						placeholder="Departure Location" required />
				</div>
			</div>

			<div class="row m-2">
				<div class="col">
					<input name="arrLoc" type="text" class="form-control"
						placeholder="Arrival Location" required />
				</div>
			</div>
			
			<div class="row m-3 align-items-center">
				<div class="col-sm-3">Date :</div>
				<div class="col offset-sm-0 offset-3">
					<input name="date" type="date" class="form-control" required />
				</div>
			</div>

			<div class="row m-3 align-items-center">
				<div class="col-sm-3">Flight Class :</div>
				<div class="col-sm-3 offset-sm-0 offset-3">
					<div class="form-check">
						<input class="form-check-input" type="radio" name="flightClass"
							id="bClass" value="B"> <label class="form-check-label"
							for="bClass">Business</label>
					</div>
				</div>
				<div class="col offset-sm-0 offset-3">
					<div class="form-check">
						<input class="form-check-input" type="radio" name="flightClass"
							id="eClass" value="E" checked> <label
							class="form-check-label" for="eClass">Economy</label>
					</div>
				</div>
			</div>

			<div class="row m-3 align-items-center">
				<div class="col-sm-3">Output Preference :</div>
				<div class="col-sm-3 offset-sm-0 offset-3">
					<div class="form-check">
						<input class="form-check-input" type="radio" name="op" id="fare"
							value="Fare" checked /> <label class="form-check-label" for="fare">Fare</label>
					</div>
				</div>
				<div class="col offset-sm-0 offset-3">
					<div class="form-check">
						<input class="form-check-input" type="radio" name="op" id="ffd"
							value="ffd" > <label class="form-check-label"
							for="ffd">Flight Duration</label>
					</div>
				</div>
			</div>

			<div class="d-grid col-6 mx-auto m-5">
				<button type="submit" class="btn btn-outline-success">Search
					Flights</button>
			</div>

		</div>

	</form>

	<script
		src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/js/bootstrap.bundle.min.js"
		integrity="sha384-MrcW6ZMFYlzcLA8Nl+NtUVF0sA7MsXsP1UyJoMp4YLEuNSfAP+JcXn/tWtIaxVXM"
		crossorigin="anonymous"></script>
</body>

</html>