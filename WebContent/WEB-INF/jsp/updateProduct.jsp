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
    <h3>Update Product</h3>
    <p>Note: Cannot edit the image location right now.</p>
    <form action="UpdateProduct" method="post">
        <input type="hidden" name="idProduct" value="${updateProduct.idProduct }">        
        <label><h3>Artist</h3></label>
        <input type="text" name="artist" value="${updateProduct.artist}" 
               required><br>
        <label><h3>Title</h3></label>
        <input type="text" name="title" value="${updateProduct.title}"  
               required><br>       
        <label><h3>Genre</h3></label>
        <input type="text" name="genre" value="${updateProduct.genre}" required><br>
        <label><h3>Price</h3></label>
        <input type="text" name="price" value="${updateProduct.price}" required><br>
        <label><h3>Stock</h3></label>
        <input type="text" name="stock" value="${updateProduct.stock}" required><br>
        <br>
        <br><br>
        <button class="loginBtn" type="submit">Update</button>
    </form>
    </div>
   </div>
</div>
<%@ include file="../jspf/footer.jspf"%>
</body>
</html>