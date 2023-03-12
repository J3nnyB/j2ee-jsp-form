<!DOCTYPE html>
<html>
<!-- Name: Jennifer Byrne
	 Date: February 23, 2020
	 Notes: ENTD481 Week 3 Assignment - "Thank you" JSP page that is supposed to echo values/parameters 
	 		passed in the index.html form.
-->
<head>
	<meta charset="utf-8">
	<title>Thank you</title>
</head>
<body>
	<h1>Your contact information has been received!</h1>
	
	<p>Here is what you entered:</p>
	
	<label>Customer ID:</label>
	<span>${customerId}</span><br />
	
	<label>First Name:</label>
	<span>${fName}</span><br />
	
	<label>Last Name:</label>
	<span>${lName}</span><br />
	
	<label>Email:</label>
	<span>${email}</span><br />
	
	<label>Phone Number:</label>
	<span>${phone}</span><br />
	
	<p>Please allow 1-2 business days for us to contact you. We will do our best to respond as soon as we can.
	   Thank you for your patience.</p>
	
</body>
</html>