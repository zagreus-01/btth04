<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Admin Dashboard</title>
<style>
body {
	font-family: Arial, sans-serif;
	margin: 20px;
}

.navbar {
	display: flex;
	justify-content: space-between;
	align-items: center;
	margin-bottom: 20px;
}

h2 {
	margin: 0;
}

.logout-btn {
	padding: 6px 12px;
	cursor: pointer;
}

h3 {
	margin-top: 0;
}

.add-form input {
	padding: 6px;
	margin-right: 6px;
}

.add-form button {
	padding: 6px 12px;
	cursor: pointer;
}

table {
	width: 100%;
	border-collapse: collapse;
	margin-top: 20px;
}

table, th, td {
	border: 1px solid #ddd;
}

th, td {
	padding: 10px;
	text-align: left;
}

th {
	background: #f4f4f4;
}

/* Actions nhỏ gọn */
th.actions, td.actions {
	width: 180px;
	text-align: center;
	white-space: nowrap;
}

td.actions button {
	padding: 4px 8px;
	font-size: 0.9em;
	margin: 2px;
	cursor: pointer;
}

/* Popup style */
.popup {
	display: none;
	position: fixed;
	top: 50%;
	left: 50%;
	transform: translate(-50%, -50%);
	background: #fff;
	padding: 20px;
	border: 1px solid #ddd;
	box-shadow: 0 2px 8px rgba(0, 0, 0, 0.3);
	z-index: 1000;
}

.popup.active {
	display: block;
}

.popup h3 {
	margin-top: 0;
}

.popup input {
	padding: 6px;
	margin-bottom: 10px;
	width: 100%;
}

.popup button {
	padding: 6px 12px;
	margin-right: 6px;
}
</style>
</head>
<body>
	<div class="navbar">
		<h2>Admin Dashboard</h2>
		<form action="${pageContext.request.contextPath}/logout" method="get">
			<button type="submit" class="logout-btn">Logout</button>
		</form>
	</div>

	<h3>All Categories</h3>
	<form action="${pageContext.request.contextPath}/admin/category/add"
		method="post" class="add-form">
		<input type="text" name="name" placeholder="Enter category name"
			required>
		<button type="submit">Add</button>
	</form>

	<table>
		<tr>
			<th>ID</th>
			<th>Name</th>
			<th>Owner</th>
			<th class="actions">Actions</th>
		</tr>
		<c:forEach var="category" items="${categories}">
			<tr>
				<td>${category.id}</td>
				<td>${category.name}</td>
				<td>${category.user.username}</td>
				<td class="actions">
					<button
						onclick="openViewPopup('${category.id}', '${category.name}', '${category.user.username}')">View</button>
					<button
						onclick="openEditPopup('${category.id}', '${category.name}')">Edit</button>
					<a
					href="${pageContext.request.contextPath}/admin/category/delete?id=${category.id}">
						<button type="button"
							onclick="return confirm('Delete this category?')">Delete</button>
				</a>
				</td>
			</tr>
		</c:forEach>
	</table>

	<!-- View Popup -->
	<div id="viewPopup" class="popup">
		<h3>Category Details</h3>
		<p>
			<b>ID:</b> <span id="viewId"></span>
		</p>
		<p>
			<b>Name:</b> <span id="viewName"></span>
		</p>
		<p>
			<b>Owner:</b> <span id="viewOwner"></span>
		</p>
		<button onclick="closePopup('viewPopup')">Close</button>
	</div>

	<!-- Edit Popup -->
	<div id="editPopup" class="popup">
		<h3>Edit Category</h3>
		<form action="${pageContext.request.contextPath}/admin/category/edit"
			method="post">
			<input type="hidden" name="id" id="editId"> <input
				type="text" name="name" id="editName" required>
			<button type="submit">Save</button>
			<button type="button" onclick="closePopup('editPopup')">Cancel</button>
		</form>
	</div>

	<script>
    function openViewPopup(id, name, owner) {
        document.getElementById("viewId").innerText = id;
        document.getElementById("viewName").innerText = name;
        document.getElementById("viewOwner").innerText = owner;
        document.getElementById("viewPopup").classList.add("active");
    }

    function openEditPopup(id, name) {
        document.getElementById("editId").value = id;
        document.getElementById("editName").value = name;
        document.getElementById("editPopup").classList.add("active");
    }

    function closePopup(popupId) {
        document.getElementById(popupId).classList.remove("active");
    }
</script>
</body>
</html>
