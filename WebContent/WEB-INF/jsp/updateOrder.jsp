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
		<%@ include file="../jspf/message.jspf"%>
			<table class ="table table-striped"style="width: 75%; margin: 0 auto; text-align: center;">
			ORDER ${order.idOrder}
				<thead>
					<tr>
						<th>Item ID</th>
						<th>Product</th>
						<th>Quantity</th>
						<th>Update</th>
						<th>Delete</th>
					</tr>
				</thead>
				<tbody style="text-align:left;">
				
				<!-- should use the c:out for these -->
					<c:forEach var="ol" items="${order.lines}">
						<tr>
							<td>${ol.product.idProduct}</td>
							<td>${ol.product.title} by ${ol.product.artist}</td>
							<form method="POST" action="UpdateOrder">
							<input name ="orderID" value='${order.idOrder}' type="hidden">
							<input name ="orderlineID" value='${ol.idOrderline}' type="hidden">
							<td><input name ="quantity" value='${ol.quantity}' type="number" min="0"></td>
							<td><button class="btn glyphicon glyphicon-pencil" type="submit" name="update"></button></td>
							<td><button class="btn glyphicon glyphicon-trash" type="submit" name="delete" style="color:red"></button></td>
							</form>
						</tr>
					</c:forEach>
				</tbody>
			</table>
			<form method="POST" action="UpdateOrder">
			<input type="hidden" name="orderID" value="${order.idOrder}">  
			<label><h3>User</h3></label>
        	<input type="text" name="username" value="${order.username}" 
               readonly><br>      
        	<label><h3>Shipping Address</h3></label>
        	<input type="text" name="address" value="${order.shippingAddress}" 
               required><br>
            <label><h3>Shipping Name</h3></label>
        	<input type="text" name="name" value="${order.shippingName}" 
               required><br>
            <label><h3>Shipping Type</h3></label>
            <select name="temp">
            <option>Standard</option>
            <option>Two-Day</option>
            <option>Next-Day</option>
            </select>
            
            <label><h3>Shipped</h3></label>
            <input type="checkbox" name="ship">
        <br>
        	<button class="loginBtn" name="submit" type="submit">Update</button>
             <br><br><br>
			</form>



		</div>
	</div>
</body>
</html>