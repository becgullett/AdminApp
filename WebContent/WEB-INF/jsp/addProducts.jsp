<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html lang="en">
<%@ include file="../jspf/header.jspf"%>
<body>
	<!-- Body container and content container should be on every page except for landing page -->
	<div class="body-container">
	<%@ include file="../jspf/navbar.jspf"%>
	<div class="content-container">
		<div class="login-container">
			<%@ include file="../jspf/message.jspf"%>
			<form role="form" enctype="multipart/form-data" action="AddProducts" method="POST" required>
			<h2>Add Multiple Products</h2>
			
			
			<br /> <label><h3>Upload .csv File</h3></label><br/><br/>
			<input type="file" name="file" required value="Choose .csv to add multiple products" accept=".csv" enctype="multipart/form-data"/>
			<br><br><button class="loginBtn" type="submit">Add Products</button>
			</form>
			
			<br>
		</div>
	</div>
	</div>
</body>
</html>