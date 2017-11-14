<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="edu.ben.models.User"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html lang="en">

<%@ include file="../jspf/header.jspf"%>



<body>

	<%@ include file="../jspf/navbar.jspf"%>

	<!-- Body container and content container should be on every page except for landing page -->
	<div class="body-container">
		<div class="content-container">

			<!-- Product Container is needed for each product to be responsive on the page -->
			<c:forEach var="tempProd" items="${cdArrayList}">


				<div class="prod-container">
					<div class="prod-image">
						<img src="${tempProd.url}" alt="album"
							class="album-thumbnail" border="5"> <input
							class="add-to-cart-btn" type="button" id="${tempProd.idCd}"
							value="Add To Cart" onClick="addToCart(this.id)">
					</div>
					<div class="cd-description">
						<h3>Artist:</h3>
						${tempProd.artist}<br />
						<h3>Album Title:</h3>
						${tempProd.title}<br />
						<h3>Genre:</h3>
						${tempProd.genre}<br />
						<h3>Available:</h3>
						${tempProd.stock}<br />
						<h3>$${tempProd.price}</h3>
					</div>
				</div>
			</c:forEach>
			<!-- End of Product Container -->


			<!-- Do not remove this... it pushes the pagination to the bottom of the page lol -->
			<div style="width: 100%; color: white; clear: both;">.</div>



			<!-- Pagination begins -->
			<div class="pagination">
				<a href="#">&laquo;</a> <a href="#">1</a> <a class="active" href="#">2</a>
				<a href="#">3</a> <a href="#">4</a> <a href="#">5</a> <a href="#">6</a>
				<a href="#">&raquo;</a>
			</div>
			<!-- End of Pagination -->

		</div>
	</div>

	<%@ include file="../jspf/footer.jspf"%>



	<!-- Java script-->
	<script src="https://unpkg.com/axios/dist/axios.min.js"></script>
	<script src="https://unpkg.com/sweetalert/dist/sweetalert.min.js"></script>
</body>




</html>