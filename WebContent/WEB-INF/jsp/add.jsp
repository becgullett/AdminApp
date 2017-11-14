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
				<form role="form" action="Add" method="POST" required>
				<h2>Add User</h2>
				<br>
				<input type="hidden" name="action" value="add_user"> 
					<label><h3>First Name</h3></label> <input type="text"
						placeholder="Enter First Name" name="fname" value='${af.firstName}' autofocus required /> <br />
					<br /> <label><h3>Last Name</h3></label> <input type="text"
						placeholder="Enter last Name" name="lname" value='${af.lastName}' required /> <br />
					<br /> <label><h3>Email</h3></label> <input type="text"
						placeholder="Enter Email" name="email" value='${af.email}'required /> <br /> <br />
					<label><h3>User Name</h3></label> <input type="text"
						placeholder="Enter Username" name="uname" value='${af.username}' required /> <br />
					<br /> <label><h3>Password</h3></label> <input type="password"
						placeholder="Enter Password" name="password" value='${af.password}'required> <br />
					<br /> <label><h3>Security</h3></label>
					<select name="security" required>
					<option>Administrator</option>
					<option selected>Shopper</option>
					</select> <br /> <br /> <br />
					<button class="loginBtn" type="submit">Add User</button>

				</form>
				
				<br><br>
				
				</form>
				<h3><a href="AddUsers">Add Multiple Users</a></h3><br>
			</div>


		</div>
	</div>
</body>
</html>