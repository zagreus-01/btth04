<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>User Dashboard</title>
    <style>
        body { font-family: Arial, sans-serif; margin: 20px; }
        .navbar { display: flex; justify-content: space-between; align-items: center; margin-bottom: 20px; }
        h2 { margin: 0; }
        .logout-btn { padding: 6px 12px; cursor: pointer; }

        h3 { margin-top: 0; }
        table { width: 100%; border-collapse: collapse; margin-top: 20px; }
        table, th, td { border: 1px solid #ddd; }
        th, td { padding: 10px; text-align: left; }
        th { background: #f4f4f4; }

        /* Actions column if needed */
        th.actions, td.actions {
            width: 120px;
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
            top: 50%; left: 50%;
            transform: translate(-50%, -50%);
            background: #fff; padding: 20px; border: 1px solid #ddd;
            box-shadow: 0 2px 8px rgba(0,0,0,0.3);
            z-index: 1000;
        }
        .popup.active { display: block; }
        .popup h3 { margin-top: 0; }
        .popup p { margin: 6px 0; }
        .popup button { padding: 6px 12px; margin-top: 10px; }
    </style>
</head>
<body>
<div class="navbar">
    <h2>User Dashboard</h2>
    <form action="${pageContext.request.contextPath}/logout" method="get">
        <button type="submit" class="logout-btn">Logout</button>
    </form>
</div>

<h3>Category List</h3>
<table>
    <tr>
        <th>ID</th>
        <th>Name</th>
        <th class="actions">Actions</th>
    </tr>
    <c:forEach var="category" items="${categories}">
        <tr>
            <td>${category.id}</td>
            <td>${category.name}</td>
            <td class="actions">
                <button onclick="openViewPopup('${category.id}', '${category.name}')">View</button>
            </td>
        </tr>
    </c:forEach>
</table>

<!-- View Popup -->
<div id="viewPopup" class="popup">
    <h3>Category Details</h3>
    <p><b>ID:</b> <span id="viewId"></span></p>
    <p><b>Name:</b> <span id="viewName"></span></p>
    <button onclick="closePopup('viewPopup')">Close</button>
</div>

<script>
    function openViewPopup(id, name) {
        document.getElementById("viewId").innerText = id;
        document.getElementById("viewName").innerText = name;
        document.getElementById("viewPopup").classList.add("active");
    }

    function closePopup(popupId) {
        document.getElementById(popupId).classList.remove("active");
    }
</script>
</body>
</html>
