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
			ORDERS
				<thead>
					<tr>
						<th>Order ID</th>
						<th>Customer</th>
						<th>Shipping Address</th>
						<th>Shipping Name</th>
						<th>Shipped</th>
						<th>Shipping Type</th>
						<th>Update</th>
						<th>Delete</th>
					</tr>
				</thead>
				<tbody style="text-align:left;">
				
				<!-- should use the c:out for these -->
					<c:forEach var="o" items="${orders}">
						<tr>
							<td>${o.idOrder}</td>
							<td>${o.username}</td>
							<td>${o.shippingAddress}</td>
							<td>${o.shippingName}</td>
							<td><c:choose>
  								<c:when test='${o.shipped == true}'>
  								<span class="glyphicon glyphicon-ok" style="color:green"></span>
  								</c:when>
  								<c:otherwise>
  								<span class="glyphicon glyphicon-remove" style="color:red"></span></c:otherwise>
								</c:choose></td>
							<td> <c:choose>
								<c:when test="${o.shippingType == '1'}">
  								Standard
  								</c:when>
  								<c:when test="${o.shippingType == '2'}">
  								Two-Day
  								</c:when>
  								<c:when test="${o.shippingType == '5'}">
  								Next-Day
  								</c:when>
								</c:choose></td>
							<form method="POST" action="Orders">
							<input type="hidden" value='${o.idOrder}' name="idOrder">
							
							<td><c:choose>
  								<c:when test='${o.shipped == true}'>
  								<button class="btn glyphicon glyphicon-pencil" type="submit" name="uo" disabled></button>
  								</c:when>
  								<c:otherwise>
  								<button class="btn glyphicon glyphicon-pencil" type = "submit" name="uo"></button></c:otherwise>
							</c:choose></td>
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