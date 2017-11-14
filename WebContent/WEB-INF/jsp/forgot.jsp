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
			<h2>Forgot Password</h2>
			<div> Submit your username, and an email will be sent to the email address associated with that username with a new password
			.It is suggested that you reset your password upon logging in.</div>
				<form role="form" action="Forgot" method="POST" required>
				<%@ include file="../jspf/message.jspf"%>
				<label><h3>Username</h3></label> <input type="text"
					placeholder="Enter Username" name="uname" required autofocus/> <br/><br/>
					<br/>
				<button class="loginBtn" type="submit">Reset</button>
				</form>
			</div>
			
			
		</div>
	</div>
	<%@ include file="../jspf/footer.jspf"%>
</body>
</html>