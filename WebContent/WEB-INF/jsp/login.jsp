<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html lang="en">

<%@ include file="../jspf/header.jspf"%>



<body>
	<%@ include file="../jspf/navbar.jspf"%>
	<!-- Body container and content container should be on every page except for landing page -->
	<div class="body-container">
		<div class="content-container">
		
			<div class="login-container">
			<h2>Login</h2>
				<form role="form" action="Login" method="POST" required>
				<%@ include file="../jspf/message.jspf"%>
				<input type="hidden" name="action" value="login"> 
				<label><h3>Username</h3></label> <input type="text"
					placeholder="Enter Username" name="uname" required tabindex="1" autofocus/> <br/><br/>
					<label><h3>Password</h3></label>
				<input type="password" placeholder="Enter Password" name="password"
					required tabindex="2">
					<br/><br/><br/>
				<a href="Forgot" class = "btn">Forgot Password?</a>
				<button class="loginBtn" type="submit" tabindex="3">Login</button>
				<br/>
				
				</form>
			</div>
			
			
		</div>
	</div>
	<%@ include file="../jspf/footer.jspf"%>
</body>
</html>