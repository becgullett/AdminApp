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
				<form role="form" action="AddProduct" method="POST" required enctype="multipart/form-data">
				<h2>Add Product</h2>
				Note: default image used for album cover
				<br>
					<label><h3>Artist</h3></label> <input type="text"
						placeholder="Enter Artist" name="artist" value='${pf.artist}' autofocus required /> <br />
					<br /> <label><h3>Title</h3></label> <input type="text"
						placeholder="Enter Title" name="title" value='${pf.title}' required /> <br />
					<br /> <label><h3>Genre</h3></label> <input type="text"
						placeholder="Enter Genre" name="genre" value='${pf.genre}'required /> <br /> <br />
					<label><h3>Price</h3></label> <input type="number"
						placeholder="Enter Price" name="price" value='${pf.price}' required /> <br />
					<br /> <label><h3>Stock</h3></label> <input type="number"
						placeholder="Enter Stock" name="stock" value='${pf.stock}'required> <br />
					 <br /> <br /> <br />
					<button class="loginBtn" type="submit">Add Product</button>

				</form>
			</div>
	<h3><a href="AddProducts">Add Multiple Products</a></h3><br>

		</div>
	</div>
</body>
</html>