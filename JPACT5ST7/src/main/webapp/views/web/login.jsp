<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Login</title>
<style>
body {
	font-family: Arial, sans-serif;
	text-align: center;
	margin-top: 50px;
}

.form-container {
	width: 300px;
	margin: 0 auto;
	padding: 20px;
	border: 1px solid #ccc;
	border-radius: 5px;
}

.form-group {
	margin-bottom: 15px;
}

label {
	display: block;
	margin-bottom: 5px;
}

input, select {
	width: 100%;
	padding: 8px;
	box-sizing: border-box;
}

button {
	padding: 10px 20px;
	background-color: #4CAF50;
	color: white;
	border: none;
	border-radius: 5px;
	cursor: pointer;
}

button:hover {
	background-color: #45a049;
}

.error {
	color: red;
	font-size: 14px;
	margin-top: 10px;
	display: <%= request.getAttribute ( "error") != null ? "block" : "none"
		%>;
}
</style>
</head>
<body>
	<div class="form-container">
		<h2>Login</h2>
		<div class="error"><%= request.getAttribute("error") != null ? request.getAttribute("error") : "" %></div>
		<form action="login" method="post">
			<div class="form-group">
				<label for="username">Username:</label> <input type="text"
					id="username" name="username"
					value="<%= request.getParameter("username") != null ? request.getParameter("username") : "" %>"
					required>
			</div>
			<div class="form-group">
				<label for="password">Password:</label> <input type="password"
					id="password" name="password" required>
			</div>
			<div class="form-group">
				<label for="role">Role:</label> <select id="role" name="role"
					required>
					<option value="0"
						<%= "0".equals(request.getParameter("role")) ? "selected" : "" %>>User</option>
					<option value="1"
						<%= "1".equals(request.getParameter("role")) ? "selected" : "" %>>Admin</option>
					<option value="2"
						<%= "2".equals(request.getParameter("role")) ? "selected" : "" %>>Manager</option>
				</select>
			</div>
			<button type="submit">Login</button>
		</form>
		</p>
	</div>
</body>
</html>