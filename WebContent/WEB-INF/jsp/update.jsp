<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html lang="en">

<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
  <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
  <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
<%@ include file="../jspf/header.jspf"%>
<body>
<%@ include file="../jspf/navbar.jspf"%>
<div class="body-container">
	<div class="content-container">
		<div class="login-container">
		<%@ include file="../jspf/message.jspf"%>
    <h3>Update User</h3>
    <p>NOTE: You can't update the username.</p>
    <form action="Update" method="post">
        <input type="hidden" name="action" value="update_user">        
        <label><h3>First Name</h3></label>
        <input type="text" name="fname" value="${update.firstName}" 
               required><br>
        <label><h3>Last Name</h3></label>
        <input type="text" name="lname" value="${update.lastName}"  
               required><br>       
        <label><h3>Email</h3></label>
        <input type="text" name="email" value="${update.email}" required><br>
        <label><h3>Username</h3></label>
        <input type="hidden" value="${update.username}" name = "uname">
        <input type="text" value="${update.username}" 
               disabled><br>
        <label><h3>Locked</h3></label>
        <c:choose>
        <c:when test="${update.locked == true}"><input type="checkbox" name="locked" checked></c:when>
        <c:otherwise><input type="checkbox" name="locked"></c:otherwise>
        </c:choose>
    <br>
        <label><h3>Security</h3></label>
        <select name="security"required>
    	<c:choose>
    	<c:when test="${update.security == '20'}"><option> Administrator</option><option selected>Shopper</option></c:when>
    	<c:when test="${update.security == '50'}"><option selected> Administrator</option><option>Shopper</option></c:when>
    	</c:choose>
        </select>
        <br>
        
        <label><h3>Reset Password</h3></label>
        <input type="checkbox" name="resetPassword">
        <br><br>
        <button class="loginBtn" type="submit">Update</button>
    </form>
    </div>
   </div>
</div>
<%@ include file="../jspf/footer.jspf"%>
</body>
</html>