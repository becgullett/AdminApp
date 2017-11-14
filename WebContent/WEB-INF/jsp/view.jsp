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
			USERS
				<thead>
					<tr>
						<th>First Name</th>
						<th>Last Name</th>
						<th>Email</th>
						<th>Username</th>
						<th>Locked</th>
						<th>Security</th>
						<th>Update</th>
						<th>Delete</th>
					</tr>
				</thead>
				<tbody style="text-align:left;">
				
				<!-- should use the c:out for these -->
					<c:forEach var="u" items="${users}">
						<tr>
							<td>${u.firstName}</td>
							<td>${u.lastName}</td>
							<td>${u.email}</td>
							<td>${u.username}</td>
							<td><c:choose>
  								<c:when test='${u.locked == true}'>
  								<span class="glyphicon glyphicon-lock" style="color:red"></span>
  								</c:when>
  								<c:otherwise>
  								<span class="glyphicon glyphicon-remove" style="color:green"></span></c:otherwise>
								</c:choose></td>
							<td> <c:choose>
								<c:when test="${u.security == '10'}">
  								Guest
  								</c:when>
  								<c:when test="${u.security == '20'}">
  								Shopper
  								</c:when>
  								<c:when test="${u.security == '50'}">
  								Administrator
  								</c:when>
  								<c:when test="${u.security == '90'}">
  								Developer
  								</c:when>
								</c:choose></td>
							<form method="POST" action="ViewUsers">
							<input type="hidden" value='${u.username}' name="username">
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