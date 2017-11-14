<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html lang="en">
<%@ include file="../jspf/header.jspf"%>
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
  <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
  <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
<body>
<%@ include file="../jspf/navbar.jspf"%>
	<div class="body-container">
		<div class="content-container" style="padding-top: 50px;">
			<table class ="table table-striped"style="width: 75%; margin: 0 auto; text-align: center;">
			Products
				<thead>
					<tr>
						<th>Artist</th>
						<th>Title</th>
						<th>Genre</th>
						<th>Price</th>
						<th>Stock</th>
						<th>URL</th>
						<th>Update</th>
						<th>Delete</th>
					</tr>
				</thead>
				<tbody style="text-align:left;">
				
				<!-- should use the c:out for these -->
					<c:forEach var="p" items="${prods}">
						<tr>
							<td>${p.artist}</td>
							<td>${p.title}</td>
							<td>${p.genre}</td>
							<td>${p.price}</td>
							<td>${p.stock}</td>
							<td>${p.price}</td>
							<form method="POST" action="ViewProducts">
							<input type="hidden" value='${p.idProduct}' name="idProduct">
							<td><button class="btn glyphicon glyphicon-pencil" type = "submit" name = "update"></button></td>
    						<td><button class="btn glyphicon glyphicon-trash" type="submit" name="delete" style="color:red"></button></td>
							</form>
						</tr>
					</c:forEach>
				
				
				</tbody>
			</table>



		</div>
	</div>
</body>
</html>